/*package auton;

import org.usfirst.frc.team503.robot.commands.TurnDegreesCommand;
import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

*//**
 *
 *//*
public class Test extends CommandGroup {
    
    public  Test() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveStraightDistanceCommand(120));
    	//addSequential(new DriveStraightTimeCommand(3));
    	addSequential(new TurnDegreesCommand(90));
    	addSequential(new DriveStraightDistanceCommand(120));
     	addSequential(new TurnDegreesCommand(-90));
    }
}
*/