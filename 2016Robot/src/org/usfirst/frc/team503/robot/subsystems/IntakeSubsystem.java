package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	//intake Spike relay, //Proximity Switch DIO 1
	
	private static Relay intake;
	private static DigitalInput intakeProximitySwitch;
	
	private IntakeSubsystem(){
		intake = new Relay(RobotMap.intakeMotorPort);
		intakeProximitySwitch = new DigitalInput(1);
	}
	
	public static IntakeSubsystem instance = new IntakeSubsystem();
    
    public void intakeIn(){
    	intake.set(Relay.Value.kForward);
    }
    public void intakeOut(){
    	intake.set(Relay.Value.kReverse);
    }
    public void intakeStop(){
    	intake.set(Relay.Value.kOff);
    }
    
    public boolean intakeHasBall(){
    	return intakeProximitySwitch.get();
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

