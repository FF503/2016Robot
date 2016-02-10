package org.usfirst.frc.team503.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonSelectorSubsystem extends Subsystem {
    
	private static int StartPos;
	private static SendableChooser autonStartPos;
	private static int ShootPos;
	private static SendableChooser autonShootPos;
	private static int DefType;
	private static SendableChooser autonDefType;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AutonSelectorSubsystem (){
		autonStartPos = new SendableChooser();
		autonShootPos = new SendableChooser();
		autonDefType = new SendableChooser();
		StartPos = 1;
		ShootPos = 1;
		DefType = 1;
	}
	
	public static AutonSelectorSubsystem instance = new AutonSelectorSubsystem();
	
	public void chooseAuton(){
    	//Autonomous starting position information
        autonStartPos.addDefault("StartPos 1", 1);
        autonStartPos.addObject("StartPos 2", 2);
        autonStartPos.addObject("StartPos 3", 3);
        autonStartPos.addObject("StartPos 4", 4);
        autonStartPos.addObject("StartPos 5", 5);
        SmartDashboard.putData("Starting Position Selector", autonStartPos);
        
        //Autonomous shooting position information
        autonShootPos.addDefault("ShootPos 1", 1);
        autonShootPos.addObject("ShootPos 2", 2);
        autonShootPos.addObject("ShootPos 3", 3);
        SmartDashboard.putData("Shooting Position Selector", autonShootPos);
        
        //Autonomous defensive type information
        autonDefType.addDefault("Defense 1", 1);
        autonDefType.addObject("Defense 2", 2);
        autonDefType.addObject("Defense 3", 3);
        autonDefType.addObject("Defense 4", 4);
        autonDefType.addObject("Defense 5", 5);
        autonDefType.addObject("Defense 6", 6);
        autonDefType.addObject("Defense 7", 7);
        autonDefType.addObject("Defense 8", 8);
        autonDefType.addObject("Defense 9", 9);
        SmartDashboard.putData("Defense Barrier Selector", autonDefType);	
	}
	
	public void runSelectedAuton (){
		StartPos = (int) autonStartPos.getSelected();
        ShootPos = (int) autonShootPos.getSelected();
        DefType = (int) autonDefType.getSelected();
        SmartDashboard.putNumber("Starting Position Selected ", StartPos);
        SmartDashboard.putNumber("Shooting Position Selected ", ShootPos);
        SmartDashboard.putNumber("Defensive Barrier Selected ", DefType);
        
        switch(DefType){
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        }
        
        switch(StartPos){
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
        
        switch(ShootPos){
        case 1:
        case 2:
        case 3:
        }
	}
	
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

