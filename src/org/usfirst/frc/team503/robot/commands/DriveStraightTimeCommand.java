package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightTimeCommand extends Command {
	private double seconds;
	private double initAngle;
	private Timer timer = new Timer();
	
    public DriveStraightTimeCommand(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.seconds = seconds;
    	requires(NavSensorSubsystem.instance);
    	requires(NewDrivetrainSubsystem.instance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initAngle = NavSensorSubsystem.ahrs.getFusedHeading();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(NavSensorSubsystem.ahrs.getFusedHeading()>(initAngle+RobotMap.COMPASS_TOLERANCE)%360){
    		NewDrivetrainSubsystem.instance.tankDrive(0,-RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	else if(NavSensorSubsystem.ahrs.getFusedHeading()<(((initAngle-RobotMap.COMPASS_TOLERANCE)<0)?(initAngle-RobotMap.COMPASS_TOLERANCE)%360+360:initAngle-RobotMap.COMPASS_TOLERANCE)){
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED,0, false);
    	}
    	else{
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    	/*if(NavSensorSubsystem.ahrs.getYaw()>(initAngle+RobotMap.COMPASS_TOLERANCE)){
    		DrivetrainSubsystem.instance.tankDrive(0,-RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	else if(NavSensorSubsystem.ahrs.getFusedHeading()<(initAngle-RobotMap.COMPASS_TOLERANCE)){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED,0, false);
    	}
    	else{
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get()>seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	NewDrivetrainSubsystem.instance.tankDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
