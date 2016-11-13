package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TeleopClimbCommand extends Command {

    public TeleopClimbCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(OI.getOperatorRightTrigger() > .5){
    			if(RobotMap.armWinchMode == false){
    				ArmSubsystem.instance.winchMode();
    			}
    			ArmSubsystem.instance.extenderRetract();
            	ArmSubsystem.instance.setArmWinchMotor(-RobotMap.CLIMB_SPEED);
    		}
    		else{
    			ArmSubsystem.instance.extenderOff();
            	ArmSubsystem.instance.setArmWinchMotor(0);
    		}
        
        
        	
        	
        }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.extenderOff();
    	ArmSubsystem.instance.setArmWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
