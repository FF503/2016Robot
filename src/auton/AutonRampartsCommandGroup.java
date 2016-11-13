package auton;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRampartsCommandGroup extends CommandGroup {
    
    public  AutonRampartsCommandGroup() {
    	//By Jyotsina
    	

    	//addSequential(new LowerArmCommand());//use if more stable than arm up
    	addSequential(new DriveStraightDistanceCommand(181,4));
    	//addSequential(new DriveStraightDistanceCommand(81));//may be 88
    		//may be jolted off course, realign or add more dist.
    	addSequential(new PathSorter());

    	
    }
}
