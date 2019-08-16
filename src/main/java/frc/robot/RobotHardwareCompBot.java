package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class RobotHardwareCompBot extends RobotHardware{
	//Define objects for motors on this bot 
	public static TalonSRX frontLeftMotor;
	public static TalonSRX frontRightMotor;
	public static TalonSRX backLeftMotor;
	public static TalonSRX backRightMotor;
	private static TalonSRX rightShootMotor;
	private static TalonSRX leftShootMotor;
	private static TalonSRX indexerMotor;
	private static TalonSRX armWinchMotor;
	public static Spark indexer;
	public static Spark intake;
	public static DigitalInput intakeProximitySwitch;
	
	@Override
	public void initialize()
	{
		//Assign motor variables to ports on robot 
		frontLeftMotor = new TalonSRX(0);    //why right drive  was 3 
		frontRightMotor = new TalonSRX(4);   //why left drive ?? was 1 
		backLeftMotor = new TalonSRX(5);     //why right drive ? was 6 
		backRightMotor = new TalonSRX(6);    //why left drive was 4   
		armWinchMotor = new TalonSRX(1);
		intake = new Spark(1);
		
		intakeProximitySwitch = new DigitalInput(0);//was 0
		
		indexerMotor = new TalonSRX(3);
		rightShootMotor = new TalonSRX(7); //has encoder
		leftShootMotor = new TalonSRX(2); 
	}
	
	@Override
	public TalonSRX getCANTalonObj(int MotorID) 
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
	public TalonSRX getIndexerObj(){
		return indexerMotor;
	}
	public TalonSRX getRightShooterObj() 
	{
	    return rightShootMotor;  	
	}
	
	public TalonSRX getLeftShooterObj() 
	{
	    return leftShootMotor;  	
	}
	public TalonSRX getArmWinchMotor(){
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
