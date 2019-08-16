package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ClimberExtenderOutCommand extends Command {
	DigitalInput limitSwitch; 
	
    public ClimberExtenderOutCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	ArmSubsystem.instance.armMode();
    	Timer.delay(.25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(ArmSubsystem.instance.getExtenderUpperLimitSwitch()) {
        	ArmSubsystem.instance.extenderOff();
        	SmartDashboard.putString("extender", "stopped");
    			 
        	end();
        } else {
        	SmartDashboard.putString("extender", "started");
        	ArmSubsystem.instance.extenderExtend();
        		 
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // return true;
    	return false; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.extenderPosition = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}