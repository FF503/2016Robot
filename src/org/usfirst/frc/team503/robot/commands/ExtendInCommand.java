package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendInCommand extends Command {

	public ExtendInCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ArmSubsystem.instance.winchMode();

		if (ArmSubsystem.instance.getExtenderLowerLimitSwitch()) {
			end();
		} else if (OI.getClimbButton()) {
			ArmSubsystem.instance.extenderRetract();
			ArmSubsystem.instance.setArmWinchMotor(-RobotMap.CLIMB_SPEED); // needs to be positive

		} else if (!OI.getClimbButton()) {
			ArmSubsystem.instance.setArmWinchMotor(0);
			ArmSubsystem.instance.extenderOff();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		ArmSubsystem.instance.extenderRetract();
		ArmSubsystem.instance.setArmWinchMotor(-RobotMap.CLIMB_SPEED); // needs to be positive

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (ArmSubsystem.instance.getExtenderLowerLimitSwitch()) {
			end();
		}
		return !OI.getClimbButton();
	}

	// Called once after isFinished returns true
	protected void end() {
		ArmSubsystem.instance.setArmWinchMotor(0);
		ArmSubsystem.instance.extenderOff();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
