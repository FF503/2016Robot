package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ArmSubsystem extends PIDSubsystem{
	//powered on same axis as intake 
	//CANTalon for climber arm // come back for more info on power takeoff
	//one spike relay to push hooks out = extend
	
	public com.ctre.phoenix.motorcontrol.can.TalonSRX armWinchMotor;
	private static Spark extender;
	private static DoubleSolenoid armWinchShifter;
	public static Encoder armAngleEncoder;
	private static DigitalInput armLowerLimitSwitch;
	private static DigitalInput armUpperLimitSwitch;
	private static DigitalInput extenderUpperLimitSwitch;
	private static DigitalInput extenderLowerLimitSwitch;
	
	//public static Encoder extenderEncoder;
	

	private ArmSubsystem(){
		super("ArmSubsystem", RobotMap.ARM_kP, RobotMap.ARM_kI, RobotMap.ARM_kD);
		getPIDController().setContinuous(false);
		//try percent tolerance?
		setAbsoluteTolerance(RobotMap.ARM_PID_TOLERANCE);
		setInputRange(0,32000);
		setOutputRange(0,.8);     // was .5 
		
		
		armAngleEncoder =  new Encoder(RobotMap.armEncoderPortA, RobotMap.armEncoderPortB, RobotMap.armEncoderReverseDirection,RobotMap.encoderType);
		armWinchMotor = Robot.bot.getArmWinchMotor();
		extender = new Spark (RobotMap.extenderSparkPort);
		armWinchShifter = new DoubleSolenoid(RobotMap.armSolenoidport,RobotMap.winchSolenoidPort);
		armLowerLimitSwitch = new DigitalInput(9);
		extenderUpperLimitSwitch = new DigitalInput(23);
		
		extenderLowerLimitSwitch = new DigitalInput(7);
		//armUpperLimitSwitch = new DigitalInput(23);
		//extenderEncoder = new Encoder(RobotMap.extenderEncoderPortA, RobotMap.extenderEncoderPortB, RobotMap.extenderEncoderReverseDirection, RobotMap.encoderType);
		
    	//armAngleEncoder.setDistancePerPulse(RobotMap.ARM_DISTANCE_PER_PULSE);
    	//armAngleEncoder.setMaxPeriod(.5);
    	//armAngleEncoder.setMinRate(1);
    	//armAngleEncoder.setSamplesToAverage(10);
	}
	
	public boolean ArmOnTarget(ArmPosition pos){
		return Math.abs(getArmEncoderCounts() - pos.position) < RobotMap.ARM_PID_TOLERANCE;
	}
	public static ArmSubsystem instance = new ArmSubsystem();
	
	public void winchMode(){
		armWinchShifter.set(Value.kForward);
		RobotMap.armWinchMode = true;
	}
	
	public void armMode(){
		armWinchShifter.set(Value.kReverse);
		RobotMap.armWinchMode = false;
	}
	
	public void extenderExtend(){
		extender.set(1);   //was 0.6
	}
	public void extenderOff(){
		extender.set(0);   
	}
	
	public void extenderRetract(){
		extender.set(-RobotMap.CLIMB_SPEED*RobotMap.EXTEND_WINCH_RATIO);
	}
	
	public static enum ArmPosition {
		TOP(RobotMap.ARM_POSITION_TOP), LOAD(RobotMap.ARM_POSITION_LOAD), BOT(RobotMap.ARM_POSITION_BOT), OPERATOR(7000), PBOTTOP(RobotMap.ARM_POSITION_PBOT_TOP);
		public double position;

		private ArmPosition(double position) {
			this.position = position;
		}
	}
	
	public boolean getExtenderUpperLimitSwitch(){
		return extenderUpperLimitSwitch.get();
	}
	public boolean getExtenderLowerLimitSwitch(){
		return extenderLowerLimitSwitch.get();
	}
	public boolean getArmLowerLimitSwitch(){
		return !armLowerLimitSwitch.get();
	}
	
	public boolean getArmUpperLimitSwitch(){
		//SmartDashboard.putBoolean("arm upper limit switch", armUpperLimitSwitch.get());
		return false;
	}
	
	public void goToArmPosition(ArmPosition position){
		
		while(instance.getArmAngleEncoderError(position)>RobotMap.ARM_PID_RANGE){
			if(getArmEncoderCounts()> position.position){
				armWinchMotor.set(-RobotMap.ARM_SPEED);
			}
			else if(getArmEncoderCounts()< position.position){
				armWinchMotor.set(RobotMap.ARM_SPEED);
			}
			if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
				//ArmSubsystem.armAngleEncoder.reset();
			}
		}
		
		double setpoint = position.position;
		ArmSubsystem.instance.setSetpoint(setpoint);
		ArmSubsystem.instance.enable();
		
		while(!ArmSubsystem.instance.onTarget()){
			armWinchMotor.set(RobotMap.armPIDOutput);
		}
		
		ArmSubsystem.instance.disable();
		armWinchMotor.set(0);		
	}
	
	//public double getExtenderPosition(){
	//	return extenderEncoder.getDistance();
	//}
	
	public double getArmEncoderAngle(){
		 return armAngleEncoder.getDistance()  ;
	}
	
	public double getArmEncoderCounts(){
		SmartDashboard.putNumber("arm encoder kounts", armAngleEncoder.get());
		return armAngleEncoder.get();
	}
	
	public void resetEncoder(){
		armAngleEncoder.reset();
	}
	
	public double getArmAngleEncoderError(ArmPosition position){
		//SmartDashboard.putNumber("arm encoder error", Math.abs(armAngleEncoder.get() - position.position));
		return Math.abs(armAngleEncoder.get() - position.position);
	}
	
	public void setArmWinchMotor(double power){
		armWinchMotor.set(power);
		//SmartDashboard.putNumber("ARM POWER", power);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return armAngleEncoder.get();
	}

	
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if(output <.15 && output > 0){
			output = .15;
		}
		/*else if(output >-.08&& output <0){
			output = -.08;
		}*/
		RobotMap.armPIDOutput = -output;
	}
	
}

