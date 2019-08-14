package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;

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
    	initDegrees = NavSensorSubsystem.ahrs.getFusedHeading();
    	if(degrees<0){
        	turnDegrees= (initDegrees+degrees+RobotMap.COMPASS_DRIVE_TOLERANCE);
    	}
    	else if(degrees>0){
    		turnDegrees= (initDegrees+degrees-RobotMap.COMPASS_DRIVE_TOLERANCE);
    	}
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
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED,RobotMap.AUTON_DRIVE_SPEED, false);
       	}
    	else if(rightDir==false){
    		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	else{
    		NewDrivetrainSubsystem.instance.tankDrive(0, 0, false);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((NavSensorSubsystem.ahrs.getFusedHeading() <= (turnDegrees+RobotMap.COMPASS_TOLERANCE)) && (NavSensorSubsystem.ahrs.getFusedHeading() >= (turnDegrees-RobotMap.COMPASS_TOLERANCE)));
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
