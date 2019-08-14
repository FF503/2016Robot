package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.NavSensorSubsystem;
import frc.robot.subsystems.NewDrivetrainSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class AnilSTurn extends Command {
	double firstAngle;
	double straightInches;
	double secondAngle;
	double toBatter;
	double step = 0;
	double FINAL_DISTANCE = 12;
	Timer time;
	
    public AnilSTurn(double leftAngle, double straightInches, double rightAngle, double toBatter) {
    	this.firstAngle = leftAngle;
    	this.secondAngle = rightAngle;
    	this.straightInches = straightInches;
    	this.toBatter = toBatter;
    	time = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	NavSensorSubsystem.ahrs.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(step <1){
    		if(firstAngle> 0){
    			NewDrivetrainSubsystem.instance.tankDrive(-.8, -.2, false);
    		}
    		else{
    			NewDrivetrainSubsystem.instance.tankDrive(-.2, -.8, false);
    		}
    		if(Math.abs(NavSensorSubsystem.ahrs.getYaw()) > Math.abs(firstAngle)){
    			step = 1;
    			NewDrivetrainSubsystem.instance.resetEncoder();
    		}
    	}
    	else if(step<2){
    		
    		NewDrivetrainSubsystem.instance.tankDrive(-.6, -.6, false);
    		if(NewDrivetrainSubsystem.instance.getEncoderDistance()> straightInches){
    			step = 2;
    		}
    		
    	}
    	else if(step <3){
    		if(firstAngle< 0){
    			NewDrivetrainSubsystem.instance.tankDrive(-.8, -.2, false);
    		}
    		else{
    			NewDrivetrainSubsystem.instance.tankDrive(-.2, -.8, false);
    		}
    		if(NavSensorSubsystem.ahrs.getYaw() > -13&&NavSensorSubsystem.ahrs.getYaw() <13){
    			step = 3;
    			NewDrivetrainSubsystem.instance.resetEncoder();
    		}
    	}
    	/*else if(step<4){
    		NewDrivetrainSubsystem.instance.tankDrive(-.6, -.6, false);
    		if(NewDrivetrainSubsystem.instance.getEncoderDistance()> toBatter){
    			step = 4;
    			time.start();
    		}
    	}
    	else if(step<5){
    		ArmSubsystem.instance.setArmWinchMotor(RobotMap.ARM_SPEED);
    		if(time.get()>2 || ArmSubsystem.instance.getArmLowerLimitSwitch()){
    			step = 5;
    			NewDrivetrainSubsystem.instance.resetEncoder();
    		}
    	}
    	else if(step<6){
    		NewDrivetrainSubsystem.instance.tankDrive(-.6, -.6, false);
    		if(NewDrivetrainSubsystem.instance.getEncoderDistance()> FINAL_DISTANCE){
    			step = 6;
    		
    		}*/
    	}
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return step ==3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	NewDrivetrainSubsystem.instance.tankDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
