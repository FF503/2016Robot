package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;

/**
 *
 */
public class NavXDriveStraightDistance extends Command implements PIDOutput {
	
	PIDController turnController;
	double rotateToAngleRate;
	private double inches;
	private double speed;
	/* PID Controller Coefficients to be tuned */ 
	static final double kP = 0.00;//.03
	static final double kI = 0.00; 
	static final double kD = 0.00; 
	static final double kF = 0.00;
	
	/* indicates how close to "on target" the PID controller will attempt to get */ 
	static final double kToleranceDegrees = 2.0f; 
	
    public NavXDriveStraightDistance(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.inches = inches*150;//*148.667;
    }
    	public static NewDrivetrainSubsystem ndsinstance = NewDrivetrainSubsystem.instance;

    // Called just before this Command runs the first time
    protected void initialize() {
	    turnController = new PIDController(kP,kI,kD,kF,NavSensorSubsystem.ahrs,this);
	    turnController.setInputRange(-180.0f,  180.0f);
	    turnController.setOutputRange(-1.0,1.0);
	    turnController.setAbsoluteTolerance(kToleranceDegrees);
	    turnController.setContinuous(true);
	    LiveWindow.addActuator("DriveStraight", "RotateController",turnController);
	 
	    //Reset Encoder to zero
	    ndsinstance.resetEncoder();
	    ndsinstance.setDistanceperpulse(RobotMap.KITBOT_DRIVE_DISTANCE_PER_PULSE ); 	
	    turnController.enable();
	    SmartDashboard.putNumber("once", 10);
    }
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = RobotMap.drivePIDOutput;
    	ndsinstance.arcadeDrive(-.5, rotateToAngleRate, false); 
    	SmartDashboard.putNumber("NavXDriveStraight RotatetoAngle=", rotateToAngleRate);
    	SmartDashboard.putNumber("Drive Distance=",  ndsinstance.getEncoderDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean wearedone = false; 
     	// if actual distance traveled on encoders > than set point we have reached destination  
     	if (ndsinstance.getEncoderDistance() >= inches) {    
     	    wearedone = true; 
     	} else {
     		wearedone = false; 
     	}
     	SmartDashboard.putBoolean("On target?", wearedone);
     	return ndsinstance.getEncoderDistance()>inches; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    	ndsinstance.arcadeDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    @Override 
    public void pidWrite(double output) {
    	rotateToAngleRate = output; 
    }
}