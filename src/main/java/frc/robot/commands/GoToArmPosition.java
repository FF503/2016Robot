package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.OI.Mode;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.ArmPosition;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GoToArmPosition extends Command {
	ArmPosition position;
	double setpoint;
	Timer timer;
	boolean enableTimer;
	boolean enableHold;

    public GoToArmPosition(ArmPosition position, boolean enableTimer, boolean enableHold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.position = position;
    	this.enableTimer = enableTimer;
    	this.enableHold = enableHold;
		timer = new Timer();
    	requires(ArmSubsystem.instance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		setpoint = position.position;
		timer.start();
		//SmartDashboard.putNumber("Arm Set", position.position);
		ArmSubsystem.instance.setSetpoint(setpoint);
		ArmSubsystem.instance.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		/*if(ArmSubsystem.instance.getArmAngleEncoderError(position)>RobotMap.ARM_PID_RANGE){
			if(ArmSubsystem.instance.getArmEncoderCounts()> position.position){
				ArmSubsystem.instance.armWinchMotor.set(RobotMap.ARM_SPEED/2);
			}
			else if(ArmSubsystem.instance.getArmEncoderCounts()< position.position){
				ArmSubsystem.instance.armWinchMotor.set(-RobotMap.ARM_SPEED);
			}
			if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
				//ArmSubsystem.armAngleEncoder.reset();
			}
		}*/
		
		//else{		
			
			ArmSubsystem.instance.setArmWinchMotor(RobotMap.armPIDOutput);
			
		//}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//SmartDashboard.putBoolean("Arm is OnTarget", ArmSubsystem.instance.ArmOnTarget(position));
    	if(enableHold){
    		return false;
    	}
    	else if(enableTimer){
            if(ArmSubsystem.instance.ArmOnTarget(position)){
            	return true;
            }
            else if(timer.get()>2){
            	return true;
            }
            else{
            	return false;
            }
    	}
    	else{
    		
    		 if(ArmSubsystem.instance.ArmOnTarget(position)){
    			ArmSubsystem.instance.disable();
             	RobotMap.armPIDOutput = 0;
             	ArmSubsystem.instance.setArmWinchMotor(0);
             	return true;
             }
    		 else{
    			 return false;
    		 }
    		
    	}

    }

    // Called once after isFinished returns true
    protected void end() {
		ArmSubsystem.instance.disable();
		ArmSubsystem.instance.armWinchMotor.set(ControlMode.PercentOutput,0);	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	ArmSubsystem.instance.disable();
		ArmSubsystem.instance.armWinchMotor.set(ControlMode.PercentOutput,0);	
    }
}
