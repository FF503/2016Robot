package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.OI;
import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;


public class TeleopArcadeDriveCommand extends Command {

    public TeleopArcadeDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getSlowButton()){
    		NewDrivetrainSubsystem.instance.arcadeDrive(.85*OI.getDriverLeftY(), .78*OI.getDriverLeftX(), true);
    		
    	}
    	else if(OI.getDriveStraightButton()){
    		NewDrivetrainSubsystem.instance.tankDrive(OI.getDriverLeftY(), OI.getDriverLeftY(), true);
    	}
    	else{
    		NewDrivetrainSubsystem.instance.arcadeDrive(OI.getDriverLeftY(), OI.getDriverLeftX(), true);
    	}
    	//NewDrivetrainSubsystem.instance.arcadeDrive(OI.getDriverLeftY(), OI.getDriverLeftX(), true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	NewDrivetrainSubsystem.instance.arcadeDrive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
