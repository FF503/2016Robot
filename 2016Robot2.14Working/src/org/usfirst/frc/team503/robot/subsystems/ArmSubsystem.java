package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDInterface;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class ArmSubsystem extends PIDSubsystem{
	//powered on same axis as intake 
	//CANTalon for climber arm // come back for more info on power takeoff
	//one spike relay to push hooks out = extend
	
	private static CANTalon armWinchMotor;
	private static Spark extender;
	private static Solenoid armWinchShifter;
	public static Encoder armAngleEncoder;
	//public static Encoder extenderEncoder;
	

	private ArmSubsystem(){
		super("ArmSubsystem", RobotMap.ARM_kP, RobotMap.ARM_kI, RobotMap.ARM_kD);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(RobotMap.ARM_PID_TOLERANCE);
		setInputRange(-100,100);
		setOutputRange(-1.0,1.0);
		
		armAngleEncoder =  new Encoder(RobotMap.armEncoderPortA, RobotMap.armEncoderPortB, RobotMap.armEncoderReverseDirection,RobotMap.encoderType);
		armWinchMotor = new CANTalon(RobotMap.armWinchMotorPort);
		extender = new Spark (RobotMap.extenderSparkPort);
		armWinchShifter = new Solenoid(RobotMap.armWinchSolenoidPort);
		//extenderEncoder = new Encoder(RobotMap.extenderEncoderPortA, RobotMap.extenderEncoderPortB, RobotMap.extenderEncoderReverseDirection, RobotMap.encoderType);
		
    	//armAngleEncoder.setDistancePerPulse(RobotMap.ARM_DISTANCE_PER_PULSE);
    	//armAngleEncoder.setMaxPeriod(.5);
    	//armAngleEncoder.setMinRate(1);
    	//armAngleEncoder.setSamplesToAverage(10);
	}
	
	public static ArmSubsystem instance = new ArmSubsystem();
	
	public void winchMode(){
		armWinchShifter.set(false);
	}
	
	public void armMode(){
		armWinchShifter.set(true);
	}
	
	public void extenderExtend(){
		extender.set(.8);
	}
	public void extenderOff(){
		extender.set(0);
	}
	
	public void extenderRetract(){
		extender.set(RobotMap.CLIMB_SPEED*RobotMap.EXTRACT_WINCH_RATIO);
	}
	/*
	
	public static enum ArmPosition {
		TOP(RobotMap.ARM_POSITION_TOP), MID(RobotMap.ARM_POSITION_MID), BOT(RobotMap.ARM_POSITION_BOT);
		private double position;

		private ArmPosition(double position) {
			this.position = position;
		}
	}
	
	public void goToArmPosition(ArmPosition position){
		double setpoint = position.position;
		DrivetrainSubsystem.instance.setSetpoint(setpoint);
		
		while(Math.abs(instance.getArmAngleEncoderError())>RobotMap.ARM_PID_RANGE){
			if(getArmAngle()< position.position){
				armWinchMotor.set(RobotMap.ARM_SPEED);
			}
			else if(getArmAngle()> position.position){
				armWinchMotor.set(-RobotMap.ARM_SPEED);
			}
		}
		
		while(Math.abs(instance.getArmAngleEncoderError())>RobotMap.ARM_TOLERANCE){
			if(getArmAngle()< position.position){
				armWinchMotor.set(RobotMap.armPIDOutput);
			}
			else if(getArmAngle()> position.position){
				armWinchMotor.set(-RobotMap.armPIDOutput);
			}
			else{
				armWinchMotor.set(0);
			}
		}
	}*/
	
	//public double getExtenderPosition(){
	//	return extenderEncoder.getDistance();
	//}
	
	public double getArmEncoderAngle(){
		 return armAngleEncoder.getDistance();
	}
	
	public double getArmEncoderCounts(){
		return armAngleEncoder.get();
	}
	
	public double getArmAngleEncoderError(){
		return ((PIDInterface) instance).getError();
	}
	
	public void setArmWinchMotor(double power){
		armWinchMotor.set(power);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
		//return armAngleEncoder.getDistance();
	}


	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		RobotMap.armPIDOutput = output;
	}
	
}

