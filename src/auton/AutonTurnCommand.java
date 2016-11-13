package auton;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTurnCommand extends Command {
	double degrees;
	double tolerance;
	double lowTolerance;
	boolean direction;
    public AutonTurnCommand(double degrees) {
    	this.degrees = degrees;
    	tolerance = RobotMap.COMPASS_TOLERANCE;
    	lowTolerance = RobotMap.COMPASS_LOW_TOLERANCE;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	NavSensorSubsystem.ahrs.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("YAWWWWWWWWWWWWWWW", NavSensorSubsystem.ahrs.getYaw());
    	if(NavSensorSubsystem.ahrs.getYaw() < degrees - tolerance){
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_DRIVE_SPEED, RobotMap.AUTON_DRIVE_SPEED, false);    		
    	}
    	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + tolerance){
    		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_DRIVE_SPEED, -RobotMap.AUTON_DRIVE_SPEED, false);
    	}
    	else{
    		if(NavSensorSubsystem.ahrs.getYaw() < degrees - lowTolerance){
        		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.AUTON_LOW_DRIVE_SPEED, RobotMap.AUTON_LOW_DRIVE_SPEED, false);    		
        	}
        	else if(NavSensorSubsystem.ahrs.getYaw() > degrees + lowTolerance){
        		NewDrivetrainSubsystem.instance.tankDrive(RobotMap.AUTON_LOW_DRIVE_SPEED, -RobotMap.AUTON_LOW_DRIVE_SPEED, false);
        	}
        	
        		end();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((NavSensorSubsystem.ahrs.getYaw() <= (degrees + tolerance)) && (NavSensorSubsystem.ahrs.getYaw() >= (degrees-tolerance)));
    }

    // Called once after isFinished returns true
    protected void end() {
    	NewDrivetrainSubsystem.instance.tankDrive(0,0, false);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
