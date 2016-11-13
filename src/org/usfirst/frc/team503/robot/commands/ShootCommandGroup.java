package org.usfirst.frc.team503.robot.commands;

import org.usfirst.frc.team503.robot.RobotMap;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;
import org.usfirst.frc.team503.robot.subsystems.ShooterSubsystem;

import auton.AutonIntakeCommand;
import auton.AutonShootCommand;
import auton.AutonStopIntakeCommand;
import auton.AutonStopShooterCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootCommandGroup extends CommandGroup {
    
    public  ShootCommandGroup() {
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
        addSequential(new LowerArmCommand());
       	addSequential(new RaiseDeflectorCommand());
       	addSequential(new AutonShootCommand());
    	addSequential(new TurnPIDCommand());
    	addParallel(new GoToArmPosition(ArmPosition.LOAD,false, false));
    	addSequential(new WaitForShooterCommand());
		addParallel(new AutonIntakeCommand());
		addSequential(new GoToArmPosition(ArmPosition.LOAD,true, false));
		addSequential(new WaitCommand(RobotMap.INTAKE_TIME));
		addSequential(new AutonStopShooterCommand());
		addSequential(new AutonStopIntakeCommand());
		}
    }

