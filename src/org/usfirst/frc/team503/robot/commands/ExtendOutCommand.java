package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team503.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class ExtendOutCommand extends Command {

    public ExtendOutCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	ArmSubsystem.instance.armMode();
    	if(ArmSubsystem.instance.getExtenderUpperLimitSwitch()){
    		end();
    	}
    	else if(OI.getExtenderButton()){
        	ArmSubsystem.instance.extenderExtend();
        }
        else if(!OI.getExtenderButton()){
        	ArmSubsystem.instance.extenderOff();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	ArmSubsystem.instance.extenderExtend(); 	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(ArmSubsystem.instance.getExtenderUpperLimitSwitch()){
    		end();
    	}
        return !OI.getExtenderButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.extenderOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
