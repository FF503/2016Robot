package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {
	Timer timer;

    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	if(RobotMap.deflectorMode == false){
    		end();
    	}
    	timer.start();
    	//ShooterSubsystem.instance.setIndexer(true);
    	ShooterSubsystem.instance.runShooter(true);
    	Timer.delay(RobotMap.SHOOT_TIME);
    	ShooterSubsystem.instance.stopShooter();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > RobotMap.SHOOT_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//why isnt this getting called :(
    	//ShooterSubsystem.instance.setIndexer(false);
    	//ShooterSubsystem.instance.stopShooter();    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
