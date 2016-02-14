/*package org.usfirst.frc.team503.robot.subsystems;


import org.usfirst.frc.team503.robot.commands.AutonLowBarCommandGroup;

//import auton.Test;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

*//**
 *
 *//*
public class AutonSelectorSubsystem extends Subsystem {
    
	public static int StartPos;
	private static SendableChooser autonStartPos;
	public static int ShootPos;
	private static SendableChooser autonShootPos;
	
	public static CommandGroup DefType;
	private static SendableChooser autonDefType;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private AutonSelectorSubsystem (){
		autonStartPos = new SendableChooser();
		autonShootPos = new SendableChooser();
		autonDefType = new SendableChooser();
		StartPos = 1;
		ShootPos = 1;
	}
	
	public static AutonSelectorSubsystem instance = new AutonSelectorSubsystem();
	
	public void chooseAuton(){
    	//Autonomous starting position information
        autonStartPos.addDefault("StartPos 1 beacuse", 1);
        autonStartPos.addObject("StartPos 2", 2);
        autonStartPos.addObject("StartPos 3", 3);
        autonStartPos.addObject("StartPos 4", 4);
        autonStartPos.addObject("StartPos 5", 5);
        SmartDashboard.putData("Starting Position Selector", autonStartPos);
        
        //Autonomous shooting position information
        autonShootPos.addDefault("ShootPos 1 why ", 1);
        autonShootPos.addObject("ShootPos 2", 2);
        autonShootPos.addObject("ShootPos 3", 3);
        SmartDashboard.putData("Shooting Position Selector", autonShootPos);
        
        //Autonomous defensive type information
    //    autonDefType.addDefault("Portcullis", 1);
    //    autonDefType.addObject("Cheval De Frise", 2);
    //    autonDefType.addObject("Moat", 3);
    //    autonDefType.addObject("Ramparts", 4);
    //    autonDefType.addObject("Drawbridge", 5);
    //    autonDefType.addObject("Sally Port", 6);
    //    autonDefType.addObject("Rock Wall", 7);
    //    autonDefType.addObject("Rough Terrain", 8);
        autonDefType.addObject("Low Bar", new AutonLowBarCommandGroup());
        SmartDashboard.putData("Defense Barrier Selector", autonDefType);	
	}
	
	public void runSelectedAuton(){
		StartPos = (int) autonStartPos.getSelected();
        ShootPos = (int) autonShootPos.getSelected();
        DefType =  (CommandGroup) autonDefType.getSelected();
        
        SmartDashboard.putNumber("Starting Position Selected ", StartPos);
        SmartDashboard.putNumber("Shooting Position Selected ", ShootPos);
        SmartDashboard.putData("Defensive Barrier Selected ", DefType);
        DefType.start();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}*/