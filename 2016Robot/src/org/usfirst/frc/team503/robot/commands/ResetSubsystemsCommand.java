package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetSubsystemsCommand extends Command {

    public ResetSubsystemsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DrivetrainSubsystem.instance.setMotorOutputs(0, 0, false);
    	ArmSubsystem.instance.setArmMotor(0);
    	ShooterSubsystem.instance.lowerDeflector();
    	ShooterSubsystem.instance.stopShooter();
    	ShooterSubsystem.instance.setIndexer(false);
    	IntakeSubsystem.instance.intakeStop();
    	DrivetrainSubsystem.driveEncoder.reset();
    	NavSensorSubsystem.ahrs.reset();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
