package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDInterface;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ArmSubsystem extends PIDSubsystem{
	//powered on same axis as intake 
	//CANTalon for climber arm // come back for more info on power takeoff
	//one spike relay to push hooks out = extend
	
	private static CANTalon armWinchMotor;
	private static Relay extender;
	private static Solenoid armWinchShifter;
	public static Encoder armAngleEncoder;
	public static Encoder extenderEncoder;
	
	private ArmSubsystem(){
		super("ArmSubsystem", RobotMap.ARM_kP, RobotMap.ARM_kI, RobotMap.ARM_kD);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(RobotMap.ARM_PID_TOLERANCE);
		setInputRange(-100,100);
		setOutputRange(-1.0,1.0);
		
		armAngleEncoder =  new Encoder(RobotMap.armEncoderPortA, RobotMap.armEncoderPortB, RobotMap.armEncoderReverseDirection,RobotMap.encoderType);
		armWinchMotor = new CANTalon(RobotMap.armWinchMotorPort);
		extender = new Relay (RobotMap.extenderMotorPort);
		armWinchShifter = new Solenoid(RobotMap.armWinchSolenoidPort);
		extenderEncoder = new Encoder(RobotMap.extenderEncoderPortA, RobotMap.extenderEncoderPortB, RobotMap.extenderEncoderReverseDirection, RobotMap.encoderType);
		
    	armAngleEncoder.setDistancePerPulse(RobotMap.ARM_DISTANCE_PER_PULSE);
    	armAngleEncoder.setMaxPeriod(.5);
    	armAngleEncoder.setMinRate(1);
    	armAngleEncoder.setSamplesToAverage(10);
	}
	
	public static ArmSubsystem instance = new ArmSubsystem();
	
	public void winchMode(){
		armWinchShifter.set(true);
	}
	
	public void armMode(){
		armWinchShifter.set(false);
	}
	
	public void extenderExtend(){
		extender.set(Relay.Value.kForward);
	}
	public void extenderStop(){
		extender.set(Relay.Value.kOff);
	}
	public void extenderRetract(){
		extender.set(Relay.Value.kReverse);
	}
	
	public static enum ArmPosition {
		TOP(60), MID(30), BOTTOM(0);
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
				armWinchMotor.set(RobotMap.ARM_PID_OUTPUT);
			}
			else if(getArmAngle()> position.position){
				armWinchMotor.set(-RobotMap.ARM_PID_OUTPUT);
			}
			else{
				armWinchMotor.set(0);
			}
		}
	}
	
	public double getExtenderPosition(){
		return extenderEncoder.getDistance();
	}
	
	public double getArmAngle(){
		 return armAngleEncoder.getDistance();
	}
	
	public double getArmAngleEncoderError(){
		return ((PIDInterface) instance).getError();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return armAngleEncoder.getDistance();
	}


	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		RobotMap.ARM_PID_OUTPUT = output;
	}
}

