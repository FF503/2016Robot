package auton;

import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonTestCommandGroup extends CommandGroup {

	public AutonTestCommandGroup() {

		// Get YAW Value from navx and store in Robot map
		// This allow us to realign robot straight after the defense has been crossed

		/*
		 * addSequential(new LowerArmCommand()); addSequential(new
		 * DriveStraightDistanceCommand(200, 4));//210
		 * 
		 * addSequential(new AutonTurnPIDCommand(64,3,true)); addParallel(new
		 * GoToArmPosition(ArmPosition.LOAD, true, false)); addSequential(new
		 * DriveStraightDistanceCommand(65,2)); addParallel(new AutonShootCommand());
		 * addParallel(new LowerArmCommand()); addSequential(new
		 * DriveStraightDistanceCommand(30, 1.5)); addSequential(new
		 * WaitForShooterCommand()); addSequential(new CheckTime()); addSequential(new
		 * IndexerLoadCommand());
		 */
		addSequential(new DriveStraightDistanceCommand(46, 2));// 41

		// addSequential(new LowerArmCommand());
		// addSequential(new AnilSTurn(-21,16,-21,14));
		// addSequential(new AnilSTurn(-21,10,-21,10)); was
		// addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));

		// addSequential(new WaitCommand(1));
		// addSequential(new autonDriveCommand());
		// addSequential(new LowerArmCommand());
		// addSequential(new DriveStraightDistanceCommand(10,1));

		/*
		 * addSequential(new DriveStraightDistanceCommand(46,2));//41 //overshot on
		 * purpose according to Mitchell addParallel(new
		 * DriveStraightDistanceCommand(-2,.5));//maybe -1 addSequential(new
		 * LowerArmCommand()); addParallel(new
		 * DriveStraightDistanceCommand(105,3));//unsure 81, 111 addParallel(new
		 * GoToArmPosition(ArmPosition.LOAD, false, true)); addSequential(new
		 * AnilSTurn(-30, 3, 30, 20)); //*POS4*
		 */ // addSequential(new AnilSTurn(30, 24, -30, 5)); //*POS3*
			// addSequential(new LowerArmCommand());
			// addSequential(new DriveStraightDistanceCommand(24, 2));
		/* addSequential(new PathSorter()); */
		/*
		 * addSequential(new LowerArmCommand()); addSequential(new
		 * GoToArmPosition(ArmPosition.TOP, false, false));
		 */
		// * POS1
		/*
		 * addSequential(new DriveStraightDistanceCommand(80)); addSequential(new
		 * AutonTurnPIDCommand(5)); addSequential(new DriveStraightDistanceCommand(10));
		 * addSequential(new AutonTurnPIDCommand(55)); addSequential(new
		 * autonDriveCommand()); //addParallel(new LowerArmCommand());
		 * 
		 * addSequential(new DriveStraightDistanceCommand(17)); addParallel(new
		 * StayOnBatterCommand()); addSequential(new WaitCommand(5)); // addParallel(new
		 * AutonShootCommand()); // addSequential(new WaitForShooterCommand()); //
		 * addSequential(new IndexerLoadCommand()); // addSequential(new
		 * WaitCommand(1)); // addSequential(new AutonStopShooterCommand());
		 * addSequential(new StopMotorsCommand());
		 */
		// *POS1

		// *POS2*
		/*
		 * addSequential(new DriveStraightDistanceCommand(108)); addSequential(new
		 * AutonTurnPIDCommand(60)); addSequential(new autonDriveCommand());
		 * //addParallel(new LowerArmCommand());
		 * 
		 * addSequential(new DriveStraightDistanceCommand(17)); addParallel(new
		 * StayOnBatterCommand()); addSequential(new WaitCommand(5)); // addParallel(new
		 * AutonShootCommand()); // addSequential(new WaitForShooterCommand()); //
		 * addSequential(new IndexerLoadCommand()); // addSequential(new
		 * WaitCommand(1)); // addSequential(new AutonStopShooterCommand());
		 * addSequential(new StopMotorsCommand());
		 */
		// *POS 2*

		/*
		 * //*POS3 addSequential(new autonSturnCommand(60,-.6, 63, 22, 33);
		 * addSequential(new autonDriveCommand()); //addParallel(new LowerArmCommand());
		 * //addSequential(new AutonTurnPIDCommand(-5)); addSequential(new
		 * DriveStraightDistanceCommand(17)); addParallel(new StayOnBatterCommand());
		 * addSequential(new WaitCommand(5)); // addParallel(new AutonShootCommand());
		 * // addSequential(new WaitForShooterCommand()); // addSequential(new
		 * IndexerLoadCommand()); // addSequential(new WaitCommand(1)); //
		 * addSequential(new AutonStopShooterCommand()); addSequential(new
		 * StopMotorsCommand());
		 */
		// *POS3

		/*
		 * //*POS4 addSequential(new autonSturnCommand(-70,.7,54,22,24));
		 * addSequential(new autonDriveCommand()); //addParallel(new LowerArmCommand());
		 * //addSequential(new AutonTurnPIDCommand(5)); addSequential(new
		 * DriveStraightDistanceCommand(17)); addParallel(new StayOnBatterCommand());
		 * addSequential(new WaitCommand(5)); // addParallel(new AutonShootCommand());
		 * // addSequential(new WaitForShooterCommand()); // addSequential(new
		 * IndexerLoadCommand()); // addSequential(new WaitCommand(1)); //
		 * addSequential(new AutonStopShooterCommand()); addSequential(new
		 * StopMotorsCommand()); //POS4
		 */
		/*
		 * // *POS 5 addSequential(new DriveStraightDistanceCommand(126));
		 * addSequential(new AutonTurnPIDCommand(-50)); addSequential(new
		 * autonDriveCommand()); //addParallel(new LowerArmCommand());
		 * //addSequential(new AutonTurnPIDCommand(-5)); addSequential(new
		 * DriveStraightDistanceCommand(17)); addParallel(new StayOnBatterCommand());
		 * addSequential(new WaitCommand(5)); // addParallel(new AutonShootCommand());
		 * // addSequential(new WaitForShooterCommand()); // addSequential(new
		 * IndexerLoadCommand()); // addSequential(new WaitCommand(1)); //
		 * addSequential(new AutonStopShooterCommand()); addSequential(new
		 * StopMotorsCommand()); //*POS5*
		 */

	}
}
