package org.usfirst.frc.team503.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static final int driveEncoderPortA = 2; //do not use 0/1, encoder is broken
	public static final int driveEncoderPortB = 3;
	public static final boolean driveEncoderReverseDirection = false;
	
	public static final int shooterEncoderPortA = 4;//check
	public static final int shooterEncoderPortB = 5;//check
	public static final boolean shooterEncoderReverseDirection = false;
	
	public static final int armEncoderPortA = 4;// change
	public static final int armEncoderPortB = 5;//change
	public static final boolean armEncoderReverseDirection = false;

	public static final int extenderEncoderPortA = 6;
	public static final int extenderEncoderPortB = 7;
	public static final boolean extenderEncoderReverseDirection = false;
	
	public static final EncodingType encoderType = EncodingType.k4X;
	
	public static final double KITBOT_DRIVE_DISTANCE_PER_PULSE = .0908126217;
	public static final double PROBOT_DRIVE_DISTANCE_PER_PULSE = 1.0;
	public static final double DRIVE_LOW_GEAR_DISTANCE_PER_PULSE = 1;
	public static final double DRIVE_HIGH_GEAR_DISTANCE_PER_PULSE = 1;
	public static final double ARM_DISTANCE_PER_PULSE = 1;
	public static final double EXTENDER_DISTANCE_PER_PULSE = 1;
	public static final double SHOOTER_DISTANCE_PER_PULSE = 1;
	
	public static final int drivetrainLeftSolenoidPort = 1;
	public static final int drivetrainRightSolenoidPort = 0;
	
	public static final int deflectorSolenoidPortA = 3;
	public static final int deflectorSolenoidPortB = 4;
	
	public static final int rightShootMotorPort = 0;
	public static final int leftShootMotorPort = 2;
	
	public static final int indexerMotorPort = 7;//Update after figuring out port.
	public static final int indexerProximitySwitchPort = 1;

	public static final int armWinchMotorPort = 5; //need to change thingy thing
	public static final int armWinchSolenoidPort = 1; //change
	
	public static final int extenderSparkPort = 0; //also need to change thingy thing
	
	public static boolean armWinchMode = false; // true is winch, false is arm
	public static boolean deflectorMode = false; // false down, true up
	public static boolean extenderPosition = false; //false is in , true is out
	public static boolean currentGear = false; // false is low, true is high
	
	public static final boolean SENSITIVITY = true;
	public static final double DRIVE_SENSITIVITY = 1;
	
	public static final double JOYSTICK_TOLERANCE = .15;
	
	public static final double ARM_SPEED = .5;
	public static final double ARM_PID_RANGE = 15;
	public static final double ARM_TOLERANCE = 2;
	public static final double ARM_SENSITIVITY = .8;
	public static final double ARM_POSITION_TOP = 60;
	public static final double ARM_POSITION_MID = 30;
	public static final double ARM_POSITION_BOT = 0;
	
	public static final double INDEXER_SPEED = 1;
	
	public static final double SHOOTER_SPEED = .5;
	public static final double SHOOT_TIME = 1.5;
	
	public static final double EXTRACT_WINCH_RATIO = .45;
	public static final int EXTENDER_OUT = 500; // change
	public static final int EXTENDER_IN = 0; // change
	
	public static final double CLIMB_SPEED = .7;
	
	public static final double AUTON_DRIVE_SPEED = .7;
	public static final double COMPASS_TOLERANCE = .25;
	
	public static final double DRIVE_kP = 0.02;
	public static final double DRIVE_kI = 0;
	public static final double DRIVE_kD = 0;
	
	public static final double TURN_kP = .01;
	public static final double TURN_kI = 0;
	public static final double TURN_kD = 0;
	
	public static final double ARM_kP = 0;
	public static final double ARM_kI = 0;
	public static final double ARM_kD = 0;

	public static final double DRIVE_PID_TOLERANCE = 2;
	public static final double TURN_PID_TOLERANCE = 2;
	public static final double ARM_PID_TOLERANCE = 2;	
	
	public static double drivePIDOutput = 0;
	public static double armPIDOutput = 0;
	public static double turnPIDOutput = 0;
}
