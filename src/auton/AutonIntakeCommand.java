package auton;

import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonIntakeCommand extends Command {
	Timer timer;

	public AutonIntakeCommand() {

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		// requires(IntakeSubsystem.instance);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
		timer.start();
		IntakeSubsystem.instance.intakeIn();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		IntakeSubsystem.instance.intakeIn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timer.get() > 2) {
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
