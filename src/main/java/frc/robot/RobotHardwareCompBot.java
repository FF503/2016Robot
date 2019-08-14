package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class RobotHardwareCompBot extends RobotHardware{
	//Define objects for motors on this bot 
	public static CANTalon frontLeftMotor;
	public static CANTalon frontRightMotor;
	public static CANTalon backLeftMotor;
	public static CANTalon backRightMotor;
	private static CANTalon rightShootMotor;
	private static CANTalon leftShootMotor;
	private static CANTalon indexerMotor;
	private static CANTalon armWinchMotor;
	public static Spark indexer;
	public static Spark intake;
	public static DigitalInput intakeProximitySwitch;
	
	@Override
	public void initialize()
	{
		//Assign motor variables to ports on robot 
		frontLeftMotor = new CANTalon(0);    //why right drive  was 3 
		frontRightMotor = new CANTalon(4);   //why left drive ?? was 1 
		backLeftMotor = new CANTalon(5);     //why right drive ? was 6 
		backRightMotor = new CANTalon(6);    //why left drive was 4   
		armWinchMotor = new CANTalon(1);
		intake = new Spark(1);
		
		intakeProximitySwitch = new DigitalInput(0);//was 0
		
		indexerMotor = new CANTalon(3);
		rightShootMotor = new CANTalon(7); //has encoder
		leftShootMotor = new CANTalon(2); 
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
			return null;
		}
	}
	
	@Override
	public DigitalInput getDigitalInputObj (int DigitalID){
		if(DigitalID == 1){
			return intakeProximitySwitch;
		}
		else{
			return null;
		}
	}
	public CANTalon getIndexerObj(){
		return indexerMotor;
	}
	public CANTalon getRightShooterObj() 
	{
	    return rightShootMotor;  	
	}
	
	public CANTalon getLeftShooterObj() 
	{
	    return leftShootMotor;  	
	}
	public CANTalon getArmWinchMotor(){
		return armWinchMotor;
	}
	
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
		return false;
	}
	
	@Override
	public String getName()
	{
		return "CompBot Bot";
	}

}
