package frc.robot.subsystems;



import java.io.IOException;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.FeedbackDeviceStatus;
import com.ctre.phoenix.motorcontrol.can.TalonSRX.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	//roller: 2 CANTalons; encoder on gearbox
	//indexer (also roller): Spike relay ;
	//hood/deflector: Pneumatic
    // encoder (4,5) 
	//Proximity switch 1
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static int deflectorMode = 0; // 0 down, 1 up
	private static CANTalon rightShootMotor;
	private static CANTalon leftShootMotor;
	public static CANTalon indexerMotor; 
	
	public static FeedbackDeviceStatus status;
	private static DigitalInput indexerLimitSwitch;
	private static Solenoid deflectorShifter;
	public static Encoder shooterEncoder;
	
	private ShooterSubsystem(){
		//super("ShooterSubsystem", RobotMap.Shoot_kP, RobotMap.Shoot_kI, RobotMap.Shoot_kD);
		//rightShootMotor = new CANTalon(RobotMap.rightShootMotorPort);
		rightShootMotor = Robot.bot.getRightShooterObj();
		//setOutputRange(.2,.8);
		//setInputRange(-5,11000);
		/*rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightShootMotor.setEncPosition(0);
		rightShootMotor.setPosition(0);*/
		
		//status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);
		//leftShootMotor = new CANTalon(RobotMap.leftShootMotorPort);
		leftShootMotor = Robot.bot.getLeftShooterObj();
		
		status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);
		rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightShootMotor.configEncoderCodesPerRev(1024); // was 360 
	    rightShootMotor.setProfile(0);
		rightShootMotor.setF(0.004657);  // was 0.029 , 0.004657
		rightShootMotor.setP(.24);     // was .3000 ,.2
		rightShootMotor.setI(.0000025);
		rightShootMotor.setD(0);
		
		
		//rightShootMotor.changeControlMode(TalonControlMode.Speed);
		//leftShootMotor.changeControlMode(TalonControlMode.Speed);
		
		/*rightShootMotor.changeControlMode(TalonControlMode.Speed);
		leftShootMotor.changeControlMode(TalonControlMode.Speed);
		leftShootMotor.setF(.021);
		rightShootMotor.setF(.021);*/
		
	
		
		//shooterEncoder =  new Encoder(0, 1, false, EncodingType.k4X);
		//shooterEncoder.setDistancePerPulse(1.0/2048);  // should be 1024 for this encoder 
		
		indexerMotor = Robot.bot.getIndexerObj();
		indexerLimitSwitch = new DigitalInput(RobotMap.indexerProximitySwitchPort);
		deflectorShifter = new Solenoid(1);
	}

	public static ShooterSubsystem instance = new ShooterSubsystem();
	

    public boolean shooterUpToSpeed(){
    	return getShootSpeedRight() > 6200;
    }
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
    		indexerMotor.set(-.5);    
    		RobotMap.indexerRunning = true;
    	}
    	else{
    		SmartDashboard.putString("Indexer Running", "false");
    		indexerMotor.set(0);
    		RobotMap.indexerRunning = false;
    	}
    }
    
    
 
    	
    public boolean getIndexSwitch(){
    	return indexerLimitSwitch.get();
    }
    public void indexerSpin(){
    	Timer time = new Timer();
    	time.start();
    	RobotMap.indexerRunning = true;
    	while(!getIndexSwitch() || time.get()< .5){
    		indexerMotor.set(-.4);
    		if(OI.getShootButton()){
    			if(RobotMap.shooterRunning){
    				runShooter(false);
    			}
    			else{
    				runShooter(true);
    			}
    		if(OI.getReverseIndexerButton()){
    			break;
    		}
    		}
    	}
    	while(getIndexSwitch()){
    		indexerMotor.set(-.4);
    		if(OI.getShootButton()){
    			if(RobotMap.shooterRunning){
    				runShooter(false);
    			}
    			else{
    				runShooter(true);
    			}
    		}
    		if(OI.getReverseIndexerButton()){
        			break;
        	}
    		
    	}
    	RobotMap.indexerRunning = false;
    	indexerMotor.set(0);
    }
    public void indexerReverse(){
    	indexerMotor.set(.4);
    }
    public void indexerStop(){
    	indexerMotor.set(0);
    }
    
  
    public void setShooter(double speed){
    	
    	leftShootMotor.changeControlMode(TalonControlMode.Follower); 
    	leftShootMotor.set(7);  // must be set to talon ID of master (right shooter = 2) 
    	leftShootMotor.enableBrakeMode(false);
    	//right is master 
    	rightShootMotor.enable();
    	rightShootMotor.enableBrakeMode(false);
    	rightShootMotor.changeControlMode(TalonControlMode.Speed);
    	SmartDashboard.putNumber("Shooter Set To:", speed);
    	rightShootMotor.set(speed);
    	RobotMap.shooterRunning = true;
    	//SmartDashboard.putString("ShooterRunning", "true");
    }
    
    public void runShooter(boolean run){
    	rightShootMotor.changeControlMode(TalonControlMode.PercentVbus);
    	leftShootMotor.changeControlMode(TalonControlMode.PercentVbus);
    	if(run){
    		//SmartDashboard.putBoolean("ShooterRunning", true);
    		leftShootMotor.set(RobotMap.SHOOTER_SPEED);    
    		rightShootMotor.set(RobotMap.SHOOTER_SPEED);
    		RobotMap.shooterRunning = true;
    	}
    	else{
    		//SmartDashboard.putBoolean("ShooterRunning", false);
    		leftShootMotor.set(0);
    		rightShootMotor.set(0);
    		RobotMap.shooterRunning = false;
    	}
    }
    
    public void stopShooter(){
    	leftShootMotor.changeControlMode(TalonControlMode.PercentVbus);
    	leftShootMotor.set(0);
    	rightShootMotor.changeControlMode(TalonControlMode.PercentVbus);
    	rightShootMotor.set(0);
    	//rightShootMotor.disableControl();
    	
    	RobotMap.shooterRunning = false;
    	SmartDashboard.putString("ShooterRunning", "false");
    }
    
  
    public double getShootSpeedRight(){
    	try{
    		SmartDashboard.putBoolean("Shooter error", false);
    		return rightShootMotor.getEncVelocity()/6.86;
    		
    	} catch(Exception e){
    		SmartDashboard.putBoolean("Shooter error", true);
    		return 6800;
    	} 
    	//return rightShootMotor.getEncVelocity(); //velocity is six times rpm
    	
    }
    public double getShootSpeedLeft(){
    	return leftShootMotor.getEncVelocity()/6.0;
    	//return leftShootMotor.getEncVelocity();
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
	//@Override
	/*protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getShootSpeedRight();
	}
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
		RobotMap.shootPIDOutput = output;
	}*/
	
}

