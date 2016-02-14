package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	DrivetrainSubsystem.instance.setSetpoint(inches);    //this is the drivetrain controller 
    	SmartDashboard.putNumber("Setpoint=", inches);
    	DrivetrainSubsystem.driveEncoder.reset();
    	//DrivetrainSubsystem.setDistanceSetpoint(inches);          //This is the PID Controller 
    	
    	initAngle=NavSensorSubsystem.ahrs.getFusedHeading();
    	DrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, RobotMap.SENSITIVITY);
    	SmartDashboard.putString("At End", "No");
    	SmartDashboard.putBoolean("On target?", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("pidOutput=", RobotMap.drivePIDOutput);
    	if(NavSensorSubsystem.ahrs.getFusedHeading()>(initAngle+RobotMap.COMPASS_TOLERANCE)%360){
    		DrivetrainSubsystem.instance.tankDrive(0,-RobotMap.drivePIDOutput, RobotMap.SENSITIVITY);
    	}
    	else if(NavSensorSubsystem.ahrs.getFusedHeading()<(((initAngle-RobotMap.COMPASS_TOLERANCE)<0)?(initAngle-RobotMap.COMPASS_TOLERANCE)%360+360:initAngle-RobotMap.COMPASS_TOLERANCE)){
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.drivePIDOutput, 0, RobotMap.SENSITIVITY);
    	}
    	else{
    		DrivetrainSubsystem.instance.tankDrive(-RobotMap.drivePIDOutput, -RobotMap.drivePIDOutput, RobotMap.SENSITIVITY);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean wearedone = false; 
    	// if actual distance traveled on encoders > than set point we have reached destination  
    	if (DrivetrainSubsystem.driveEncoder.getDistance() >= DrivetrainSubsystem.instance.getSetpoint()) 
    	{    
    	    wearedone = true; 
    	} else {
    		wearedone = false; 
    	}
    	SmartDashboard.putBoolean("On target?", wearedone);
//    	return (DrivetrainSubsystem.pidOnTarget());                //This is the PID controller  
//     	return (DrivetrainSubsystem.instance.onTarget());     //This is the drivetrain controller
    	return wearedone; 

    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("At End", "Yes");
    	DrivetrainSubsystem.instance.tankDrive(0, 0, RobotMap.SENSITIVITY);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}