package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExpelBallCommand extends Command {

    public ExpelBallCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*if(RobotMap.armWinchMode == false){
    		ShooterSubsystem.instance.raiseDeflector();
    	}*/
    	if(OI.getExpelButton()){
    	IntakeSubsystem.instance.intakeOut();
    	}
    	else if(!OI.getExpelButton()){
    		IntakeSubsystem.instance.intakeStop();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !OI.getExpelButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	IntakeSubsystem.instance.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	IntakeSubsystem.instance.intakeStop();
    	
    }
}
