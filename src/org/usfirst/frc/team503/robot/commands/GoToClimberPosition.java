
package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;

import edu.wpi.first.wpilibj.command.Command;


public class GoToClimberPosition extends Command {

    public GoToClimberPosition() {
    	requires(ArmSubsystem.instance);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//if(RobotMap.deflectorMode == false){
    	//	end();
    	//}
    	ArmSubsystem.instance.armMode();
    	ArmSubsystem.instance.goToArmPosition(ArmPosition.TOP);
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(RobotMap.ARM_POSITION_TOP - ArmSubsystem.instance.getArmEncoderCounts()) < RobotMap.ARM_TOLERANCE; //???????????
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
