package auton;

import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonStopShooterCommand extends Command {

    public AutonStopShooterCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(ShooterSubsystem.instance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//ShooterSubsystem.instance.runShooter(false);
    	ShooterSubsystem.instance.stopShooter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ShooterSubsystem.instance.runShooter(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
