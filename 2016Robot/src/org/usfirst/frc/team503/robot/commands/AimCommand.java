package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.VisionProcessor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimCommand extends Command {
	double degrees;
	double tolerance;
	boolean direction;
	
    public AimCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	this.degrees = VisionProcessor.instance.getAngle();
    	tolerance = RobotMap.COMPASS_TOLERANCE;
    	
    	
    	
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Degrees", degrees);
    	//if(NavSensorSubsystem.ahrs.getFusedHeading()> ((degrees +tolerance+  180) %360)){
    	//	DrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    	//}
    	//else if(NavSensorSubsystem.ahrs.getFusedHeading()< (((degrees-tolerance)<0)?
    	//		(degrees-tolerance)%360+360:(degrees-tolerance))){
    	if(NavSensorSubsystem.ahrs.getYaw() < degrees - tolerance){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);    		
    	}
    	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + tolerance){
    		DrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    	}
    	else{
    		//DrivetrainSubsystem.instance.tankDrive(0,0, RobotMap.SENSITIVITY);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((NavSensorSubsystem.ahrs.getYaw() <= (degrees + tolerance)) && (NavSensorSubsystem.ahrs.getYaw() >= (degrees-tolerance)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	DrivetrainSubsystem.instance.tankDrive(0, 0, RobotMap.SENSITIVITY);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
