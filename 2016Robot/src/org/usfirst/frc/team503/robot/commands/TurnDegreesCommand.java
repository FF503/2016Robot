package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegreesCommand extends Command {
	private double degrees;
	private double initDegrees;
	private double turnDegrees;
	private boolean rightDir;
    public TurnDegreesCommand(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initDegrees = NavSensorSubsystem.ahrs.getYaw();
    	turnDegrees= (initDegrees+degrees);
    	if(turnDegrees>initDegrees){
    		rightDir=true;
    	}
    	else if(turnDegrees<initDegrees){
    		rightDir=false;
    	}
    	
    	if(turnDegrees>=360){
        	turnDegrees = turnDegrees%360;
    	}
    	else if(turnDegrees<=0){
    		turnDegrees=turnDegrees%360 + 360;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(rightDir==true){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED,RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
       	}
    	else if(rightDir==false){
    		DrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    	}
    	else{
    		DrivetrainSubsystem.instance.tankDrive(0, 0, RobotMap.SENSITIVITY);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((NavSensorSubsystem.ahrs.getFusedHeading() <= (turnDegrees+RobotMap.COMPASS_TOLERANCE)) && (NavSensorSubsystem.ahrs.getFusedHeading() >= (turnDegrees-RobotMap.COMPASS_TOLERANCE)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	DrivetrainSubsystem.instance.tankDrive(0, 0, RobotMap.SENSITIVITY);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
