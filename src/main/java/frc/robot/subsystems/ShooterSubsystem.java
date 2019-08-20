package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	// roller: 2 CANTalons; encoder on gearbox
	// indexer (also roller): Spike relay ;
	// hood/deflector: Pneumatic
	// encoder (4,5)
	// Proximity switch 1
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public static int deflectorMode = 0; // 0 down, 1 up
	private static TalonSRX rightShootMotor;
	private static TalonSRX leftShootMotor;
	public static TalonSRX indexerMotor;

	private static DigitalInput indexerLimitSwitch;
	private static Solenoid deflectorShifter;
	public static Encoder shooterEncoder;

	private ShooterSubsystem() {
		// super("ShooterSubsystem", RobotMap.Shoot_kP, RobotMap.Shoot_kI, RobotMap.Shoot_kD);
		rightShootMotor = Robot.bot.getRightShooterObj();
		// setOutputRange(.2,.8);
		// setInputRange(-5,11000);


		// rightShootMotor.setEncPosition(0);
		// rightShootMotor.setPosition(0);

		leftShootMotor = Robot.bot.getLeftShooterObj();

		// ORIGINAL CODE, PLS REVIEW
		// rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		// rightShootMotor.configEncoderCodesPerRev(1024); // was 360
		// rightShootMotor.setProfile(0);
		// rightShootMotor.setF(0.004657); // was 0.029 , 0.004657
		// rightShootMotor.setP(.24); // was .3000 ,.2
		// rightShootMotor.setI(.0000025);

		rightShootMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		// rightShootMotor.configEncoderCodesPerRev(1024); // was 360 TalonSRX always uses 1024 unlike CANTalon
		rightShootMotor.selectProfileSlot(0, 0);
		rightShootMotor.config_kF(0, 0.004657, 0); // was 0.029 , 0.004657
		rightShootMotor.config_kP(0, .24, 0); // was .3000 ,.2
		rightShootMotor.config_kI(0, .0000025, 0);
		rightShootMotor.config_kD(0, 0, 0);

		// rightShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.changeControlMode(TalonControlMode.Speed);

		/*
		 * rightShootMotor.changeControlMode(TalonControlMode.Speed); leftShootMotor.changeControlMode(TalonControlMode.Speed); leftShootMotor.setF(.021); rightShootMotor.setF(.021);
		 */

		// shooterEncoder = new Encoder(0, 1, false, EncodingType.k4X);
		// shooterEncoder.setDistancePerPulse(1.0/2048); // should be 1024 for this
		// encoder

		indexerMotor = Robot.bot.getIndexerObj();
		indexerLimitSwitch = new DigitalInput(RobotMap.indexerProximitySwitchPort);
		deflectorShifter = new Solenoid(1);
	}

	public static ShooterSubsystem instance = new ShooterSubsystem();

	public boolean shooterUpToSpeed() {
		return getShootSpeedRight() > 6200;
	}

	public void raiseDeflector() {
		deflectorShifter.set(true);
		RobotMap.deflectorUp = true;
	}

	public void lowerDeflector() {
		deflectorShifter.set(false);
		RobotMap.deflectorUp = false;
	}

	public void setIndexer(boolean run) {
		if (run) {
			SmartDashboard.putString("Indexer Running", "true");
			indexerMotor.set(ControlMode.PercentOutput, -.5);
			RobotMap.indexerRunning = true;
		} else {
			SmartDashboard.putString("Indexer Running", "false");
			indexerMotor.set(ControlMode.PercentOutput, 0);
			RobotMap.indexerRunning = false;
		}
	}

	public boolean getIndexSwitch() {
		return indexerLimitSwitch.get();
	}

	public void indexerSpin() {
		Timer time = new Timer();
		time.start();
		RobotMap.indexerRunning = true;
		while (!getIndexSwitch() || time.get() < .5) {
			// SHOULD IT BE PERCENT OUTPUT
			indexerMotor.set(ControlMode.PercentOutput, -.4);
			if (OI.getShootButton()) {
				if (RobotMap.shooterRunning) {
					runShooter(false);
				} else {
					runShooter(true);
				}
				if (OI.getReverseIndexerButton()) {
					break;
				}
			}
		}
		while (getIndexSwitch()) {
			// SHOULD IT BE PERCENT OUTPUT
			indexerMotor.set(ControlMode.PercentOutput, -.4);
			if (OI.getShootButton()) {
				if (RobotMap.shooterRunning) {
					runShooter(false);
				} else {
					runShooter(true);
				}
			}
			if (OI.getReverseIndexerButton()) {
				break;
			}

		}
		RobotMap.indexerRunning = false;
		// SHOULD IT BE PERCENT OUTPUT
		indexerMotor.set(ControlMode.PercentOutput, 0);
	}

	public void indexerReverse() {
		// SHOULD IT BE PERCENT OUTPUT
		indexerMotor.set(ControlMode.PercentOutput, .4);
	}

	public void indexerStop() {
		indexerMotor.set(ControlMode.PercentOutput, 0);
	}

	public void setShooter(double speed) {
		/*
		 * ORIGINAL CODE: leftShootMotor.changeControlMode(TalonControlMode.Follower); leftShootMotor.set(7); // must be set to talon ID of master (right shooter = 2) leftShootMotor.enableBrakeMode(false);
		 */
		leftShootMotor.set(ControlMode.Follower, 7);
		leftShootMotor.setNeutralMode(NeutralMode.Coast);

		// ORIGINAL CODE:
		// // right is master
		// rightShootMotor.enable();
		// rightShootMotor.enableBrakeMode(false);
		// rightShootMotor.changeControlMode(TalonControlMode.Speed);
		// SmartDashboard.putNumber("Shooter Set To:", speed);
		// rightShootMotor.set(speed);
		// RobotMap.shooterRunning = true;
		// // SmartDashboard.putString("ShooterRunning", "true");

		rightShootMotor.setNeutralMode(NeutralMode.Coast);
		rightShootMotor.set(ControlMode.Velocity, speed);
		RobotMap.shooterRunning = true;
		SmartDashboard.putNumber("Shooter Set To:", speed);

	}

	public void runShooter(boolean run) {
		

		if (run) {
			// SmartDashboard.putBoolean("ShooterRunning", true);
			leftShootMotor.set(ControlMode.PercentOutput, RobotMap.SHOOTER_SPEED);
			rightShootMotor.set(ControlMode.PercentOutput, RobotMap.SHOOTER_SPEED);
			RobotMap.shooterRunning = true;
		} else {
			// SmartDashboard.putBoolean("ShooterRunning", false);
			leftShootMotor.set(ControlMode.PercentOutput, 0);
			rightShootMotor.set(ControlMode.PercentOutput, 0);
			RobotMap.shooterRunning = false;
		}
	}

	public void stopShooter() {

		// ORIGINAL CODE:
		// leftShootMotor.changeControlMode(TalonControlMode.PercentVbus);
		// leftShootMotor.set(0);
		// rightShootMotor.changeControlMode(TalonControlMode.PercentVbus);
		// rightShootMotor.set(0);

		leftShootMotor.set(ControlMode.PercentOutput, 0);
		rightShootMotor.set(ControlMode.PercentOutput, 0);

		// rightShootMotor.disableControl();

		RobotMap.shooterRunning = false;
		SmartDashboard.putString("ShooterRunning", "false");
	}

	public double getShootSpeedRight() {
		try {
			SmartDashboard.putBoolean("Shooter error", false);
			// ORIGINAL CODE:
			// return rightShootMotor.getEncVelocity() / 6.86;
			return rightShootMotor.getSelectedSensorVelocity(0) / 6.86;

		} catch (Exception e) {
			SmartDashboard.putBoolean("Shooter error", true);
			return 6800;
		}
		// return rightShootMotor.getEncVelocity(); //velocity is six times rpm

	}

	public double getShootSpeedLeft() {
		// OLD CODE:
		// return leftShootMotor.getEncVelocity() / 6.0;
		return leftShootMotor.getSelectedSensorVelocity(0) / 6.0;

		// return leftShootMotor.getEncVelocity();

	}

	public double getShootEncoderRight() {
		// OLD CODE:
		// return rightShootMotor.getEncPosition(); // 4096 per revolution

		return rightShootMotor.getSelectedSensorPosition(0); // 4096 per revolution
	}

	public double getShootEncoderLeft() {
		// OLD CODE:
		// return leftShootMotor.getEncPosition();
		return leftShootMotor.getSelectedSensorPosition(0);

	}

	public double getShooterEncoder() {
		return shooterEncoder.getDistance();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	// @Override
	/*
	 * protected double returnPIDInput() { // TODO Auto-generated method stub return getShootSpeedRight(); }
	 * 
	 * @Override protected void usePIDOutput(double output) { // TODO Auto-generated method stub
	 * 
	 * RobotMap.shootPIDOutput = output; }
	 */

}
