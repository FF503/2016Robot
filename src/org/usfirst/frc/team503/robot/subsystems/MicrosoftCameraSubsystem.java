package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *fr
 */
public class MicrosoftCameraSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CameraServer server;

	private MicrosoftCameraSubsystem() {
		server = CameraServer.getInstance();
	}
	
	public static MicrosoftCameraSubsystem instance = new MicrosoftCameraSubsystem();

	public void startCapture() {

		server.startAutomaticCapture();// cam2 comp bot, cam0 practice bot
	}
	
	public void setQuality(int qual) {

		server.setQuality(qual);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
