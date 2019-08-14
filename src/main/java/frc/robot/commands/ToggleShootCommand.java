package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleShootCommand extends Command {

    public ToggleShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.shooterRunning){
    		//ShooterSubsystem.instance.runShooter(false);
    		
    		/*ShooterSubsystem.instance.setSetpoint(0);
    		ShooterSubsystem.instance.disable();*/
    		//command to start shooter at certain speed 
    		ShooterSubsystem.instance.stopShooter();
    	}
    	else{
    		//ShooterSubsystem.instance.runShooter(true);
    		/*ShooterSubsystem.instance.setSetpoint(6500);
    		ShooterSubsystem.instance.enable();*/
    		//command to stop shooter at certain speed
    		ShooterSubsystem.instance.setShooter(RobotMap.SHOOTER_RPM);//was SHOOTER_RPM //6800
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; 
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
