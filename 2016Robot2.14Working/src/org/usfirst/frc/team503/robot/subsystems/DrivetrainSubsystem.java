package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.Robot;
import org.usfirst.frc.team503.robot.RobotMap;
//import org.usfirst.frc.team503.robot.RobotHardware;

//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem implements PIDOutput, PIDSource{
	private static Solenoid drivetrainSolenoid;
	
	private DrivetrainSubsystem(){
		super("DrivetrainSubsystem", RobotMap.DRIVE_kP,RobotMap.DRIVE_kI, RobotMap.DRIVE_kD);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(RobotMap.DRIVE_PID_TOLERANCE);
		setInputRange(-100,100);
		setOutputRange(-1.0,1.0);
		LiveWindow.addActuator("Drivetrain", "PIDSubsystem contoller", getPIDController());
		
		drivetrainSolenoid = new Solenoid(0);

	}
	
	public static DrivetrainSubsystem instance = new DrivetrainSubsystem();
	
    
	private static void setMotorOutputs(double leftSpeed, double rightSpeed, boolean sensitivity){
		if(sensitivity){
			leftSpeed = setDriveSensitivity(leftSpeed);
			rightSpeed = setDriveSensitivity(rightSpeed);
		}		
		Robot.bot.getCANTalonObj(1).set(-leftSpeed);   // front Left 
		Robot.bot.getCANTalonObj(2).set(rightSpeed);  // front Right 
		Robot.bot.getCANTalonObj(3).set(-leftSpeed);   // back Left 
		Robot.bot.getCANTalonObj(4).set(rightSpeed);  // back Right 
	}
	
	private static double setDriveSensitivity(double input){
		input = RobotMap.DRIVE_SENSITIVITY*Math.pow(input, 3) + (1-RobotMap.DRIVE_SENSITIVITY)*input;
		return input;
	}
	
	public void shiftGears(boolean gear){
		if(gear){
			drivetrainSolenoid.set(true);
			RobotMap.currentGear = true;
		}
		else{
			drivetrainSolenoid.set(false);
			RobotMap.currentGear = false;
		}
	}
	
    private static double limit(double num) {
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


 public static Encoder driveEncoder = new Encoder(RobotMap.driveEncoderPortA, RobotMap.driveEncoderPortB, RobotMap.driveEncoderReverseDirection, RobotMap.encoderType);

 public static PIDController turn_pid = new PIDController(RobotMap.TURN_kP,RobotMap.TURN_kI,RobotMap.TURN_kD, NavSensorSubsystem.ahrs, instance);
   
    static{
    	turn_pid.setInputRange(-180, 180);
    	turn_pid.setOutputRange(-1, 1);
    	turn_pid.setAbsoluteTolerance(RobotMap.TURN_PID_TOLERANCE);
    	driveEncoder.setDistancePerPulse(RobotMap.DRIVE_LOW_GEAR_DISTANCE_PER_PULSE);
    	driveEncoder.setMaxPeriod(.5);
    	driveEncoder.setMinRate(5);
    	driveEncoder.setSamplesToAverage(10);
    	
    }
   
    public static double getTurnPIDOutput(){
    	return turn_pid.get();
    }
    
    public static void setDegreesSetpoint(double degrees){
    	turn_pid.setSetpoint(degrees);
    }
    
    public static void turnPIDEnable(){
    	turn_pid.enable();
    }
    
    public static void turnPIDDisable(){
    	turn_pid.disable();
    }
    
    public static boolean turnPIDOnTarget(){
    	return turn_pid.onTarget();
    }
    
    public static void turnPIDReset(){
    	turn_pid.reset();
    }
    
    public static double getturnPidError(){
    	return turn_pid.getError();
    }
    
    public static double getEncoderDistance(){
    	SmartDashboard.putNumber("Encoder Distance=", driveEncoder.getDistance());
    	return driveEncoder.getDistance();
    }
    
    public static double getEncoderRate(){
    	return driveEncoder.getRate();
    }
    
    public static boolean isEncoderStopped(){
    	return driveEncoder.getStopped();
    }
    
    public static int getEncoderPulse(){
    	return driveEncoder.get();
    }
    
	public void initDefaultCommand(){
	}

	@Override
	public void pidWrite(double output) {
		//arcadeDrive(output, 0, false);
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
		RobotMap.drivePIDOutput = output;
	}  
	
}

