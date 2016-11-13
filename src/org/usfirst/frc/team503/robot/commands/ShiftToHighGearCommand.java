package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.VisionProcessor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftToHighGearCommand extends Command {

    public ShiftToHighGearCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.currentGear == true){
    		SmartDashboard.putString("ShiftHigh","False");
    		end();
    	}
    	else{
    		SmartDashboard.putString("ShiftHigh","True");
    		NewDrivetrainSubsystem.instance.shiftGears(true);
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
