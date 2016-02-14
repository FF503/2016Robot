/*package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.subsystems.AutonSelectorSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

*//**
 *
 *//*
public class AutonLowBarCommandGroup extends CommandGroup {
    
    public  AutonLowBarCommandGroup() {
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

    	 addSequential(new DriveStraightDistanceCommand (41));
    	 addSequential(new DriveStraightDistanceCommand (30));
    	//  addSequential(new TurnDegreesCommand (90));
    	//	if (AutonSelectorSubsystem.ShootPos == 2) {
//		   	 addSequential(new DriveStraightDistanceCommand (122));
//		   	 addSequential(new TurnDegreesCommand (90));
//		   	 addSequential(new DriveStraightDistanceCommand (132));
//		   	 //addSequential(new TurnDegreesCommand (-90));
//		   	 }  
 //   	if (AutonSelectorSubsystem.ShootPos == 1) { 
//		  	addSequential(new DriveStraightDistanceCommand (122));
//		  	addSequential(new DriveStraightDistanceCommand (67));
//		  	addSequential(new TurnDegreesCommand (60));
 //   	}
  // 
  // 	 	if (AutonSelectorSubsystem.ShootPos == 3) {
  // 	 		addSequential(new DriveStraightDistanceCommand (122));
  // 	 		addSequential(new DriveStraightDistanceCommand (36));
  // 	 		addSequential(new TurnDegreesCommand (90));
  // 	 		addSequential(new DriveStraightDistanceCommand (200));
  // 	 		addSequential(new TurnDegreesCommand (120));
  // 	 	}

    }  // End Low bar 
}  // End Class 
*/