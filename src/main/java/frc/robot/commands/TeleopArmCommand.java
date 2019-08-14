package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TeleopArmCommand extends Command {

    public TeleopArmCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Arm powered", "true");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(RobotMap.armWinchMode == false){
        	if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
        		if(OI.getArmMotorPower()>0){
        			ArmSubsystem.instance.setArmWinchMotor(0);
        		}
        		else {
        			ArmSubsystem.instance.setArmWinchMotor(OI.getArmMotorPower());
        		}
        	}
        	//else if(ArmSubsystem.instance.getArmUpperLimitSwitch()){
        	//	if(OI.getArmMotorPower()<0){
        	//		ArmSubsystem.instance.setArmWinchMotor(0);
        	//	}
        	//	else{
        	//		ArmSubsystem.instance.setArmWinchMotor(OI.getArmMotorPower());
        	//	}
        	
        	else{
        		ArmSubsystem.instance.setArmWinchMotor(OI.getArmMotorPower());
        	}
        	SmartDashboard.putNumber("arm power", OI.getArmMotorPower());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.extenderOff();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
