package frc.robot.subsystems;

import frc.robot.Robot;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.FeedbackDeviceStatus;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterRPMSubsystem extends PIDSubsystem {
	//roller: 2 CANTalons; encoder on gearbox
	//indexer (also roller): Spike relay ;
	//hood/deflector: Pneumatic
    // encoder (4,5) 
	//Proximity switch 1
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static int deflectorMode = 0; // 0 down, 1 up
	public static TalonSRX rightShootMotor;
	public static TalonSRX leftShootMotor;
	public static TalonSRX indexerMotor; 
	
	public static FeedbackDeviceStatus status;
	//private static DigitalInput indexerProximitySwitch;
	public static Solenoid deflectorShifter;
	public static Encoder shooterEncoder;
	
	public ShooterRPMSubsystem(){
		super("ShooterSubsystem", RobotMap.Shoot_kP, RobotMap.Shoot_kI, RobotMap.Shoot_kD);

		//rightShootMotor = new CANTalon(RobotMap.rightShootMotorPort);
		rightShootMotor = Robot.bot.getRightShooterObj();
		
		/*rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightShootMotor.setEncPosition(0);
		rightShootMotor.setPosition(0);*/
		
		//status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);
		//leftShootMotor = new CANTalon(RobotMap.leftShootMotorPort);
		leftShootMotor = Robot.bot.getLeftShooterObj();
		rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightShootMotor.setF(0);
		rightShootMotor.setP(0);
		rightShootMotor.setI(0);
		rightShootMotor.setD(0);
		//rightShootMotor.changeControlMode(TalonControlMode.Speed);
		//leftShootMotor.changeControlMode(TalonControlMode.Speed);
		status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);
		/*rightShootMotor.changeControlMode(TalonControlMode.Speed);
		leftShootMotor.changeControlMode(TalonControlMode.Speed);
		leftShootMotor.setF(.021);
		rightShootMotor.setF(.021);*/
		
	
		//rightShootMotor.configEncoderCodesPerRev(1);
		//shooterEncoder =  new Encoder(0, 1, false, EncodingType.k4X);
		//shooterEncoder.setDistancePerPulse(1.0/2048);
		
		indexerMotor = new CANTalon(RobotMap.indexerMotorPort);
		//indexerProximitySwitch = new DigitalInput(RobotMap.indexerProximitySwitchPort);
		deflectorShifter = new Solenoid(1);
	}

	public static ShooterRPMSubsystem instance = new ShooterRPMSubsystem();

	

    
    public void raiseDeflector(){
    	deflectorShifter.set(true);
    	RobotMap.deflectorUp = true;  	
    }
    public void lowerDeflector(){
    	deflectorShifter.set(false);
    	RobotMap.deflectorUp = false;	
    }
    
    public void setIndexer(boolean run) {
      	if(run){
    		SmartDashboard.putString("Indexer Running", "true");
    		indexerMotor.set(RobotMap.SHOOTER_SPEED);    
    		RobotMap.indexerRunning = true;
    	}
    	else{
    		SmartDashboard.putString("Indexer Running", "false");
    		indexerMotor.set(0);
    		RobotMap.indexerRunning = false;
    	}
    }
    
    
    
/*    public boolean isBallIndexed(){
    	return indexerProximitySwitch.get();    	
    }*/
    
    public void setShooter(double speed){
    	rightShootMotor.changeControlMode(TalonControlMode.Speed);
    	leftShootMotor.changeControlMode(TalonControlMode.Speed);
    	leftShootMotor.set(speed);
    	rightShootMotor.set(speed);
    }
    
    public void runShooter(boolean run){
    	if(run){
    		SmartDashboard.putString("ShooterRunning", "true");
    		leftShootMotor.set(RobotMap.SHOOTER_SPEED);    
    		rightShootMotor.set(RobotMap.SHOOTER_SPEED);
    		RobotMap.shooterRunning = true;
    	}
    	else{
    		SmartDashboard.putString("ShooterRunning", "false");
    		leftShootMotor.set(0);
    		rightShootMotor.set(0);
    		RobotMap.shooterRunning = false;
    	}
    }
    
    public void stopShooter(){
    	leftShootMotor.set(0);
    	rightShootMotor.set(0);
    	RobotMap.shooterRunning = false;
    }
    
  
    public double getShootSpeedRight(){
    	return rightShootMotor.getEncVelocity()/6.0; //velocity is six times rpm
    	
    }
    public double getShootSpeedLeft(){
    	return leftShootMotor.getEncVelocity()/6.0;
    }
    
   
    public double getShootEncoderRight(){
    	
    	return rightShootMotor.getEncPosition(); // 4096 per revolution
    }
    public double getShootEncoderLeft(){
    	return leftShootMotor.getEncPosition();
    }
    
    public double getShooterEncoder(){
    	return shooterEncoder.getDistance();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	/*
    @Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getShootSpeedLeft();
	}
	@Override
	protected void usePIDOutput(double output) {
		RobotMap.shootPIDOutput = output;
		// TODO Auto-generated method stub
		
	}
	*/
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected void usePIDOutput(double arg0) {
		// TODO Auto-generated method stub
		
	}
}

