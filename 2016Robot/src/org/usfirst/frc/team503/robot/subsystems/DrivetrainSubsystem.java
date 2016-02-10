package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem implements PIDOutput, PIDSource{
	private static CANTalon frontLeftMotor;
	private static CANTalon frontRightMotor;
	private static CANTalon backLeftMotor;
	private static CANTalon backRightMotor;
	
	private static Solenoid drivetrainLeftSolenoid;
	private static Solenoid drivetrainRightSolenoid;
	
    public static Encoder driveEncoder;
	public static PIDController turn_pid;
	
	private DrivetrainSubsystem(){
		super("DrivetrainSubsystem", RobotMap.DRIVE_kP,RobotMap.DRIVE_kI, RobotMap.DRIVE_kD);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(RobotMap.DRIVE_PID_TOLERANCE);
		setInputRange(-100,100);
		setOutputRange(-1.0,1.0);
		LiveWindow.addActuator("Drivetrain", "PIDSubsystem contoller", getPIDController());
		
		if(RobotMap.robot==0){
			frontLeftMotor = new CANTalon(RobotMap.frontLeftProgrammingBot);
			frontRightMotor = new CANTalon(RobotMap.frontRightProgrammingBot);
			backLeftMotor = new CANTalon(RobotMap.backLeftProgrammingBot);
			backRightMotor = new CANTalon(RobotMap.backRightProgrammingBot);
		}
		else if(RobotMap.robot==1){
			frontLeftMotor = new CANTalon(RobotMap.leftFrontKitBot);
			frontRightMotor = new CANTalon(RobotMap.rightFrontKitBot);
			backLeftMotor = new CANTalon(RobotMap.leftBackKitBot);
			backRightMotor = new CANTalon(RobotMap.rightBackKitBot);
		}
		else if(RobotMap.robot == 2){
			frontLeftMotor = new CANTalon(RobotMap.leftFrontPracticeBot);
			backLeftMotor = new CANTalon(RobotMap.leftBackPracticeBot);
			frontRightMotor = new CANTalon(RobotMap.rightFrontPracticeBot);
			backRightMotor = new CANTalon(RobotMap.rightBackPracticeBot);
		}
		
		drivetrainLeftSolenoid = new Solenoid(RobotMap.drivetrainLeftSolenoidPort);
		drivetrainRightSolenoid = new Solenoid(RobotMap.drivetrainRightSolenoidPort);
		
		driveEncoder = new Encoder(RobotMap.driveEncoderPortA, RobotMap.driveEncoderPortB, RobotMap.driveEncoderReverseDirection, RobotMap.encoderType);
	    turn_pid = new PIDController(RobotMap.TURN_kP,RobotMap.TURN_kI,RobotMap.TURN_kD, NavSensorSubsystem.instance.ahrs, instance);
	    
		turn_pid.setInputRange(-180, 180);
		turn_pid.setOutputRange(-1, 1);
		turn_pid.setAbsoluteTolerance(RobotMap.TURN_PID_TOLERANCE);
		driveEncoder.setDistancePerPulse(RobotMap.robot==1?RobotMap.KITBOT_DRIVE_DISTANCE_PER_PULSE:RobotMap.PROBOT_DRIVE_DISTANCE_PER_PULSE);
		driveEncoder.setMaxPeriod(.5);
		driveEncoder.setMinRate(1);
		driveEncoder.setSamplesToAverage(10);
	}
	
	public static DrivetrainSubsystem instance = new DrivetrainSubsystem();

	private void setMotorOutputs(double leftSpeed, double rightSpeed, boolean sensitivity){
		if(sensitivity){
			leftSpeed = setDriveSensitivity(leftSpeed);
			rightSpeed = setDriveSensitivity(rightSpeed);
		}		
		frontLeftMotor.set(-leftSpeed);
		frontRightMotor.set(rightSpeed);
		backLeftMotor.set(-leftSpeed);
		backRightMotor.set(rightSpeed);
	}
	
	private double setDriveSensitivity(double input){
		input = RobotMap.DRIVE_SENSITIVITY*Math.pow(input, 3) + (1-RobotMap.DRIVE_SENSITIVITY)*input;
		return input;
	}
	
    private double limit(double num) {
        if (num > 1.0) {
            num= 1.0;
        }
        else if (num < -1.0) {
            num= -1.0;
        }
        	return num;
    }
    	
	public void arcadeDrive(double moveValue, double rotateValue, boolean sensitivity) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        setMotorOutputs(leftMotorSpeed, rightMotorSpeed,sensitivity);
    }
	
    public void tankDrive(double leftValue, double rightValue, boolean sensitivity) {

        // square the inputs (while preserving the sign) to increase fine control while permitting full power
        leftValue = limit(leftValue);
        rightValue = limit(rightValue);

        setMotorOutputs(leftValue, rightValue, sensitivity);
    }
	
    public  double getTurnPIDOutput(){
    	return turn_pid.get();
    }
    
    public void setDegreesSetpoint(double degrees){
    	turn_pid.setSetpoint(degrees);
    }
    
    public void turnPIDEnable(){
    	turn_pid.enable();
    }
    
    public void turnPIDDisable(){
    	turn_pid.disable();
    }
    
    public boolean turnPIDOnTarget(){
    	return turn_pid.onTarget();
    }
    
    public void turnPIDReset(){
    	turn_pid.reset();
    }
    
    public double getturnPidError(){
    	return turn_pid.getError();
    }
    
    public double getEncoderDistance(){
    	return driveEncoder.getDistance();
    }
    
    public double getEncoderRate(){
    	return driveEncoder.getRate();
    }
    
    public boolean isEncoderStopped(){
    	return driveEncoder.getStopped();
    }
    
    public int getEncoderPulse(){
    	return driveEncoder.get();
    }
    
	public void initDefaultCommand(){
	}

	@Override
	public void pidWrite(double output) {
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return getEncoderDistance();
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getEncoderDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		RobotMap.DRIVE_PID_OUTPUT = output;
	}  
	
}

