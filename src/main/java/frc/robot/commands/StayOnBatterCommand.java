package frc.robot.commands;

import frc.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StayOnBatterCommand extends Command {

    public StayOnBatterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	NewDrivetrainSubsystem.instance.tankDrive(-.15, -.15, false);//(-.15, -.15)
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
