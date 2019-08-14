package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftToLowGearCommand extends Command {

    public ShiftToLowGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.currentGear == false){
    		end();
    	}
    	else{
    		NewDrivetrainSubsystem.instance.shiftGears(false);
    		end();
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
