package auton;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.LowerArmCommand;
import org.usfirst.frc.team503.robot.commands.RaiseDeflectorCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonPortcullisCommandGroup extends CommandGroup {
    
    public  AutonPortcullisCommandGroup() {
    	//By Vincent
    	
    
    	addSequential(new LowerArmCommand());
    	addSequential(new RaiseDeflectorCommand());
    	addSequential(new DriveStraightDistanceCommand(-150,5));
    	addSequential(new AutonTurnPIDCommand(180,3));
    	//addSequential(new DriveStraightDistanceCommand(7,.5));//pushes arm under the bar
    	//addSequential("Push gate up with the arm");
    	//addParallel("move arm backwards so that gate stays up and doesn't kill the robot as it moves forwards");
    		//Alternate = rotate robot around arm so arm is relatively stationary
    		//rotate back and release arm
    	//addSequential(new DriveStraightDistanceCommand(88,3));//48
    	//addSequential("move arm back down"); (maybe parallel to the above?)
    	//Robot will be facing the opposing alliance's goal
    	//addSequential(new PathSorter());

    	

    }
}