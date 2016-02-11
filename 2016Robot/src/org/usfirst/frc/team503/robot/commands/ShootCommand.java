package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

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
    	if(RobotMap.deflectorMode == false){
    		end();
    	}
    	timer.start();
    	ShooterSubsystem.instance.setIndexer(true);
    	Timer.delay(.5);
    	ShooterSubsystem.instance.runShooter();
    	
    	
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
    	ShooterSubsystem.instance.setIndexer(false);
    	ShooterSubsystem.instance.stopShooter();    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
