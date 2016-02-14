
package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.OI.Mode;
import org.usfirst.frc.team503.robot.commands.TeleopArcadeDriveCommand;
import org.usfirst.frc.team503.robot.commands.TeleopArmCommand;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team503.robot.subsystems.MicrosoftCameraSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.command.Command; 
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static RobotHardware bot = null;
	public Spark extender1;
	public Encoder climberEncoder;
	Solenoid shifter;
	public CANTalon extender2;
	//Compressor c;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {	
    	//bot = new RobotHardwareProgrammingBot(); //This changes which bot it loads. 
    	bot = new RobotHardwarePracticeBot(); //This changes which bot it loads.
    	bot.initialize();
    	bot.logSmartDashboard();         /*put botname on smartdashboard */ 
    	
    	if (bot.usesDriveCamera()) {
    		MicrosoftCameraSubsystem.instance.setQuality(50);
    		MicrosoftCameraSubsystem.instance.startCapture();
    	}
    	
    	if (bot.usesNavX()) {
    		NavSensorSubsystem.ahrs.reset();	
    	}
    	
    	//AutonSelectorSubsystem.instance.chooseAuton();
    	
    	//DrivetrainSubsystem.driveEncoder.reset();
    	OI.initialize();
    }
	
	public void disabledPeriodic() {
		if (bot.usesNavX()) {
			NavSensorSubsystem.instance.sendDashboardData();
		}
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	OI.mode = Mode.AUTON;
    	//AutonSelectorSubsystem.instance.runSelectedAuton();
    	//(new Test()).start();
        // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
    	if (bot.usesNavX()) {
    		NavSensorSubsystem.instance.sendDashboardData();
    	} 
    	
    	Scheduler.getInstance().run();

    }
    

    public void teleopInit() {
    	(new TeleopArmCommand()).start();

    	//c = new Compressor(0);
    	//c.setClosedLoopControl(true);

		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	OI.mode = Mode.TELEOP;

    	(new TeleopArcadeDriveCommand()).start();
    	//(new TeleopTankDriveCommand()).start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){	
    	OI.mode = Mode.POSTMATCH;
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if (bot.usesNavX()) {
    		NavSensorSubsystem.instance.sendDashboardData();
    	} 
  	
    	SmartDashboard.putNumber("arm encoder counts", ArmSubsystem.instance.getArmEncoderCounts());
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
