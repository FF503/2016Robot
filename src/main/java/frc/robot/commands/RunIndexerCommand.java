/*package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

*//**
 *
 *//*
public class RunIndexerCommand extends Command {
	Timer time;
    public RunIndexerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer();
    	time.start();
    	while(!ShooterSubsystem.instance.getIndexSwitch() || time.get()< .5){
    		ShooterSubsystem.instanceindexerMotor.set(-.4);
    		if(OI.getShootButton()){
    			if(RobotMap.shooterRunning){
    				runShooter(false);
    			}
    			else{
    				runShooter(true);
    			}
    		if(OI.getReverseIndexerButton()){
    			break;
    		}
    		}
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
*/