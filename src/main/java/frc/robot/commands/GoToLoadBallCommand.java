package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.ArmPosition;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToLoadBallCommand extends Command {

    public GoToLoadBallCommand() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//if(RobotMap.deflectorMode == false){
    	//	end();
    	//}
    	ArmSubsystem.instance.armMode();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ArmSubsystem.instance.setArmWinchMotor(-.5);
    	//IntakeSubsystem.instance.intakeIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ArmSubsystem.instance.getArmEncoderCounts() > 4000;
    	//return ArmSubsystem.instance.getArmAngleEncoderError(ArmPosition.MID) < RobotMap.ARM_TOLERANCE;    
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.setArmWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
