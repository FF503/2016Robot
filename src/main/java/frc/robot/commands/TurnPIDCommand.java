package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;
import frc.robot.subsystems.VisionProcessor;
import frc.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnPIDCommand extends Command {
	double angle;
	Timer timer;
    public TurnPIDCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.angle = VisionProcessor.instance.getAngle();
    	timer = new Timer();
    	timer.start();
    	NavSensorSubsystem.ahrs.reset();
    	VisionSubsystem.instance.setSetpoint(angle);
    	
    	
    	VisionSubsystem.instance.setSetpoint(angle);
    	SmartDashboard.putNumber("ANGLE", angle);
    	VisionSubsystem.instance.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("TurnPIDCommand Ended", "No");
    	NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.visionTurnPIDOutput, RobotMap.visionTurnPIDOutput, false);
    	SmartDashboard.putNumber("turnPIDOutPut", RobotMap.visionTurnPIDOutput);
    	//NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.turnPIDOutput, RobotMap.turnPIDOutput, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("Turn On Target", Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) < RobotMap.TURN_PID_TOLERANCE);
        //return Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) < RobotMap.TURN_PID_TOLERANCE;
    	return timer.get() > 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("TurnPIDCommand Ended", "Yes");
    	NewDrivetrainSubsystem.instance.tankDrive(0, 0, true);
    	VisionSubsystem.instance.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
