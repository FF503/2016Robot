package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightDistanceCommand extends Command {
	private double inches;
	private double initAngle;
    public DriveStraightDistanceCommand(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DrivetrainSubsystem.instance.enable();
    	DrivetrainSubsystem.instance.setSetpoint(inches);
    	initAngle=NavSensorSubsystem.ahrs.getFusedHeading();
    	DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(NavSensorSubsystem.ahrs.getFusedHeading()>(initAngle+RobotMap.COMPASS_TOLERANCE)%360){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.drivePIDOutput,-RobotMap.drivePIDOutput-RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    	}
    	else if(NavSensorSubsystem.ahrs.getFusedHeading()<(((initAngle-RobotMap.COMPASS_TOLERANCE)<0)?(initAngle-RobotMap.COMPASS_TOLERANCE)%360+360:initAngle-RobotMap.COMPASS_TOLERANCE)){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.drivePIDOutput-RobotMap.AUTON_DRIVE_SPEED,-RobotMap.drivePIDOutput, RobotMap.SENSITIVITY);
    	}
    	else{
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.drivePIDOutput, -RobotMap.drivePIDOutput, RobotMap.SENSITIVITY);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    	SmartDashboard.putNumber("drivePIDOutput", RobotMap.drivePIDOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (DrivetrainSubsystem.instance.onTarget());
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	DrivetrainSubsystem.instance.disable();
    	DrivetrainSubsystem.instance.tankDrive(0, 0, RobotMap.SENSITIVITY);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
