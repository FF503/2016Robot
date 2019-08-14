package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;


public class GoToIntakePosition extends Command {

    public GoToIntakePosition() {
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
    	ArmSubsystem.instance.setArmWinchMotor(RobotMap.ARM_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    	/*return ArmSubsystem.instance.getArmLowerLimitSwitch();*/
    }

    // Called once after isFinished returns true
    protected void end() {
    	ArmSubsystem.instance.setArmWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
