package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.Robot;
import org.usfirst.frc.team503.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class NewDrivetrainSubsystem extends PIDSubsystem {
	private static Solenoid drivetrainSolenoid;
	public static Encoder driveEncoder;
	private static double currentForward =0;
	private static double currentTurn;
	private final static double MAX_STEP =.2;
	public NewDrivetrainSubsystem(){
		super("NewDrivetrainSubsystem", RobotMap.DRIVE_kP, RobotMap.DRIVE_kI, RobotMap.DRIVE_kD);
		driveEncoder = new Encoder(RobotMap.driveEncoderPortA, RobotMap.driveEncoderPortB, RobotMap.driveEncoderReverseDirection, RobotMap.encoderType);
		setAbsoluteTolerance(RobotMap.DRIVE_PID_TOLERANCE);
		driveEncoder.setDistancePerPulse(.00048717);
		setInputRange(-200,200);
		currentForward = 0;
		currentTurn =0;
		//getPIDController().setContinuous();
		setOutputRange(-.8, .8);
		drivetrainSolenoid = new Solenoid(0);
	}
	
	public static NewDrivetrainSubsystem instance = new NewDrivetrainSubsystem();
	
    
	private void setMotorOutputs(double leftSpeed, double rightSpeed, boolean sensitivity){
		if(sensitivity){
			leftSpeed = setDriveSensitivity(leftSpeed);
			rightSpeed = setDriveSensitivity(rightSpeed);
		}	
		SmartDashboard.putNumber("leftSpeed", leftSpeed);
		SmartDashboard.putNumber("rightSpeed", rightSpeed);
		Robot.bot.getCANTalonObj(1).set(ControlMode.PercentOutput, -leftSpeed);   // front Left 
		Robot.bot.getCANTalonObj(2).set(ControlMode.PercentOutput, rightSpeed);  // front Right 
		Robot.bot.getCANTalonObj(3).set(ControlMode.PercentOutput, -leftSpeed);   // back Left 
		Robot.bot.getCANTalonObj(4).set(ControlMode.PercentOutput, rightSpeed);  // back Right 
	}
	
	public static void motorBrake(boolean bool){
		if(bool) {
			Robot.bot.getCANTalonObj(1).setNeutralMode(NeutralMode.Brake);
			Robot.bot.getCANTalonObj(2).setNeutralMode(NeutralMode.Brake);
			Robot.bot.getCANTalonObj(3).setNeutralMode(NeutralMode.Brake);
			Robot.bot.getCANTalonObj(4).setNeutralMode(NeutralMode.Brake);
		} else {
			Robot.bot.getCANTalonObj(1).setNeutralMode(NeutralMode.Coast);
			Robot.bot.getCANTalonObj(2).setNeutralMode(NeutralMode.Coast);
			Robot.bot.getCANTalonObj(3).setNeutralMode(NeutralMode.Coast);
			Robot.bot.getCANTalonObj(4).setNeutralMode(NeutralMode.Coast);
		}
		
		
	}
	
	private double setDriveSensitivity(double input){
		double output;
		if(RobotMap.currentGear){
			output = RobotMap.DRIVE_SENSITIVITY*Math.pow(input, 3) + (1-RobotMap.DRIVE_SENSITIVITY)*input;
		}
		else{
			output = RobotMap.LOW_GEAR_DRIVE_SENSITIVITY*Math.pow(input, 3) + (1-RobotMap.LOW_GEAR_DRIVE_SENSITIVITY)*input;
		}
		return output;
		
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
    public void resetRamp(){
    	currentForward=0;
    	currentTurn =0;
    }
    public void rampArcadeDrive(double moveValue, double rotateValue, boolean sensitivity){
    	
    	if(moveValue - currentForward > .2){
    		currentForward = currentForward + MAX_STEP;
    	}
    	else if(moveValue - currentForward < -.2){
    		currentForward = currentForward - MAX_STEP;
    	}
    	if(currentForward > 1.0){
    		currentForward = 1.0;
    	}
    	arcadeDrive(currentForward, currentTurn, sensitivity);
    	
    }
	public void arcadeDrive(double moveValue, double rotateValue, boolean sensitivity) {
        double leftMotorSpeed;
        double rightMotorSpeed;
        
        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
        /*currentForward = moveValue;
        currentTurn = rotateValue;*/

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
        RobotMap.currentForward = leftValue;
        RobotMap.currentTurn = 0;
        setMotorOutputs(leftValue, rightValue, sensitivity);
    }

 
 
    public void setDistanceperpulse(double distancePerPulse){
    	driveEncoder.setDistancePerPulse(distancePerPulse);
    }
   
 	public double getEncoderDistance(){
    	SmartDashboard.putNumber("Drive Encoder Distance=", driveEncoder.getDistance());
    	return driveEncoder.getDistance();
    }
    
    public void resetEncoder(){
    	driveEncoder.reset();
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
		if(output <.18 && output > 0.02){
			output = .18;
		}
		else if(output >-.18&& output <-.020){
			output = -.18;
		}
		RobotMap.drivePIDOutput = output;
	}
}