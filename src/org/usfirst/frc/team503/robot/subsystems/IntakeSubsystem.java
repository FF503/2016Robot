package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.Robot;
import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	// intake Spike relay, //Proximity Switch DIO 1

	private IntakeSubsystem() {
	}

	public static IntakeSubsystem instance = new IntakeSubsystem();

	public void intakeIn() {
		Robot.bot.getSparkObj(1).set(1);
		RobotMap.intakeRunning = true;
	}

	public void intakeOut() {
		Robot.bot.getSparkObj(1).set(-1);
		RobotMap.intakeRunning = true;
	}

	public void intakeStop() {
		Robot.bot.getSparkObj(1).set(0);
		RobotMap.intakeRunning = false;
	}

	public boolean intakeHasBall() {
		return Robot.bot.getDigitalInputObj(1).get();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
