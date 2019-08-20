package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RobotHardwareKitBot extends RobotHardware{
	//Define objects for motors on this bot 
	public TalonSRX frontLeftMotor;
	public TalonSRX frontRightMotor;
	public TalonSRX backLeftMotor;
	public TalonSRX backRightMotor;
	
	@Override
	public void initialize()
	{
		//sample code to be put into drive train subsystem 
		//Robot.bot.getMotorObj(1).set(-leftSpeed);   // front Left 
		//Robot.bot.getMotorObj(2).set(rightSpeed);  // front Right 
		//Robot.bot.getMotorObj(3).set(-leftSpeed);   // back Left 
		//Robot.bot.getMotorObj(4).set(rightSpeed);  // back Right 
		
		//Assign motor variables to ports on robot 
		frontRightMotor = new TalonSRX(2);
		backRightMotor = new TalonSRX(1);
		frontLeftMotor = new TalonSRX(0);	
		backLeftMotor = new TalonSRX(3);
		
	}
	
	@Override
	public TalonSRX getTalonSRXObj(int MotorID) 
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
