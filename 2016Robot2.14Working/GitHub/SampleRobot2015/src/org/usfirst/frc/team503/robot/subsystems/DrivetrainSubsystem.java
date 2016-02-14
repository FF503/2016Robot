package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivetrainSubsystem extends Subsystem {
	CANTalon frontRightMotor;
	CANTalon frontLeftMotor;
	CANTalon backRightMotor;
	CANTalon backLeftMotor;
	private static DrivetrainSubsystem instance = new DrivetrainSubsystem(0,1,2,3);
	public static DrivetrainSubsystem getInstance(){
		return instance;
	}
	
	
	private DrivetrainSubsystem(int frontRightMotor, int frontLeftMotor, int backRightMotor, int backLeftMotor){
		this.frontRightMotor = new CANTalon(frontRightMotor);
		this.frontLeftMotor = new CANTalon(frontLeftMotor);
		this.backRightMotor = new CANTalon(backRightMotor);
		this.backLeftMotor = new CANTalon(backLeftMotor);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
