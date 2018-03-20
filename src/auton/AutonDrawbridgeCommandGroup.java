package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.LowerArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDrawbridgeCommandGroup extends CommandGroup {

	public AutonDrawbridgeCommandGroup() {
		// By Vincent

		addSequential(new DriveStraightDistanceCommand(41, 2));
		addSequential(new LowerArmCommand());
		addParallel(new DriveStraightDistanceCommand(-36, 2));// parallel with the above
		addSequential(new DriveStraightDistanceCommand(36, 2));
		addSequential(new DriveStraightDistanceCommand(81, 3));
		// Robot will be facing opposing alliance's goal
		addSequential(new PathSorter());

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

	}
}