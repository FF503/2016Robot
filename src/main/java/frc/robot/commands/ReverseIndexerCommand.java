package frc.robot.commands;

import frc.robot.OI;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseIndexerCommand extends Command {

    public ReverseIndexerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*if(RobotMap.deflectorMode == false){
    		ShooterSubsystem.instance.raiseDeflector();
    	}*/
    	if(OI.getReverseIndexerButton()){
        	ShooterSubsystem.instance.indexerReverse();
        }
        else if(!OI.getReverseIndexerButton()){
        	ShooterSubsystem.instance.indexerStop();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ShooterSubsystem.instance.indexerReverse();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !OI.getReverseIndexerButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	ShooterSubsystem.instance.indexerStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
