package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

import auton.AutonChevalDeFriseCommandGroup;
import auton.AutonDoNothing;
import auton.AutonDrawbridgeCommandGroup;
import auton.AutonLowBarCommandGroup;
import auton.AutonMoatCommandGroup;
import auton.AutonPortcullisCommandGroup;
import auton.AutonRampartsCommandGroup;
import auton.AutonRockWallCommandGroup;
import auton.AutonRoughTerrainCommandGroup;
import auton.AutonSallyPortCommandGroup;
import auton.AutonTestCommandGroup;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonSelectorSubsystem extends Subsystem {
	/***************************************************
	 * Author: Leon Chen Date: Feb 17, 2016 Purpose: Allow drivers to select which
	 * auton program executes
	 *****************************************************/

	private static SendableChooser autonStartPos;
	private static SendableChooser autonShootPos;
	private static SendableChooser autonDefType;
	private static SendableChooser getPathSorter;

	public static CommandGroup DefCommand;

	private static int iShootPos = 0;
	private static int iStartPos = 0;
	private static int iDefense = 0;
	private static int iPathSorter = 0;

	public AutonSelectorSubsystem() {
		autonStartPos = new SendableChooser();
		autonShootPos = new SendableChooser();
		autonDefType = new SendableChooser();
		getPathSorter = new SendableChooser();

	}

	public static AutonSelectorSubsystem instance = new AutonSelectorSubsystem();

	public static AutonSelectorSubsystem getInstance() {
		return instance;
	}

	public static int getStartpos() {
		iStartPos = (int) autonStartPos.getSelected();
		return iStartPos;
	}

	public static int getShootpos() {
		iShootPos = (int) autonShootPos.getSelected();
		return iShootPos;
	}

	public static int getDefense() {
		iDefense = (int) autonDefType.getSelected();
		return iDefense;
	}

	public static int getPathSorter() {
		iPathSorter = (int) getPathSorter.getSelected();
		return iPathSorter;
	}

	public void chooseAuton() {
		// Autonomous starting position information
		autonStartPos.addObject("StartPos 1", 1);
		autonStartPos.addObject("StartPos 2", 2);
		autonStartPos.addObject("StartPos 3", 3);
		autonStartPos.addObject("StartPos 4", 4);
		autonStartPos.addObject("StartPos 5", 5);
		SmartDashboard.putData("Starting Position Selector", autonStartPos);

		// Autonomous shooting position information
		autonShootPos.addObject("ShootPos 1", 1);// left goal
		autonShootPos.addObject("ShootPos 2", 2);// center goal
		autonShootPos.addObject("ShootPos 3", 3);// right goal
		SmartDashboard.putData("Shooting Position Selector", autonShootPos);

		// Autonomous defensive type information
		autonDefType.addObject("Low Bar", 1);
		autonDefType.addObject("Portcullis", 2);
		autonDefType.addObject("Drawbridge", 3);
		autonDefType.addObject("Rock Wall", 4);
		autonDefType.addObject("Cheval de Frise", 5);
		autonDefType.addObject("Moat", 6);
		autonDefType.addObject("Ramparts", 7);
		autonDefType.addObject("Sally Port", 8);
		autonDefType.addObject("Rough Terrain", 9);
		autonDefType.addObject("Do Nothing", 10);
		autonDefType.addObject("Test Command", 11);
		SmartDashboard.putData("Defense Barrier Selector", autonDefType);

		// Run Autonomous PathSorter?
		getPathSorter.addObject("Shoot High Goal", 1);
		getPathSorter.addObject("Shoot Low Goal", 2);
		getPathSorter.addDefault("Don't Run", 3);
		SmartDashboard.putData("Shoot?", getPathSorter);

	}

	public void runSelectedAuton() {
		// Go get the selector values from SmatDashboard
		SmartDashboard.putNumber("Auton Selector Starting Pos=", getStartpos());
		SmartDashboard.putNumber("Auton Selector Shooting Pos=", getShootpos());
		SmartDashboard.putNumber("Auton Selector Defensive=", getDefense());
		SmartDashboard.putNumber("PathSorter Running=", getPathSorter());

		// Save Current Compass Heading before auton starts
		RobotMap.OriginalCompassHeading = NavSensorSubsystem.ahrs.getCompassHeading();

		// Determine which defense object to created based on selector
		switch (iDefense) {
		case 1:
			DefCommand = new AutonLowBarCommandGroup();
			break;
		case 2:
			DefCommand = new AutonPortcullisCommandGroup();
			break;
		case 3:
			DefCommand = new AutonDrawbridgeCommandGroup();
			break;
		case 4:
			DefCommand = new AutonRockWallCommandGroup();
			break;
		case 5:
			DefCommand = new AutonChevalDeFriseCommandGroup();
			break;
		case 6:
			DefCommand = new AutonMoatCommandGroup();
			break;
		case 7:
			DefCommand = new AutonRampartsCommandGroup();
			break;
		case 8:
			DefCommand = new AutonSallyPortCommandGroup();
			break;
		case 9:
			DefCommand = new AutonRoughTerrainCommandGroup();
			break;
		case 10:
			DefCommand = new AutonDoNothing();
			break;
		case 11:
			DefCommand = new AutonTestCommandGroup();
			break;
		default:
			DefCommand = new AutonTestCommandGroup();
		}

		// run selected command object
		DefCommand.start();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
