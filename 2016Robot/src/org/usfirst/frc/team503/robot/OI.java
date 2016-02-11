package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.commands.AimCommand;
import org.usfirst.frc.team503.robot.commands.ClimbCommandGroup;
import org.usfirst.frc.team503.robot.commands.ClimberExtenderInCommand;
import org.usfirst.frc.team503.robot.commands.ClimberExtenderOutCommand;
import org.usfirst.frc.team503.robot.commands.ExpelBallCommand;
import org.usfirst.frc.team503.robot.commands.GoToClimberPosition;
import org.usfirst.frc.team503.robot.commands.GoToIntakePosition;
import org.usfirst.frc.team503.robot.commands.GoToLoadBallCommand;
import org.usfirst.frc.team503.robot.commands.IntakeBallCommand;
import org.usfirst.frc.team503.robot.commands.LowerDeflectorCommand;
import org.usfirst.frc.team503.robot.commands.RaiseDeflectorCommand;
import org.usfirst.frc.team503.robot.commands.ShiftToArmCommand;
import org.usfirst.frc.team503.robot.commands.ShiftToHighGearCommand;
import org.usfirst.frc.team503.robot.commands.ShiftToLowGearCommand;
import org.usfirst.frc.team503.robot.commands.ShiftToWinchCommand;
import org.usfirst.frc.team503.robot.commands.ShootCommand;
import org.usfirst.frc.team503.robot.commands.TeleopArcadeDriveCommand;
import org.usfirst.frc.team503.robot.commands.TeleopArmCommand;
import org.usfirst.frc.team503.robot.commands.TeleopTankDriveCommand;

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
	
	private static Joystick driverJoystick = new Joystick(0);
	private static Joystick operatorJoystick = new Joystick(1);
	
	private static JoystickButton shiftToLowGearButton = new JoystickButton(driverJoystick, 5);
	private static JoystickButton shiftToHighGearButton = new JoystickButton(driverJoystick, 6);
	private static JoystickButton intakeButton = new JoystickButton(driverJoystick, 3);
	private static JoystickButton expelButton = new JoystickButton(driverJoystick, 4);
	private static JoystickButton lowerDeflectorButton = new JoystickButton(driverJoystick, 2);
	private static JoystickButton raiseDeflectorButton = new JoystickButton(driverJoystick, 1);
	
	private static JoystickButton climbButton = new JoystickButton(operatorJoystick, 4);
	private static JoystickButton targetButton = new JoystickButton(operatorJoystick, 5);
	private static JoystickButton shootButton = new JoystickButton(operatorJoystick, 6);
	private static JoystickButton shiftToWinchButton = new JoystickButton(operatorJoystick, 8);
	private static JoystickButton shiftToArmButton = new JoystickButton(operatorJoystick, 7);
	private static JoystickButton goToLoadButton = new JoystickButton(operatorJoystick, 1);
	private static JoystickButton goToIntakeButton = new JoystickButton(operatorJoystick, 2);
	private static JoystickButton goToClimbButton = new JoystickButton(operatorJoystick, 3);
	
	private static JoystickButton extenderOutButton = new JoystickButton(driverJoystick, 7);
	private static JoystickButton extenderInButton = new JoystickButton(driverJoystick, 8);
	
	public static enum Mode{
		PREMATCH,AUTON,TELEOP,ENDGAME,POSTMATCH;
	}
	public static Mode mode;
	
	public static void initialize(){
		intakeButton.whileHeld(new IntakeBallCommand());
		expelButton.whileHeld(new ExpelBallCommand());
		lowerDeflectorButton.whenReleased(new LowerDeflectorCommand());
		raiseDeflectorButton.whenReleased(new RaiseDeflectorCommand());
		shiftToLowGearButton.whenReleased(new ShiftToLowGearCommand());
		shiftToHighGearButton.whenReleased(new ShiftToHighGearCommand());
		
		targetButton.whenReleased((new AimCommand()));
		shootButton.whenReleased(new ShootCommand());
		climbButton.whenReleased(new ClimbCommandGroup());
		shiftToWinchButton.whenReleased(new ShiftToWinchCommand());
		shiftToArmButton.whenReleased(new ShiftToArmCommand());
		goToLoadButton.whenReleased(new GoToLoadBallCommand());
		goToIntakeButton.whenReleased(new GoToIntakePosition());
		goToClimbButton.whenReleased(new GoToClimberPosition());
		
		extenderOutButton.whenReleased(new ClimberExtenderOutCommand());
		extenderInButton.whenReleased(new ClimberExtenderInCommand());
		
		(new TeleopArmCommand()).start();
		(new TeleopArcadeDriveCommand()).start();
		//(new TeleopTankDriveCommand()).start();
		
		mode = Mode.PREMATCH;
	}
	
	public static boolean getIntakeButton(){
		return intakeButton.get();
	}
	public static boolean getExpelButton(){
		return expelButton.get();
	}

	public static double getDriverLeftY(){
		return tolerance(driverJoystick.getRawAxis(1), RobotMap.JOYSTICK_TOLERANCE)* RobotMap.DRIVE_SENSITIVITY;
	}
	public static double getDriverLeftX(){
		return tolerance(driverJoystick.getRawAxis(0), RobotMap.JOYSTICK_TOLERANCE)* RobotMap.DRIVE_SENSITIVITY;
	}
	public static double getDriverRightY(){
		return tolerance(driverJoystick.getRawAxis(5), RobotMap.JOYSTICK_TOLERANCE)* RobotMap.DRIVE_SENSITIVITY;
	}
	public static double getDriverRightX(){
		return tolerance(driverJoystick.getRawAxis(4), RobotMap.JOYSTICK_TOLERANCE)* RobotMap.DRIVE_SENSITIVITY;
	}
	
	public static double getArmMotorPower(){
		return tolerance(operatorJoystick.getRawAxis(1), RobotMap.JOYSTICK_TOLERANCE) * RobotMap.ARM_SENSITIVITY;
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
	
	

