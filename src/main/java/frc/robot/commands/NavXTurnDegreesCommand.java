package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NavXTurnDegreesCommand extends Command implements PIDOutput {

	PIDController turnController;
	double rotateToAngleRate;
	private double angle;
	Timer time;
	
	/* PID Controller Coefficients to be tuned */ 
	static final double kP = 0.0105;//.0105
	static final double kI = 0; 
	static final double kD = 0.00; 
	static final double kF = 0.00;
	
	/* indicates how close to "on target" the PID controller will attempt to get */ 
	static final double kToleranceDegrees = 2.0f; 
	
    public NavXTurnDegreesCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.angle = angle;
    }
    
    public static NewDrivetrainSubsystem ndsinstance = NewDrivetrainSubsystem.instance;

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer();
    	time.start();
	    turnController = new PIDController(RobotMap.AUTON_TURN_kP, RobotMap.AUTON_TURN_kI, RobotMap.AUTON_TURN_kD,kF, NavSensorSubsystem.ahrs, this);
	    NavSensorSubsystem.ahrs.reset();
	    turnController.setInputRange(-180.0f,  180.0f);
	    turnController.setOutputRange(-.8,.8);
	    turnController.setAbsoluteTolerance(kToleranceDegrees);
	    turnController.setContinuous(true);
	    turnController.setSetpoint(angle); 
	    turnController.enable();
	    
    	SmartDashboard.putBoolean("turn on target", false);
	    LiveWindow.addActuator("DriveTurn", "RotateController",turnController);
    }
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(rotateToAngleRate > 0){
    		ndsinstance.tankDrive(-rotateToAngleRate, rotateToAngleRate, false);
    	}
    	else if(rotateToAngleRate < 0){
    		ndsinstance.tankDrive(rotateToAngleRate , -rotateToAngleRate, false);
    	}
    	SmartDashboard.putNumber("NavXDriveStraight RotatetoAngle=", rotateToAngleRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putBoolean("turn on target", turnController.onTarget());
    	if(turnController.onTarget()||time.get()>1.5){
    		turnController.disable();
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    	ndsinstance.tankDrive(0, 0, false);
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