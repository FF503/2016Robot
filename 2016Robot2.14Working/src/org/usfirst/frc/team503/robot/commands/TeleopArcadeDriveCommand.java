package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopArcadeDriveCommand extends Command {

    public TeleopArcadeDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DrivetrainSubsystem.instance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DrivetrainSubsystem.instance.arcadeDrive(OI.getDriverLeftY(), OI.getDriverLeftX(), RobotMap.SENSITIVITY);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	DrivetrainSubsystem.instance.arcadeDrive(0, 0, RobotMap.SENSITIVITY);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}