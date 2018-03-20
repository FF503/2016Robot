package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.TurnPIDSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autonTurnToCompass extends Command {
	double angle;
	Timer timer;

	public autonTurnToCompass() {
		double d = RobotMap.OriginalCompassHeading;
		double c = NavSensorSubsystem.ahrs.getCompassHeading();
		if ((d - c) > 180) {
			c = c + 360;
		}
		if ((d - c) < -180) {
			d = d + 360;
		}
		angle = d - c;
		/*
		 * if((d-c) > 180){ angle = d-(c+360); } else if((d-c)>0){ angle = d-c; } else
		 * if((d-c) < -180){ angle = d+360-c; } else{ angle = c-d; }
		 */

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.start();
		NavSensorSubsystem.ahrs.reset();
		TurnPIDSubsystem.instance.setSetpoint(angle);
		SmartDashboard.putNumber("Auton ANGLE", angle);
		TurnPIDSubsystem.instance.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putString("AutonTurnPIDCommand Ended", "No");
		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.autonTurnPIDOutput, RobotMap.autonTurnPIDOutput, false);
		SmartDashboard.putNumber("AutonTurnPIDOutPut", RobotMap.autonTurnPIDOutput);
		// NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.turnPIDOutput,
		// RobotMap.turnPIDOutput, false);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// SmartDashboard.putBoolean("Auton Turn On Target",
		// Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) <
		// RobotMap.AUTON_TURN_PID_TOLERANCE);
		// return Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) <
		// RobotMap.TURN_PID_TOLERANCE;
		SmartDashboard.putBoolean("Auton Turn On Target", TurnPIDSubsystem.instance.onTarget());
		return TurnPIDSubsystem.instance.onTarget();
		// return timer.get() > 2; // KRM we need to put this back if we cant turn the
		// pid command
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("AutonTurnPIDCommand Ended", "Yes");
		NewDrivetrainSubsystem.instance.tankDrive(0, 0, true);
		TurnPIDSubsystem.instance.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
