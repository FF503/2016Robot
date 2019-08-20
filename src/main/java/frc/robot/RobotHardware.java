package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class RobotHardware {
	
	public abstract void initialize();
	public abstract String getName();
	//Define objects for motors on this bot 
	
	public TalonSRX dummyMotor;
	public Spark dummySpark;
	public DigitalInput dummyIO;
	public RobotHardware bot;
	private TalonSRX rightShootMotor;
	private TalonSRX leftShootMotor;
	

	public void logSmartDashboard()
	{
		SmartDashboard.putString("Current Robot", Robot.bot.getName());
	}
	
	public TalonSRX getTalonSRXObj(int MotorID) 
	{
	    return dummyMotor;  	
	}
	
	public TalonSRX getRightShooterObj() 
	{
	    return dummyMotor;  	
	}
	public TalonSRX getArmWinchMotor(){
		return dummyMotor;
	}
	
	public TalonSRX getLeftShooterObj() 
	{
	    return dummyMotor;  	
	}
	
	public Spark getSparkObj(int SparkID){
		return dummySpark;
	}
	public TalonSRX getIndexerObj(){
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
