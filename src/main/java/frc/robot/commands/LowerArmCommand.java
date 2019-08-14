package frc.robot.commands;

import frc.robot.OI;
import frc.robot.OI.Mode;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArmCommand extends Command {
	Timer timer;
    public LowerArmCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(ArmSubsystem.instance);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ArmSubsystem.instance.armMode();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   		ArmSubsystem.instance.setArmWinchMotor(.9);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(ArmSubsystem.instance.getArmLowerLimitSwitch()){
    		//ArmSubsystem.instance.resetEncoder();
    		return true;
    	}
    	else if(OI.mode == Mode.AUTON){
    		return timer.get()>2;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	//ArmSubsystem.instance.resetEncoder();
    	ArmSubsystem.instance.setArmWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	ArmSubsystem.instance.resetEncoder();
    	end();
    }
}
