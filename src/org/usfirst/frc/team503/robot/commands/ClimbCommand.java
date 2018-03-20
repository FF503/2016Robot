package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbCommand extends Command {
	DigitalInput limitSwitch;

	public ClimbCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		ArmSubsystem.instance.winchMode();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (ArmSubsystem.instance.getExtenderLowerLimitSwitch()) {
			ArmSubsystem.instance.setArmWinchMotor(0);
			ArmSubsystem.instance.extenderOff();
			/*
			 * SmartDashboard.putString("Winch Ended", "True");
			 * SmartDashboard.putBoolean("Winch Limit",
			 * ArmSubsystem.instance.getExtenderLowerLimitSwitch());
			 */
			end();
		} else {
			ArmSubsystem.instance.setArmWinchMotor(RobotMap.CLIMB_SPEED);
			ArmSubsystem.instance.extenderRetract();
			/*
			 * SmartDashboard.putString("Winch Ended", "False");
			 * SmartDashboard.putBoolean("Winch Limit",
			 * ArmSubsystem.instance.getExtenderLowerLimitSwitch());
			 */
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.extenderPosition = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
