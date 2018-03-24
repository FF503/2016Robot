package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.Robot;
import org.usfirst.frc.team503.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterRPMSubsystem extends PIDSubsystem {
	// roller: 2 CANTalons; encoder on gearbox
	// indexer (also roller): Spike relay ;
	// hood/deflector: Pneumatic
	// encoder (4,5)
	// Proximity switch 1
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public static int deflectorMode = 0; // 0 down, 1 up
	public static TalonSRX rightShootMotor;
	public static TalonSRX leftShootMotor;
	public static TalonSRX indexerMotor;

	public static FeedbackDeviceStatus status;

	// private static DigitalInput indexerProximitySwitch;
	public static Solenoid deflectorShifter;
	public static Encoder shooterEncoder;

	public ShooterRPMSubsystem() {
		super("ShooterSubsystem", RobotMap.Shoot_kP, RobotMap.Shoot_kI, RobotMap.Shoot_kD);
		rightShootMotor = Robot.bot.getRightShooterObj();

		// ALREADY COMMENTED
		// rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		// rightShootMotor.setEncPosition(0); rightShootMotor.setPosition(0);
	

		status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);
		// leftShootMotor = new CANTalon(RobotMap.leftShootMotorPort);
		leftShootMotor = Robot.bot.getLeftShooterObj();

		// CHECK OVER AND SEE ACCUACY, THIS IS THE OLD CODE
		// rightShootMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		// rightShootMotor.setF(0);
		// rightShootMotor.setP(0);
		// rightShootMotor.setI(0);
		// rightShootMotor.setD(0);

		rightShootMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightShootMotor.config_kF(0, 0, 0); // value, port (0), timeout in ms
		rightShootMotor.config_kP(0, 0, 0);
		rightShootMotor.config_kI(0, 0, 0);
		rightShootMotor.config_kD(0, 0, 0);

		// rightShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.changeControlMode(TalonControlMode.Speed);

		status = rightShootMotor.isSensorPresent(FeedbackDevice.QuadEncoder);

		// ALWAYS WAS COMMENTED
		// rightShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.setF(.021);
		// rightShootMotor.setF(.021);

		// rightShootMotor.configEncoderCodesPerRev(1);
		// shooterEncoder = new Encoder(0, 1, false, EncodingType.k4X);
		// shooterEncoder.setDistancePerPulse(1.0/2048);

		indexerMotor = new TalonSRX(RobotMap.indexerMotorPort);
		// indexerProximitySwitch = new DigitalInput(RobotMap.indexerProximitySwitchPort);
		deflectorShifter = new Solenoid(1);
	}

	public static ShooterRPMSubsystem instance = new ShooterRPMSubsystem();

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
			indexerMotor.set(ControlMode.Velocity, RobotMap.SHOOTER_SPEED);
			RobotMap.indexerRunning = true;
		} else {
			SmartDashboard.putString("Indexer Running", "false");
			indexerMotor.set(ControlMode.Velocity, 0);
			RobotMap.indexerRunning = false;
		}
	}

	// WAS ALREADY COMMENTED
	// public boolean isBallIndexed() {
	// return indexerProximitySwitch.get();
	// }

	public void setShooter(double speed) {
		// OLD CODE:
		// rightShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.changeControlMode(TalonControlMode.Speed);
		// leftShootMotor.set(speed);
		// rightShootMotor.set(speed);

		leftShootMotor.set(ControlMode.Velocity, speed);
		rightShootMotor.set(ControlMode.Velocity, speed);

	}

	public void runShooter(boolean run) {
		if (run) {
			SmartDashboard.putString("ShooterRunning", "true");
			leftShootMotor.set(ControlMode.Velocity, RobotMap.SHOOTER_SPEED);
			rightShootMotor.set(ControlMode.Velocity, RobotMap.SHOOTER_SPEED);
			RobotMap.shooterRunning = true;
		} else {
			SmartDashboard.putString("ShooterRunning", "false");
			leftShootMotor.set(ControlMode.Velocity, 0);
			rightShootMotor.set(ControlMode.Velocity, 0);
			RobotMap.shooterRunning = false;
		}
	}

	public void stopShooter() {
		leftShootMotor.set(ControlMode.Velocity, 0);
		rightShootMotor.set(ControlMode.Velocity, 0);
		RobotMap.shooterRunning = false;
	}

	public double getShootSpeedRight() {
		// OLD CODE:
		// return rightShootMotor.getEncVelocity() / 6.0; // velocity is six times rpm
		return rightShootMotor.getSelectedSensorVelocity(0) / 6.0; // velocity is six times rpm

	}

	public double getShootSpeedLeft() {
		// OLD CODE:
		// return leftShootMotor.getEncVelocity() / 6.0;
		return leftShootMotor.getSelectedSensorVelocity(0) / 6.0;
	}

	public double getShootEncoderRight() {
		// OLD CODE:
		// return rightShootMotor.getEncPosition(); // 4096 per revolution
		return rightShootMotor.getSelectedSensorPosition(0); // 4096 per revolution
	}

	public double getShootEncoderLeft() {
		// OLD CODE:
		// return leftShootMotor.getEncPosition();
		return rightShootMotor.getSelectedSensorPosition(0);
	}

	public double getShooterEncoder() {
		return shooterEncoder.getDistance();
	}

	public void initDefaultCommand() {

	}

	// WAS ALREADY COMMENTED OUT
	// @Override
	// protected double returnPIDInput() {
	// // TODO Auto-generated method stub
	// return getShootSpeedLeft(); }
	//
	// @Override
	// protected void usePIDOutput(double output) {
	// RobotMap.shootPIDOutput = output;
	// }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double arg0) {
		// TODO Auto-generated method stub

	}
}
