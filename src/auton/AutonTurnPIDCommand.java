package auton;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.TurnPIDSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonTurnPIDCommand extends Command {
	double angle;
	double timeOutTime;
	boolean lowbar;
	Timer timer;
    public AutonTurnPIDCommand(double deg,double timeOutTime, boolean lowBar) {
    	this.angle = deg;
    	this.lowbar = true;
    	this.timeOutTime = timeOutTime;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public AutonTurnPIDCommand(double deg,double timeOutTime) {
    	this.angle = deg;
    	this.lowbar = false;
    	this.timeOutTime = timeOutTime;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public AutonTurnPIDCommand(double deg){
    	this.lowbar =false;
    	this.angle =deg;
    	this.timeOutTime = 1.5;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	NavSensorSubsystem.ahrs.reset();
    	TurnPIDSubsystem.instance.setSetpoint(angle);  	
    	SmartDashboard.putNumber("Auton ANGLE", angle);
    	TurnPIDSubsystem.instance.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("AutonTurnPIDCommand Ended", "No");
    	if(lowbar){
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.autonTurnPIDOutput, 0, false);
    	}
    	else{
    		NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.autonTurnPIDOutput, RobotMap.autonTurnPIDOutput, false);
    	}
    		SmartDashboard.putNumber("AutonTurnPIDOutPut", RobotMap.autonTurnPIDOutput);
    	SmartDashboard.putNumber("AUTON ANGLE TURNED", NavSensorSubsystem.ahrs.getYaw());
    	//NewDrivetrainSubsystem.instance.tankDrive(-RobotMap.turnPIDOutput, RobotMap.turnPIDOutput, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//SmartDashboard.putBoolean("Auton Turn On Target", Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) < RobotMap.AUTON_TURN_PID_TOLERANCE);
        //return Math.abs(NavSensorSubsystem.ahrs.getAngle() - angle) < RobotMap.TURN_PID_TOLERANCE;
    	SmartDashboard.putBoolean("Auton Turn On Target", TurnPIDSubsystem.instance.onTarget());
    	return TurnPIDSubsystem.instance.onTarget() || timer.get()> timeOutTime; 
    	//return timer.get() > 2;   // KRM we need to put this back if we cant turn the pid command 
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("AutonTurnPIDCommand Ended", "Yes");
    	NewDrivetrainSubsystem.instance.tankDrive(0, 0, true);
    	TurnPIDSubsystem.instance.disable();
    	//NavSensorSubsystem.ahrs.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
