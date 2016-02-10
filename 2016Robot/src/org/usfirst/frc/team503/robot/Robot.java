
package org.usfirst.frc.team503.robot;

import org.usfirst.frc.team503.robot.OI.Mode;
import org.usfirst.frc.team503.robot.commands.TeleopTankDriveCommand;
import org.usfirst.frc.team503.robot.subsystems.AutonSelectorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.MicrosoftCameraSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.VisionProcessor;

import auton.Test;
import edu.wpi.first.wpilibj.IterativeRobot;
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
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {	
    	MicrosoftCameraSubsystem.instance.setQuality(50);
    	MicrosoftCameraSubsystem.instance.startCapture();
    	AutonSelectorSubsystem.instance.chooseAuton();
    	NavSensorSubsystem.ahrs.reset();
    	DrivetrainSubsystem.driveEncoder.reset();
    	OI.init();
    	// instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
    	NavSensorSubsystem.instance.sendDashboardData();
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	OI.mode = Mode.AUTON;
    	AutonSelectorSubsystem.instance.runSelectedAuton();
    	(new Test()).start();
        // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	NavSensorSubsystem.instance.sendDashboardData();
    	Scheduler.getInstance().run();
    	
    	SmartDashboard.putNumber("endoder value", DrivetrainSubsystem.instance.getEncoderDistance());
    	SmartDashboard.putNumber("encoder pulse", DrivetrainSubsystem.instance.getEncoderPulse());
    	SmartDashboard.putNumber("drive pid output", RobotMap.DRIVE_PID_OUTPUT);
    }
    

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	OI.mode = Mode.TELEOP;
    	DrivetrainSubsystem.driveEncoder.reset();
    	//(new TeleopArcadeDriveCommand()).start();
    	(new TeleopTankDriveCommand()).start();
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
    	NavSensorSubsystem.instance.sendDashboardData();
    	//SmartDashboard.putString("areas", VisionProcessor.instance.getAreas());
    	SmartDashboard.putNumber("centerX", VisionProcessor.instance.getCenterX());
    	SmartDashboard.putNumber("distance", VisionProcessor.instance.approximateDistance());
    	SmartDashboard.putNumber("endoder value", DrivetrainSubsystem.instance.getEncoderDistance());
    	SmartDashboard.putNumber("encoder pulse", DrivetrainSubsystem.instance.getEncoderPulse());
    	SmartDashboard.putNumber("dist. to goal", VisionProcessor.instance.approximateDistance());
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
