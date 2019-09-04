package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;
// import frc.robot.subsystems.VisionProcessor;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.sun.jdi.event.BreakpointEvent;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimCommand extends Command {
	double degrees;
	double tolerance;
	double lowTolerance;
	double midTolerance;
	boolean direction;
	static NeutralMode brake1;
	
    public AimCommand() { 	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	// this.degrees = -VisionProcessor.instance.getAngle();
    	if(Math.abs(this.degrees)< 1){
    		this.degrees =0;
    	}
    	tolerance = RobotMap.COMPASS_TOLERANCE;
    	midTolerance = RobotMap.COMPASS_MID_TOLERANCE;
    	lowTolerance = RobotMap.COMPASS_LOW_TOLERANCE;
    	
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
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, RobotMap.AUTON_DRIVE_SPEED, false);    		
    	}
    	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + tolerance){
    		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	else{
    		if(NavSensorSubsystem.ahrs.getYaw() < degrees - midTolerance){
        		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_MID_DRIVE_SPEED, RobotMap.AUTON_MID_DRIVE_SPEED, false);    		
        	}
        	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + midTolerance){
        		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_MID_DRIVE_SPEED, -RobotMap.AUTON_MID_DRIVE_SPEED, false);
        	}
        	else{ 
        		if(NavSensorSubsystem.ahrs.getYaw() < degrees - lowTolerance){
            		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_MID_DRIVE_SPEED, RobotMap.AUTON_MID_DRIVE_SPEED, false);    		
            	}
            	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + lowTolerance){
            		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_MID_DRIVE_SPEED, -RobotMap.AUTON_MID_DRIVE_SPEED, false);
            	}
            	else{
            		end();
            	}
        	}
        	
    		//DrivetrainSubsystem.instance.tankDrive(0,0, RobotMap.SENSITIVITY);
    	}
    	NavSensorSubsystem.instance.sendDashboardData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((NavSensorSubsystem.ahrs.getYaw() <= (degrees + lowTolerance)) && (NavSensorSubsystem.ahrs.getYaw() >= (degrees-lowTolerance)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	NewDrivetrainSubsystem.instance.tankDrive(0, 0, true);
    	NewDrivetrainSubsystem.instance.motorBrake(NeutralMode.Brake);
    	//NewDrivetrainSubsystem.instance.tankDrive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
