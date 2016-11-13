package org.usfirst.frc.team503.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class RobotHardwareKitBot extends RobotHardware{
	//Define objects for motors on this bot 
	public CANTalon frontLeftMotor;
	public CANTalon frontRightMotor;
	public CANTalon backLeftMotor;
	public CANTalon backRightMotor;
	
	@Override
	public void initialize()
	{
		//sample code to be put into drive train subsystem 
		//Robot.bot.getMotorObj(1).set(-leftSpeed);   // front Left 
		//Robot.bot.getMotorObj(2).set(rightSpeed);  // front Right 
		//Robot.bot.getMotorObj(3).set(-leftSpeed);   // back Left 
		//Robot.bot.getMotorObj(4).set(rightSpeed);  // back Right 
		
		//Assign motor variables to ports on robot 
		frontRightMotor = new CANTalon(2);
		backRightMotor = new CANTalon(1);
		frontLeftMotor = new CANTalon(0);	
		backLeftMotor = new CANTalon(3);
		
	}
	
	@Override
	public CANTalon getCANTalonObj(int MotorID) 
	{
	if(MotorID == 1) {
			return frontLeftMotor;
		} else if(MotorID == 2) {
			return frontRightMotor; 
		} else if(MotorID == 3) {
			return backLeftMotor; 
		} else {
			return backRightMotor; 
		}
	} // End getMotorObject
		
	@Override
	public boolean usesNavX()
	{
		return true;
	}
	
	@Override
	public boolean usesCamera()
	{
		return true; 
	}
	
	@Override
	public boolean usesDriveCamera()
	{
		return true;
	}
	
	@Override
	public String getName()
	{
		return "KitBot";
	}
}
