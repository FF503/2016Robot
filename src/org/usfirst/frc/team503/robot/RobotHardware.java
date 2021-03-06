package org.usfirst.frc.team503.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class RobotHardware {
	
	public abstract void initialize();
	public abstract String getName();
	//Define objects for motors on this bot 
	
	public CANTalon dummyMotor;
	public Spark dummySpark;
	public DigitalInput dummyIO;
	public RobotHardware bot;
	private CANTalon rightShootMotor;
	private CANTalon leftShootMotor;
	

	public void logSmartDashboard()
	{
		SmartDashboard.putString("Current Robot", Robot.bot.getName());
	}
	
	public CANTalon getCANTalonObj(int MotorID) 
	{
	    return dummyMotor;  	
	}
	
	public CANTalon getRightShooterObj() 
	{
	    return dummyMotor;  	
	}
	public CANTalon getArmWinchMotor(){
		return dummyMotor;
	}
	
	public CANTalon getLeftShooterObj() 
	{
	    return dummyMotor;  	
	}
	
	public Spark getSparkObj(int SparkID){
		return dummySpark;
	}
	public CANTalon getIndexerObj(){
		return dummyMotor;
	}
	
	public DigitalInput getDigitalInputObj (int DigitalID){
		return dummyIO;
	}

	public boolean usesNavX()
	{
		return false;
	}
	
	public boolean usesCamera()
	{
		return false;
	}
	
	public boolean usesDriveCamera()
	{
		return false;
	}

	public RobotHardware()
	{
		bot = this;
	}
	
	public RobotHardware getBot()
	{
		return bot;
	}
	
	public RobotHardware setBot(RobotHardware r)
	{
		return bot = r;
	}
}
