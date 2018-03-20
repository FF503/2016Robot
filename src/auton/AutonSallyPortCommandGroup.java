package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonSallyPortCommandGroup extends CommandGroup {

	public AutonSallyPortCommandGroup() {
		// By Sophie

		addSequential(new DriveStraightDistanceCommand(41, 1.5));
		// addSequential(Lower arm and hook onto port door)
		// addSequential(Pull back and open door)
		addSequential(new DriveStraightDistanceCommand(81, 3));// add the amount moved back later
		addSequential(new PathSorter());

	}
}
