package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.ShooterSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IndexerLoadCommand extends Command {
    boolean IsDone = false;
	Timer time = new Timer();
    public IndexerLoadCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//ShooterSubsystem.instance.indexerSpin();
        RobotMap.indexerRunning = true;
       // time.reset();
       // time.start();
       // while(time.get() < 0.2) {
         //   ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, -.4);
        //}
        //ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, 0.0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        SmartDashboard.putBoolean("Indexer Switch:",ShooterSubsystem.instance.getIndexSwitch());
        
    	// if(time.get()< .2){
        //     System.out.println(ShooterSubsystem.instance.getIndexSwitch());
    	// 	ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, -.4);
    		
    	// 	if(OI.getReverseIndexerButton()){
    	// 		end();
    	// 	}
    	// }
    	// if(ShooterSubsystem.instance.getIndexSwitch()){
        //     System.out.println("Motor should be stopped");
        //     ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, 0.0);
            
           
        	
        // 	if(OI.getReverseIndexerButton()){
        //     		end();
        //     }
        		
        // }
    	// else{
    	// 	end();
        // }

        // if(OI.getReverseIndexerButton()){
        //     end();
        // }
        // if(ShooterSubsystem.instance.getIndexSwitch()){
        //     ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, 0);
        //     IsDone = true;
        // } else {
        //     ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, -.4);
        // }
                ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput,-.4);
            

        
    }
    		
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return IsDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.indexerRunning = false;
    	ShooterSubsystem.indexerMotor.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
