package auton;

//import org.usfirst.frc.team503.robot.commands.GoToArmPosition;
//import org.usfirst.frc.team503.robot.commands.LowerArmCommand;
//import org.usfirst.frc.team503.robot.commands.RaiseDeflectorCommand;
//import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutonDoNothing extends CommandGroup {

	public AutonDoNothing() {
		// addSequential(new LowerArmCommand());
		// addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));
		// addParallel(new GoToArmPosition(ArmPosition.LOAD, true, false));
		// addSequential(new PathSorter());
	}
}
