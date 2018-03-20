package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.Robot;
import org.usfirst.frc.team503.robot.RobotMap;
//import org.usfirst.frc.team503.robot.RobotHardware;

import com.ctre.phoenix.motorcontrol.ControlMode;

//import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem{
	private static Solenoid drivetrainSolenoid;
    public static Encoder driveEncoder;
	
	private DrivetrainSubsystem(){
		super("DrivetrainSubsystem", RobotMap.DRIVE_kP,RobotMap.DRIVE_kI, RobotMap.DRIVE_kD);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(RobotMap.DRIVE_PID_TOLERANCE);
		setInputRange(-200,200);
		setOutputRange(-.7, .7);
		LiveWindow.addActuator("Drivetrain", "PIDSubsystem contoller", getPIDController());
		
		drivetrainSolenoid = new Solenoid(0);
		driveEncoder = new Encoder(RobotMap.driveEncoderPortA, RobotMap.driveEncoderPortB, RobotMap.driveEncoderReverseDirection, RobotMap.encoderType);   


	}
	
	public static DrivetrainSubsystem instance = new DrivetrainSubsystem();
	
	private static void setMotorOutputs(double leftSpeed, double rightSpeed, boolean sensitivity){
		if(sensitivity){
			leftSpeed = setDriveSensitivity(leftSpeed);
			rightSpeed = setDriveSensitivity(rightSpeed);
		}		
		Robot.bot.getCANTalonObj(1).set(ControlMode.PercentOutput, -leftSpeed);   // front Left 
		Robot.bot.getCANTalonObj(2).set(ControlMode.PercentOutput, rightSpeed);  // front Right 
		Robot.bot.getCANTalonObj(3).set(ControlMode.PercentOutput, -leftSpeed);   // back Left 
		Robot.bot.getCANTalonObj(4).set(ControlMode.PercentOutput, rightSpeed);  // back Right 
	}
	
	private static double setDriveSensitivity(double input){
		input = RobotMap.DRIVE_SENSITIVITY*Math.pow(input, 3) + (1-RobotMap.DRIVE_SENSITIVITY)*input;
		return input;
	}
	
	public void shiftGears(boolean gear){
		SmartDashboard.putString("Drivetrain ShiftGears","True");
		if(gear){
			SmartDashboard.putString("Drivetrain Shift to high","True");
			drivetrainSolenoid.set(true);
			RobotMap.currentGear = true;
		}
		else{
			SmartDashboard.putString("Drivetrain Shift to high","false");
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
    
 	static{
    	driveEncoder.setDistancePerPulse(RobotMap.KITBOT_DRIVE_DISTANCE_PER_PULSE);
    	driveEncoder.setMaxPeriod(.5);
    	driveEncoder.setMinRate(5);
    	driveEncoder.setSamplesToAverage(10);	
    }
    
    public double getEncoderDistance(){
    	SmartDashboard.putNumber("Encoder Distance=", driveEncoder.getDistance());
    	return driveEncoder.getDistance();
    }
    
    public double getEncoderRate(){
    	return driveEncoder.getRate();
    }
    
    public boolean isEncoderStopped(){
    	return driveEncoder.getStopped();
    }
    
    public int getEncoderCount(){
    	return driveEncoder.get();
    }
    
	public void initDefaultCommand(){
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

