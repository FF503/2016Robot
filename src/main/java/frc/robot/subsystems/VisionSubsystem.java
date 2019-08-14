package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class VisionSubsystem extends PIDSubsystem {

    // Initialize your subsystem here
    public VisionSubsystem() {
    	super("VisionSubsystem", RobotMap.TURN_kP,RobotMap.TURN_kI, RobotMap.TURN_kD);
		getPIDController().setContinuous(true);
		getPIDController().setAbsoluteTolerance(1);
		getPIDController().setOutputRange(-.7, .7);
		getPIDController().setInputRange(-180, 180);
		setAbsoluteTolerance(RobotMap.TURN_PID_TOLERANCE);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    public static VisionSubsystem instance = new VisionSubsystem();
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
    		
    	}
    	else{
    		if(output < .23 && output > 0){
    			output =.23;
    		}
    		else if(output > -.23 && output < 0){
    			output = -.23;
    		}
    	}
    	RobotMap.visionTurnPIDOutput = output;
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
