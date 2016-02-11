package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBallCommand extends Command {

    public IntakeBallCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.armWinchMode == true){
    		end();
    	}
    	else if(RobotMap.deflectorMode == false){
    		ShooterSubsystem.instance.raiseDeflector();
    	}
    	IntakeSubsystem.instance.intakeIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//IntakeSubsystem.instance.in();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !OI.getIntakeButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	IntakeSubsystem.instance.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
