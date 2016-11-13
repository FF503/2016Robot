package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PowerArmCommand extends Command {
	double power;
    public PowerArmCommand(double power) {
    	this.power = power;
    	requires(ArmSubsystem.instance);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ArmSubsystem.instance.setArmWinchMotor(power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ArmSubsystem.instance.setArmWinchMotor(power);
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
