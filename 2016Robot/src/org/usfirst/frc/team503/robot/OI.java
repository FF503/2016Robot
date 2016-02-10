package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.AimCommand;
import org.usfirst.frc.team503.robot.commands.BallOutCommand;
import org.usfirst.frc.team503.robot.commands.IntakeBallCommand;
import org.usfirst.frc.team503.robot.commands.ToClimberCommand;
import org.usfirst.frc.team503.robot.commands.ToIntakeCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private static Joystick joystick = new Joystick(0);
	private static JoystickButton targetButton = new JoystickButton(joystick, 2);
	private static JoystickButton intakeOutButton = new JoystickButton(joystick, 5);
	private static JoystickButton ballOutButton = new JoystickButton(joystick, 6);
	private static JoystickButton toClimberButton = new JoystickButton(joystick, 3);
	private static JoystickButton intakeInButton = new JoystickButton(joystick, 4);
	private static double DRIVE_SENSITIVITY = .75;
	
	
	public static enum Mode{
		PREMATCH,AUTON,TELEOP,ENDGAME,POSTMATCH;
	}
	
	public static Mode mode;
	
	public static int climberIntakeMode = 1; // 0 is climber, 1 is intake
	
	public static int deflectorMode = 0; // 0 down, 1 up
	
	public static void init(){
		//intakeButton.whileHeld(new IntakeBallCommand());
		//ballOutButton.whileHeld(new BallOutCommand());
		//toClimberButton.whenPressed(new ToClimberCommand());
		//toIntakeButton.whenPressed(new ToIntakeCommand());
		targetButton.whenReleased((new AimCommand()));
		mode = Mode.PREMATCH;
	}

	public static double getLeftY(){
		return tolerance(joystick.getRawAxis(1), RobotMap.JOYSTICK_TOLERANCE)*DRIVE_SENSITIVITY;
	}
	public static double getLeftX(){
		return tolerance(joystick.getRawAxis(0), RobotMap.JOYSTICK_TOLERANCE)*DRIVE_SENSITIVITY;
	}
	public static double getRightY(){
		return tolerance(joystick.getRawAxis(5), RobotMap.JOYSTICK_TOLERANCE)*DRIVE_SENSITIVITY;
	}
	public static double getRightX(){
		return tolerance(joystick.getRawAxis(4), RobotMap.JOYSTICK_TOLERANCE)*DRIVE_SENSITIVITY;
	}
	
	public static double tolerance(double input, double tolerance){
		if(input>0){
			if(input<tolerance){
				input=0;
			}
		}
		else if(input<0){
			if(input>(-tolerance)){
				input = 0;
			}
		}
		return input;
	}
}	
	
	

