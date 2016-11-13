package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.GoToArmPosition;
import org.usfirst.frc.team503.robot.commands.IndexerLoadCommand;
import org.usfirst.frc.team503.robot.commands.LowerArmCommand;
import org.usfirst.frc.team503.robot.commands.WaitForShooterCommand;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLowBarCommandGroup extends CommandGroup {
	
    public  AutonLowBarCommandGroup() {
    	
/*    	By Zoe
    	REFRENCES
    	Distance between center line and obstacle close ramp ~86.36 in.
    	Distance between center line and obstacle end ramp ~133.61
    	Width of Obstacles (short side) ~47.25
    	Distance between auto start line and center line ~12 in.
    	Distance between auto start line and obstacle far ramp ~76.36 in.
    	Already brings it to the obstacle*/
    	
    	/*SmartDashboard.putString("AutonLowbar", "is now running");
    	SmartDashboard.putNumber("LowBar Start",AutonSelectorSubsystem.instance.getStartpos());
    	SmartDashboard.putNumber("LowBar Shoot",AutonSelectorSubsystem.instance.getShootpos());
    	SmartDashboard.putNumber("LowBar Defense",AutonSelectorSubsystem.instance.getDefense());*/
    	
    	//addSequential(new LowerDeflectorCommand());
    	addSequential(new LowerArmCommand());
    	addSequential(new DriveStraightDistanceCommand(200, 4));//210
 	
    	addSequential(new AutonTurnPIDCommand(64,3,true));
    	addParallel(new GoToArmPosition(ArmPosition.LOAD, true, false));
    	addSequential(new DriveStraightDistanceCommand(65,2));
    	addParallel(new AutonShootCommand());
    	addParallel(new LowerArmCommand());
    	addSequential(new DriveStraightDistanceCommand(30, 1.5));
    	addSequential(new WaitForShooterCommand());
    	addSequential(new CheckTime());
    	addSequential(new IndexerLoadCommand());
    	/*addSequential(new LowerArmCommand());
     	addSequential(new DriveStraightDistanceCommand(131,3));//121
        addSequential(new PathSorter());*/
     	//addSequential(new DriveStraightDistanceCommand(41));
    	//addSequential(new DriveStraightDistanceCommand(81));
    	//addSequential(new GoToArmPosition(ArmPosition.LOAD));
	}  // End Low bar
}   // End Class 
