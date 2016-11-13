package auton;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonShootCommand extends Command {

    public AutonShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(ShooterSubsystem.instance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ShooterSubsystem.instance.raiseDeflector();
    	//ShooterSubsystem.instance.setShooter(RobotMap.SHOOTER_RPM);
    	ShooterSubsystem.instance.runShooter(true);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
