package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterCommand extends Command {
	Timer time;
	double rpm;
    public SetShooterCommand(double rpm) {
    	this.rpm = rpm;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer();
    //	ShooterSubsystem.instance.setSetpoint(rpm);
    //	ShooterSubsystem.instance.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ShooterSubsystem.instance.setShooter(RobotMap.shootPIDOutput);  	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.shooterRunning = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
