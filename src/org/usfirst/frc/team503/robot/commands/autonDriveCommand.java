package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.VisionProcessor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class autonDriveCommand extends Command {
	double angle;
	Timer timer;

	public autonDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		angle = VisionProcessor.instance.getAngle();
		SmartDashboard.putNumber("auton drive", angle);
		if (VisionProcessor.instance.getCameraDistance() > 7) {
			NewDrivetrainSubsystem.instance.arcadeDrive(-.60, angle * .045, false); // .025 .030
		} else if (Math.abs(NavSensorSubsystem.ahrs.getYaw()) > 3) {
			if (NavSensorSubsystem.ahrs.getYaw() < -3) {
				NewDrivetrainSubsystem.instance.arcadeDrive(0, .3, false);

			} else if (NavSensorSubsystem.ahrs.getYaw() > 3) {
				NewDrivetrainSubsystem.instance.arcadeDrive(0, -.3, false);
			}

		}

		// NewDrivetrainSubsystem.instance.arcadeDrive(-.60,angle *.037, false); //.04

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		/*
		 * SmartDashboard.putNumber("**ANGLE TO TARGET",
		 * VisionProcessor.instance.getAngle());
		 * SmartDashboard.putNumber("**Auton Distance to Target",VisionProcessor.
		 * instance.getCameraDistance());
		 */
		return (VisionProcessor.instance.getCameraDistance() < 7.0 && Math.abs(NavSensorSubsystem.ahrs.getYaw()) < 3)
				|| timer.get() > 3.5;// 10
		// return false;

	}

	// Called once after isFinished returns true
	protected void end() {
		NewDrivetrainSubsystem.instance.arcadeDrive(0, 0, false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
