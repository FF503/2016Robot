package frc.robot;

import frc.robot.commands.CancelArmCommand;
import frc.robot.commands.DeflectorCommand;
import frc.robot.commands.ExpelBallCommand;
import frc.robot.commands.ExtendInCommand;
import frc.robot.commands.ExtendOutCommand;
import frc.robot.commands.GoToArmPosition;
import frc.robot.commands.IndexerLoadCommand;
import frc.robot.commands.IntakeBallCommand;
import frc.robot.commands.PIDAimCommand;
import frc.robot.commands.ReverseIndexerCommand;
import frc.robot.commands.ShiftToArmCommand;
import frc.robot.commands.ShiftToHighGearCommand;
import frc.robot.commands.ShiftToLowGearCommand;
import frc.robot.commands.ShiftToWinchCommand;
import frc.robot.commands.ToggleManualShootCommand;
import frc.robot.commands.ToggleShootCommand;
import frc.robot.commands.TurnPIDCommand;
import frc.robot.subsystems.ArmSubsystem.ArmPosition;
import frc.robot.subsystems.VisionProcessor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


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

	//private static Trigger shootButton = new Trigger(operatorJoystick, 2); //imported, was commented
	private static JoystickButton intakeButton = new JoystickButton(driverJoystick, 3);
	private static JoystickButton expelButton = new JoystickButton(driverJoystick, 4);
	private static JoystickButton shiftToLowGearButton = new JoystickButton(driverJoystick, 5);
	private static JoystickButton shiftToHighGearButton = new JoystickButton(driverJoystick, 6);	//eventuall make operator controlled
	private static JoystickButton targetButton = new JoystickButton(driverJoystick, 8);
	private static Button slowMode = new AxisButton(driverJoystick, 3);
	private static Button driveStraightMode = new AxisButton(driverJoystick,2);
	
	private static JoystickButton shooterManualButton = new JoystickButton(operatorJoystick, 10);
	private static JoystickButton goToLoadButton = new JoystickButton(operatorJoystick, 1);
	private static JoystickButton goToIntakeButton = new JoystickButton(operatorJoystick, 2);
	//private static JoystickButton intakeButton = new JoystickButton(driverJoystick, 10);
	private static JoystickButton deflectorButton = new JoystickButton(operatorJoystick, 3);
	//private static JoystickButton goToClimbButton = new JoystickButton(operatorJoystick, 3);
	private static JoystickButton reverseIndexerButton = new JoystickButton(operatorJoystick, 4);
	private static Button indexerButton = new AxisButton(operatorJoystick, 3);

	private static Button shooterButton = new AxisButton(operatorJoystick, 2);	
	
	private static JoystickButton extenderOutButton = new JoystickButton(operatorJoystick, 5);	
	private static JoystickButton climbButton = new JoystickButton(operatorJoystick, 6);
	
	private static JoystickButton shiftToArmButton = new JoystickButton(operatorJoystick, 7);
	private static JoystickButton shiftToWinchButton = new JoystickButton(operatorJoystick, 8);

	
	public static enum Mode{
		PREMATCH,AUTON,TELEOP,ENDGAME,POSTMATCH;
	}
	public static Mode mode;
	
	public static void initialize(){
	
		intakeButton.whenPressed(new IntakeBallCommand());
		expelButton.whenPressed(new ExpelBallCommand());
	    indexerButton.whenReleased(new IndexerLoadCommand());
		shooterButton.whenReleased(new ToggleShootCommand());		
		deflectorButton.whenPressed(new DeflectorCommand());
		shiftToLowGearButton.whenReleased(new ShiftToLowGearCommand());
		shiftToHighGearButton.whenReleased(new ShiftToHighGearCommand());
		shooterManualButton.whenReleased(new ToggleManualShootCommand());
		//targetButton.whenReleased(new PIDAimCommand(VisionProcessor.instance.getAngle()));//new AimCommand()));
		//targetButton.whenReleased((new TurnPIDCommand()));//new AimCommand())); 
		climbButton.whenPressed(new ExtendInCommand());
		shiftToWinchButton.whenPressed(new ShiftToWinchCommand());
		shiftToArmButton.whenPressed(new ShiftToArmCommand());
		goToLoadButton.whenReleased(new GoToArmPosition(ArmPosition.LOAD,false, true));
		goToIntakeButton.whenReleased(new CancelArmCommand());
		reverseIndexerButton.whenPressed(new ReverseIndexerCommand());//new LowerArmCommand()
		//goToOperatorButton.whenReleased(new GoToArmPosition(ArmPosition.OPERATOR));
		extenderOutButton.whenPressed(new ExtendOutCommand());
		
		mode = Mode.PREMATCH;
	}
	
	public static boolean getIntakeButton(){
		return intakeButton.get();
	}
	public static boolean getExpelButton(){
		return expelButton.get();
	}
	public static boolean getShootButton(){
		return shooterButton.get();
	}
	
	public static boolean getExtenderButton(){
		return extenderOutButton.get();
	}
	public static boolean getClimbButton(){
		return climbButton.get();
	}
	public static boolean getReverseIndexerButton(){
		return reverseIndexerButton.get();
	}
	public static boolean getSlowButton(){
		return slowMode.get();
	}
	public static boolean getDriveStraightButton(){
		return driveStraightMode.get();
	}
	public static double getDriverLeftY(){
		return tolerance(driverJoystick.getRawAxis(1), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getDriverLeftX(){
		return tolerance(driverJoystick.getRawAxis(0), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getDriverRightY(){
		return tolerance(driverJoystick.getRawAxis(5), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getDriverRightX(){
		return tolerance(driverJoystick.getRawAxis(4), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getArmMotorPower(){
		return tolerance(operatorJoystick.getRawAxis(1), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getOperatorRightTrigger(){
		return tolerance(operatorJoystick.getRawAxis(3), RobotMap.JOYSTICK_TOLERANCE);
	}
	public static double getOperatorLeftTrigger(){
		return tolerance(operatorJoystick.getRawAxis(2), RobotMap.JOYSTICK_TOLERANCE);
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
