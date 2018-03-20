package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autonSturnCommand extends Command {
	double angle;
	Timer timer;
	long click;
	long totalLimit;
	double speed;
	long turn1Limit;
	long straightlimit;

	public autonSturnCommand(double inAngle, double inSpeed, long inLimit, long inTurnLen, long inStraightLen) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.angle = inAngle;
		this.speed = inSpeed;
		this.totalLimit = inLimit;
		this.turn1Limit = inTurnLen;
		this.straightlimit = inStraightLen + inTurnLen;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.start();
		click = 0;
		// angle = -45;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double workangle;
		workangle = angle * .025; // was .005
		if (click < turn1Limit) {
			workangle = workangle;
		} else if (click > turn1Limit && click < straightlimit) {
			workangle = 0;
		} else {
			workangle = workangle * -1;
		}
		/*
		 * SmartDashboard.putNumber("Sturn turn1 limit", turn1Limit);
		 * SmartDashboard.putNumber("Sturn straight limit", straightlimit);
		 * SmartDashboard.putNumber("Sturn angle", workangle);
		 */
		NewDrivetrainSubsystem.instance.arcadeDrive(-speed, workangle, false); // 33
		click = click + 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (click < totalLimit) {
			return false;
		} else
			return true;
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
