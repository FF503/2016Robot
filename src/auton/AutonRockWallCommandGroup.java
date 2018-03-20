package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.ShiftToLowGearCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonRockWallCommandGroup extends CommandGroup {

	public AutonRockWallCommandGroup() {
		// By Vincent

		// addSequential(new LowerArmCommand());//use if more stable than arm up
		// addSequential(new LowerArmCommand());
		addSequential(new ShiftToLowGearCommand());
		addSequential(new DriveStraightDistanceCommand(155, 4));// 180
		// addParallel(new GoToArmPosition(ArmPosition.LOAD,false));
		// addSequential(new DriveStraightDistanceCommand(100));//may be 88?
		// Fast, to drive over high wall
		// Check position? Move forwards more if not in position?
		// in case of being caught on wall, maybe shake arms
		addSequential(new PathSorter());

	}
}
