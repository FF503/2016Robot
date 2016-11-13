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
	
	public static double initialHeading;
	public static final int driveEncoderPortA = 2; // Checked and verified = 2  
	public static final int driveEncoderPortB = 3; // Check and Verified = 3 
	public static final boolean driveEncoderReverseDirection = false;
	
	public static final int shooterEncoderPortA = 4;//check  --- now is versa encoder 
	public static final int shooterEncoderPortB = 5;//check  --- now a versa encoder 
	public static final boolean shooterEncoderReverseDirection = false;
	
	public static final int armEncoderPortA = 4;  //Check and verified for arm angle  
	public static final int armEncoderPortB = 5;  //Check and Verified  
	public static final boolean armEncoderReverseDirection = false;

	public static final int extenderEncoderPortA = 6;
	public static final int extenderEncoderPortB = 7;
	public static final boolean extenderEncoderReverseDirection = false;
	
	public static final EncodingType encoderType = EncodingType.k4X;
	
	//public static final double KITBOT_DRIVE_DISTANCE_PER_PULSE = .0908126217;
	//public static final double KITBOT_DRIVE_DISTANCE_PER_PULSE = .0882722;
	public static final double KITBOT_DRIVE_DISTANCE_PER_PULSE = .09813543;
	//public static final double KITBOT_DRIVE_DISTANCE_PER_PULSE = 1;
	public static final double PROBOT_DRIVE_DISTANCE_PER_PULSE = 1.0;
	public static final double DRIVE_LOW_GEAR_DISTANCE_PER_PULSE = 1;
	public static final double DRIVE_HIGH_GEAR_DISTANCE_PER_PULSE = 1;
	public static final double ARM_DISTANCE_PER_PULSE = 1;
	public static final double EXTENDER_DISTANCE_PER_PULSE = 1;
	public static final double SHOOTER_DISTANCE_PER_PULSE = 1;
	
	public static final int drivetrainRightSolenoidPort = 0;
	
	public static boolean deflectorUp = false;
	
	//public static final int rightShootMotorPort = 7;  //Practice Bot = 0, Comp Bot = 7   
	//public static final int leftShootMotorPort = 2;
	
	public static final int indexerMotorPort = 3;       //3 on comp bot 
	public static final double INDEXER_SPEED = 0.6;
	public static final int indexerProximitySwitchPort = 22;

	public static final int armWinchMotorPort = 1; //Practice Bot = 5, Comp Bot = 1 
	public static final int winchSolenoidPort = 2;
    public static final int armSolenoidport = 3;
	
	public static final int extenderSparkPort = 0; //also need to change thingy thing
	
	public static boolean armWinchMode = false; // true is winch, false is arm
	public static boolean deflectorMode = false; // false down, true up
	public static boolean extenderPosition = false; //false is in , true is out
	public static final boolean highGear = false;
	public static final boolean lowGear = true;
	public static boolean currentGear = false; // false is low, true is high
	public static final double LOW_GEAR_DRIVE_SENSITIVITY = .3;
	public static final double DRIVE_SENSITIVITY = .6;
	
	public static final double JOYSTICK_TOLERANCE = .15;
	
	public static final double ARM_SPEED = .75;  // was .6 
	public static final double ARM_PID_RANGE = 5000; //was 1000
	public static final double ARM_SENSITIVITY = .8;
	public static final double ARM_POSITION_TOP = 29000;
	public static final double ARM_POSITION_LOAD = 10000; //10000
	public static final double ARM_POSITION_BOT = 300;
	public static final double ARM_POSITION_PBOT_TOP = 23000;
	
	public static final double INTAKE_TIME = 2.5;
	public static boolean intakeRunning = false;
	public static final double SHOOTER_RPM = 8000;//6600
	public static final double SHOOTER_SPEED = .71;//71 set to 55 on comp bot //was .90 on P bot
	public static final double SHOOT_TIME = 3;
	public static boolean shooterRunning = false;
	
	public static final double EXTEND_WINCH_RATIO = .33;
	public static final int EXTENDER_OUT = 500; // change
	public static final int EXTENDER_IN = 0; // change
	
	public static final double CLIMB_SPEED = 1;   // was 0.8 
	
	public static final double AUTON_DRIVE_SPEED = .8;
	public static final double AUTON_MID_DRIVE_SPEED = .3;
	public static final double AUTON_LOW_DRIVE_SPEED = .1;
	public static final double COMPASS_LOW_TOLERANCE = .75;
	public static final double COMPASS_MID_TOLERANCE = 2;
	public static final double COMPASS_TOLERANCE = 1.5;//3
	public static final double COMPASS_DRIVE_TOLERANCE = 1.5;
	public static double INIT_ANGLE = 0;
	
	public static final double DRIVE_kP = 0.024;//.032 practice --- compbot = .024 //
	public static final double DRIVE_kI = 0.00005;//.00003 practice;  //compbot - .00005
	public static final double DRIVE_kD = 0;
	
	public static final double TURN_kP = 0.025;//.019
	public static final double TURN_kI = 0.00005; 
	public static final double TURN_kD = 0;
	
	public static final double Shoot_kP = 0.0002;
	public static final double Shoot_kI = 0.0001;
	public static final double Shoot_kD = 0;
	

	public static final double ARM_kP = .00008; //.0015
	public static final double ARM_kI = 0.0000003;//0.00003;
	public static final double ARM_kD = 0;

	public static final double DRIVE_PID_TOLERANCE = 1.0; //was 2.5 
	public static final double TURN_PID_TOLERANCE = .5;
	public static final double AUTON_TURN_PID_TOLERANCE = 1.0;
	public static final double ARM_PID_TOLERANCE = 200;
	public static final double ARM_TOLERANCE = 100;	
	
	public static int startPos; 
	public static int shootPos;
	public static boolean FirstTime = true; 
	public static boolean indexerRunning = false; 
	
	public static double AUTON_TURN_kP = .0100; // was .012 
	public static double AUTON_TURN_kI = .00000;  // was .00002 
	public static double AUTON_TURN_kD = 0; 
	
	public static double currentForward = 0;
	public static double currentTurn = 0;
	
	public static double shootPIDOutput = 0;
	public static double drivePIDOutput = 0;
	public static double autonTurnPIDOutput = 0;
	public static double armPIDOutput = 0;
	public static double visionTurnPIDOutput = 0;
	public static double OriginalCompassHeading = 0; 
	public static double YawAfterDefense = 0; 
}
