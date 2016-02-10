package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbCommand extends Command {

    public ClimbCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(OI.climberIntakeMode == 1){
    		end();
    	}
    	else{
    		ArmSubsystem.instance.goToArmPosition(ArmPosition.TOP);
    		ArmSubsystem.instance.extenderExtend();
    		(new DriveStraightDistanceCommand(.5)).start();
    		Timer.delay(.25);
    		ArmSubsystem.instance.extenderStop();
    		Timer.delay(.25);
    		ArmSubsystem.instance.extenderRetract();
    		Timer.delay(.5);
    		ArmSubsystem.instance.extenderStop();
    		end();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.extenderRetract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
