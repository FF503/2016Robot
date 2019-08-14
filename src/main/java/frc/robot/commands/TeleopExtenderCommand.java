package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TeleopExtenderCommand extends Command {

    public TeleopExtenderCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if(OI.getOperatorLeftTrigger() > .5){
  
    			if(RobotMap.armWinchMode == true){
    				ArmSubsystem.instance.armMode();
    			}
    			ArmSubsystem.instance.extenderExtend();
    			SmartDashboard.getBoolean("ExtenderRUnning", true);
            	
    		}
    		else{
    			SmartDashboard.putBoolean("ExtenderRUnning", false);
    			ArmSubsystem.instance.extenderOff();
            	
    		}
        
        
        	
        	
        }

    protected boolean isFinished() {
        return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ArmSubsystem.instance.extenderOff();
    	ArmSubsystem.instance.setArmWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}