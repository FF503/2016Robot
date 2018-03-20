package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.GoToArmPosition;
import org.usfirst.frc.team503.robot.commands.LowerArmCommand;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */

public class AutonChevalDeFriseCommandGroup extends CommandGroup {

	public AutonChevalDeFriseCommandGroup() {
		// By Leon
		addSequential(new DriveStraightDistanceCommand(46, 2));// 41
		// overshot on purpose according to Mitchell
		addParallel(new DriveStraightDistanceCommand(-2, .5));// maybe -1
		addSequential(new LowerArmCommand());
		addParallel(new DriveStraightDistanceCommand(85, 3));// unsure 81, 111
		addParallel(new GoToArmPosition(ArmPosition.LOAD, true, false));
		addSequential(new WaitCommand(2));
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
