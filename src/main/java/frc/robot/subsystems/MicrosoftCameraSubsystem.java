package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MicrosoftCameraSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CameraServer server;
	
	private MicrosoftCameraSubsystem(){
		server = CameraServer.getInstance();
	}
	
	public static MicrosoftCameraSubsystem instance = new MicrosoftCameraSubsystem();
	
	public void startCapture(){
		server.startAutomaticCapture("cam0");//cam2 comp bot, cam0 practice bot
	}
	
	public void setQuality(int qual){
		server.setQuality(qual);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

