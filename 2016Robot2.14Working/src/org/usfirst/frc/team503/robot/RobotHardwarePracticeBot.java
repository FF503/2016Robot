package org.usfirst.frc.team503.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;

public class RobotHardwarePracticeBot extends RobotHardware{
	//Define objects for motors on this bot 
	public static CANTalon frontLeftMotor;
	public static CANTalon frontRightMotor;
	public static CANTalon backLeftMotor;
	public static CANTalon backRightMotor;
	
	public static Spark indexer;
	public static Spark intake;
	public static DigitalInput intakeProximitySwitch;
	
	@Override
	public void initialize()
	{
		//Assign motor variables to ports on robot 
		frontLeftMotor = new CANTalon(3);
		frontRightMotor = new CANTalon(1);
		backLeftMotor = new CANTalon(6);
		backRightMotor = new CANTalon(4);
		
		intake = new Spark(1);
		
		intakeProximitySwitch = new DigitalInput(0);
	}
	
	@Override
	public CANTalon getCANTalonObj(int MotorID) 
	{
		if(MotorID == 1) {
			return frontLeftMotor;
		} 
		else if(MotorID == 2) {
			return frontRightMotor; 
		} 
		else if(MotorID == 3) {
			return backLeftMotor; 
		} 
		else{
			return backRightMotor; 
		}
	} // End getMotorObject 
	
	@Override
	public Spark getSparkObj (int SparkID){
		if(SparkID == 1){
			return intake;
		}
		else{
			return indexer;
		}
	}
	
	@Override
	public DigitalInput getDigitalInputObj (int DigitalID){
		if(DigitalID == 1){
			return intakeProximitySwitch;
		}
		else{
			return intakeProximitySwitch;
		}
	}
	
	@Override
	public boolean usesNavX()
	{
		return false;
	}
	
	@Override
	public boolean usesCamera()
	{
		return false;
	}
	
	@Override
	public boolean usesDriveCamera()
	{
		return false;
	}
	
	@Override
	public String getName()
	{
		return "Programming Bot";
	}

}