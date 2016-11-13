package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class TurnPIDSubsystem extends PIDSubsystem {

    // Initialize your subsystem here
    public TurnPIDSubsystem() {
    	super("TurnPIDSubsystem", RobotMap.AUTON_TURN_kP, RobotMap.AUTON_TURN_kI, RobotMap.AUTON_TURN_kD);
		getPIDController().setContinuous(true);
		getPIDController().setAbsoluteTolerance(RobotMap.AUTON_TURN_PID_TOLERANCE);
		getPIDController().setOutputRange(-.8, .8);
		getPIDController().setInputRange(-180, 180);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    public static TurnPIDSubsystem instance = new TurnPIDSubsystem();
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return NavSensorSubsystem.ahrs.getYaw();
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
    }
    
    protected void usePIDOutput(double output) {
    	if(onTarget()){
    		
    	}  // was .20 
    	else{
    		if(output < .25 && output > .02){
    			output =.25;
    		}
    		else if(output > -.25 && output < -.020){
    			output = -.25;
    		}
    	}
    	RobotMap.autonTurnPIDOutput = output;
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
