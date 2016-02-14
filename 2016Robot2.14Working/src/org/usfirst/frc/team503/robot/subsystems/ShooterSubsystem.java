package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	//private static CANTalon indexerMotor;
	//private static DigitalInput indexerProximitySwitch;
	//private static DoubleSolenoid deflectorShifter;
	//public static Encoder shooterEncoder;
	
	private ShooterSubsystem(){
		rightShootMotor = new CANTalon(RobotMap.rightShootMotorPort);
		leftShootMotor = new CANTalon(RobotMap.leftShootMotorPort);
		//indexerMotor = new CANTalon(RobotMap.indexerMotorPort);
		//shooterEncoder =  new Encoder(RobotMap.shooterEncoderPortA, RobotMap.shooterEncoderPortB, RobotMap.shooterEncoderReverseDirection,RobotMap.encoderType);
		//indexerProximitySwitch = new DigitalInput(RobotMap.indexerProximitySwitchPort);
		//deflectorShifter = new DoubleSolenoid( RobotMap.deflectorSolenoidPortA, RobotMap.deflectorSolenoidPortB);
	}
	
	public static ShooterSubsystem instance = new ShooterSubsystem();
    
   /* public void raiseDeflector(){
    	if(deflectorMode == 0){
    		deflectorShifter.set(DoubleSolenoid.Value.kForward);
    		deflectorMode = 1;
    	}    	
    }
    public void lowerDeflector(){
    	if(deflectorMode ==1){
    		deflectorShifter.set(DoubleSolenoid.Value.kReverse);
    		deflectorMode = 0;
    	} 		
    }
    
    public boolean isBallIndexed(){
    	return indexerProximitySwitch.get();    	
    }*/
    
    public void runShooter(){
    	//if(isBallIndexed()){
    		leftShootMotor.set(RobotMap.SHOOTER_SPEED);    
    		rightShootMotor.set(RobotMap.SHOOTER_SPEED);
    	//}
    }
    
    public void stopShooter(){
    	leftShootMotor.set(0);
    	rightShootMotor.set(0);
    }
    
   /* public void setIndexer(boolean on){
    	if(on){
        	indexerMotor.set(RobotMap.INDEXER_SPEED);
    	}
    	else{
    		indexerMotor.set(0);
    	}
    }
    
    public double getShooterEncoder(){
    	return shooterEncoder.getDistance();
    }*/

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

