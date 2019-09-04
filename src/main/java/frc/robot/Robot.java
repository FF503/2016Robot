
package frc.robot;

import frc.robot.OI.Mode;
import frc.robot.commands.ShiftToArmCommand;
import frc.robot.commands.TeleopArcadeDriveCommand;
import frc.robot.commands.TeleopArmCommand;
import frc.robot.commands.TeleopClimbCommand;
import frc.robot.commands.TeleopExtenderCommand;
import frc.robot.subsystems.ArmSubsystem;
// import frc.robot.subsystems.AutonSelectorSubsystem;
// import frc.robot.subsystems.MicrosoftCameraSubsystem;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.subsystems.VisionProcessor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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
	public static Timer time;
	//PowerDistributionPanel panel = new PowerDistributionPanel();
	//Compressor c;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {	

    	bot = new RobotHardwareCompBot();  //This changes which bot it loads.
    	bot.initialize();
    	bot.logSmartDashboard();         /*put botname on smartdashboard */
    	
    	if (bot.usesDriveCamera()) {
    		// MicrosoftCameraSubsystem.instance.setQuality(50);
    		// MicrosoftCameraSubsystem.instance.startCapture();
    	}
    	
    	if (bot.usesNavX()) {
    		NavSensorSubsystem.ahrs.reset();	
    		RobotMap.initialHeading = NavSensorSubsystem.ahrs.getFusedHeading();
    	}
    	// AutonSelectorSubsystem.instance.chooseAuton();
    	OI.initialize();
    }
	
	public void disabledPeriodic() {
		if (bot.usesNavX()) {
			//NavSensorSubsystem.instance.sendDashboardData();
		}
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	time = new Timer();
    	time.start();
    	OI.mode = Mode.AUTON;
    	RobotMap.INIT_ANGLE = NavSensorSubsystem.ahrs.getCompassHeading();
    	// AutonSelectorSubsystem.instance.runSelectedAuton();
    	NavSensorSubsystem.ahrs.reset();
    	NewDrivetrainSubsystem.instance.resetEncoder();
    	// schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
    	if (bot.usesNavX()) {
    		//NavSensorSubsystem.instance.sendDashboardData();
    	} 
    		/*if (firstTime = true) {
    		AutonSelectorSubsystem.instance.runSelectedAuton();
    		firstTime = false; 
    	}*/
    	
		if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
			ArmSubsystem.armAngleEncoder.reset();
		}
    	//SmartDashboard.putNumber("ARM PID OUTPUT", RobotMap.armPIDOutput);
		/*SmartDashboard.putNumber("TIMER", time.get());*/
		SmartDashboard.putNumber("Arm Encoder counts", ArmSubsystem.instance.getArmEncoderCounts());
		/*SmartDashboard.putNumber("distance traveled", NewDrivetrainSubsystem.driveEncoder.getDistance());
		SmartDashboard.putNumber("angleTurned", NavSensorSubsystem.ahrs.getYaw());*/
		SmartDashboard.putNumber("ShooterSpeed2", ShooterSubsystem.instance.getShootSpeedRight());
	/*	SmartDashboard.putBoolean("armLowerLimitSwitch", ArmSubsystem.instance.getArmLowerLimitSwitch());
		//SmartDashboard.putNumber("Original Compass Heading", RobotMap.OriginalCompassHeading);
		//89SmartDashboard.putNumber("DistanceToGoal", VisionProcessor.instance.getDriveDistance());
		SmartDashboard.putNumber("GetAngle", VisionProcessor.instance.getAngle());*/
    	Scheduler.getInstance().run();

    }
    

    public void teleopInit() {
    	//(new TestCommandGroup()).start();

    	//c = new Compressor(0);
    	//c.setClosedLoopControl(true);

		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
    	OI.mode = Mode.TELEOP;

    	(new TeleopArcadeDriveCommand()).start();
    	//(new TeleopExtenderCommand()).start();
    	//(new TeleopClimbCommand()).start();
    	(new ShiftToArmCommand()).start();
    	(new TeleopArmCommand()).start();
    	
    	NavSensorSubsystem.ahrs.reset();
    	//(new TeleopTankDriveCommand()).start();
    }
    

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){	
    	OI.mode = Mode.POSTMATCH;
    	ArmSubsystem.instance.armMode();
    	ShooterSubsystem.instance.runShooter(false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//SmartDashboard.putNumber("Current current", panel.getTotalCurrent());
    	// VisionProcessor.instance.refresh();
    	

    	if (bot.usesNavX()) {
    		//NavSensorSubsystem.instance.sendDashboardData();
    	} 
		if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
			ArmSubsystem.armAngleEncoder.reset();
		}
		SmartDashboard.putBoolean("INDEXER RESET", ShooterSubsystem.instance.getIndexSwitch()); 
		SmartDashboard.putBoolean("INTAKE_RUNNING", RobotMap.intakeRunning);
		
		SmartDashboard.putBoolean("Shooter Up To Speed", ShooterSubsystem.instance.shooterUpToSpeed());
		SmartDashboard.putBoolean("SHOOTER_IS_RUNNING", RobotMap.shooterRunning);	
		SmartDashboard.putNumber("ShooterSpeed", ShooterSubsystem.instance.getShootSpeedRight());
		
		SmartDashboard.putBoolean("armLowerLimitSwitch", ArmSubsystem.instance.getArmLowerLimitSwitch());
    	
		
		SmartDashboard.putBoolean("ExtenderIn", ArmSubsystem.instance.getExtenderLowerLimitSwitch());
		SmartDashboard.putBoolean("ExtenderOut", ArmSubsystem.instance.getExtenderUpperLimitSwitch());
		 
		
    	//SmartDashboard.putNumber("DistanceToGoal", VisionProcessor.instance.()); */
    	SmartDashboard.putNumber("Arm Encoder counts", ArmSubsystem.instance.getArmEncoderCounts());
    	
    
    	SmartDashboard.putNumber("Drive Distance=", NewDrivetrainSubsystem.instance.getEncoderDistance());
    	
    	//if(RobotMap.shooterRunning) {
    	//	SmartDashboard.putNumber("Shooter Encoder Speed", ShooterSubsystem.instance.getEncoderSpeed());
    	//} else {
    	//	SmartDashboard.putNumber("Shooter Encoder Speed", 0);
    	//}
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	    	LiveWindow.run();
    }
}
