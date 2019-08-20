package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IndexerLoadCommand extends Command {
	Timer time = new Timer();
    public IndexerLoadCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//ShooterSubsystem.instance.indexerSpin();
    	RobotMap.indexerRunning = true;
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(time.get()< .5){
    		ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, -.4);
    		
    		if(OI.getReverseIndexerButton()){
    			end();
    		}
    	}
    	else if(ShooterSubsystem.instance.getIndexSwitch()){
        	ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, -.4);
        	
        	if(OI.getReverseIndexerButton()){
            		end();
            }
        		
        }
    	else{
    		end();
    	}
    }
    		
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !RobotMap.indexerRunning;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.indexerRunning = false;
    	ShooterSubsystem.indexerMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
