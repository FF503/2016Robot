package auton;

import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonExpelCommand extends Command {
	Timer time;

	public AutonExpelCommand() {

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		// requires(IntakeSubsystem.instance);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		time = new Timer();
		time.start();
		IntakeSubsystem.instance.intakeOut();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		IntakeSubsystem.instance.intakeOut();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (time.get() > 3) {
			IntakeSubsystem.instance.intakeStop();
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		IntakeSubsystem.instance.intakeStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
