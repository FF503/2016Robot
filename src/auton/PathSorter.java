package auton;


import org.usfirst.frc.team503.robot.commands.AnilSTurn;
import org.usfirst.frc.team503.robot.commands.DriveStraightDistanceCommand;
import org.usfirst.frc.team503.robot.commands.GoToArmPosition;
import org.usfirst.frc.team503.robot.commands.IndexerLoadCommand;
import org.usfirst.frc.team503.robot.commands.LowerArmCommand;
import org.usfirst.frc.team503.robot.commands.RaiseDeflectorCommand;
import org.usfirst.frc.team503.robot.commands.StayOnBatterCommand;
import org.usfirst.frc.team503.robot.commands.StopMotorsCommand;
import org.usfirst.frc.team503.robot.commands.WaitForShooterCommand;
import org.usfirst.frc.team503.robot.commands.autonDriveCommand;
import org.usfirst.frc.team503.robot.commands.autonSturnCommand;
import org.usfirst.frc.team503.robot.subsystems.ArmSubsystem.ArmPosition;
import org.usfirst.frc.team503.robot.subsystems.AutonSelectorSubsystem;
import org.usfirst.frc.team503.robot.subsystems.NavSensorSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/** 
 * @author Garrett Ray 
 * @start date 2/13/16
 * @last updated: 2/17/16
 */


//does high goal
public class PathSorter extends CommandGroup{
   public PathSorter(){
   	int shootPos = AutonSelectorSubsystem.instance.getShootpos(); 
   	int startPos = AutonSelectorSubsystem.instance.getStartpos(); 
   	int usePathSorter = AutonSelectorSubsystem.instance.getPathSorter();
	double deg = -NavSensorSubsystem.ahrs.getYaw();   
   	/*SmartDashboard.putNumber("Pathsorter startpos=", startPos);
   	SmartDashboard.putNumber("Pathsorter shootpos=" ,shootPos);*/
   	
   	/*addSequential(new LowerArmCommand());
   	addParallel(new GoToArmPosition(ArmPosition.LOAD,false));*/
if(usePathSorter == 1 || usePathSorter == 2 ){
	//take out usePathSorter == 3
   	
   	switch(startPos){
       
       	case 1:
       		
         switch (shootPos){
          case 1:
        	addSequential(new RaiseDeflectorCommand());
        	addSequential(new DriveStraightDistanceCommand(80,2));
          	addSequential(new AutonTurnPIDCommand(10));
          	addSequential(new DriveStraightDistanceCommand(10,.5));
          	addSequential(new AutonTurnPIDCommand(55));
          	addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));
          	addSequential(new DriveStraightDistanceCommand(36,1.5));
          	//addSequential(new autonDriveCommand());
          	addParallel(new LowerArmCommand());
  			break;
  			
          case 2:
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(132,3.3));
  			addSequential(new AutonTurnPIDCommand(-90));
  			break;
        	
          case 3:
  			addSequential(new DriveStraightDistanceCommand(36,1.5));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(200,4));
  			addSequential(new AutonTurnPIDCommand(-120));
  			break;
          }
         break;
         
        case 2:
        	
         switch (shootPos){
          case 1:
        	addSequential(new RaiseDeflectorCommand());
        	addSequential(new LowerArmCommand());
        	addSequential(new GoToArmPosition(ArmPosition.LOAD, false, true));
        	addSequential(new DriveStraightDistanceCommand(100,2.5));
          	addSequential(new AutonTurnPIDCommand(57));
          	addSequential(new autonDriveCommand());
          	addParallel(new LowerArmCommand());
  			break;
  			
          case 2:
  			//addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(42,1.5));
  			addSequential(new AutonTurnPIDCommand(30));
  			break;
  			
          case 3:
  			addSequential(new DriveStraightDistanceCommand(36,1));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(150,3));
  			addSequential(new AutonTurnPIDCommand(-120));
  			break;
         }
        break; 
        
        case 3:
        	
         switch (shootPos){
          case 1:
  			addSequential(new AutonTurnPIDCommand(-90));
  			addSequential(new DriveStraightDistanceCommand(50,1.5));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(96,3));
  			addSequential(new AutonTurnPIDCommand(60));
  			break;
  			
          case 2:
        	addSequential(new LowerArmCommand());
            //addSequential(new autonSturnCommand(60,-.6, 63, 22, 33));
            addParallel(new RaiseDeflectorCommand());
            addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));
            addSequential(new AnilSTurn(33,24,33,16));  //was 33,10,33,10 
            addSequential(new WaitCommand(.5));
          	addSequential(new autonDriveCommand());
          	addParallel(new LowerArmCommand());
          	//addSequential(new AutonTurnPIDCommand(-5));
  			break;
  			
          case 3:
  			addSequential(new DriveStraightDistanceCommand(36,1));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(100,2.5));
  			addSequential(new AutonTurnPIDCommand(-120));
  			break;
  			
         }
         break;
         
        case 4:
        	
         switch (shootPos){
          case 1:
  			addSequential(new AutonTurnPIDCommand(-90));
  			addSequential(new DriveStraightDistanceCommand(100,2.5));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(96,2.5));
  			addSequential(new AutonTurnPIDCommand(60));
  			break;
  			
          case 2:
        	  addSequential(new LowerArmCommand());
        	  addSequential(new WaitCommand(.5));
              addParallel(new RaiseDeflectorCommand());
              addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));
          	  addSequential(new AnilSTurn(-21,15,-21,14));
              addSequential(new WaitCommand(.5));
              addSequential(new autonDriveCommand());
              addParallel(new LowerArmCommand());
          	
        	
  			break;
  			
          case 3:
        	
  			//addSequential(new DriveStraightDistanceCommand(36));
  			addSequential(new AutonTurnPIDCommand(60));
  			addSequential(new DriveStraightDistanceCommand(60,2));
  			addSequential(new AutonTurnPIDCommand(-120));
  			break;
  			
         }
         break;
         
        case 5:
        	
         switch (shootPos){
          case 1:
  			addSequential(new AutonTurnPIDCommand(-90));
  			addSequential(new DriveStraightDistanceCommand(150,4));
  			addSequential(new AutonTurnPIDCommand(90));
  			addSequential(new DriveStraightDistanceCommand(96,3));
  			addSequential(new AutonTurnPIDCommand(60));
  			break;
  			
          case 2:
  			//addSequential(new AutonTurnPIDCommand(-90));
  			//addSequential(new DriveStraightDistanceCommand(50));
  			//addSequential(new AutonTurnPIDCommand(90));
  			break;
  			
          case 3:
        	addSequential(new RaiseDeflectorCommand());
        	addSequential(new LowerArmCommand());
        	addParallel(new GoToArmPosition(ArmPosition.LOAD, false, false));
        	addSequential(new DriveStraightDistanceCommand(124,3));
        	
          	addSequential(new AutonTurnPIDCommand(-60,2.5, false));
          	//addSequential(new GoToArmPosition(ArmPosition.TOP, false, false));
          	//addSequential(new autonDriveCommand());
          	addParallel(new LowerArmCommand());
          	
  			break;
  			
         }
         break;

        }
   	

   	
	switch (shootPos){
   	
   	case 1:
   		addSequential(new DriveStraightDistanceCommand(160,4));//not sure, may be 173
   		break;
   		
   	case 2:
   		if (usePathSorter == 1){
   	//		addSequential(new DriveStraightDistanceCommand(137));
   	//		addSequential(new autonDriveCommand());
   	//		addSequential(new DriveStraightDistanceCommand(24));
   //			addSequential(new GoToArmPosition(ArmPosition.BOT, true , false));
   		}
   		break;
   		
   	case 3:
   		addSequential(new DriveStraightDistanceCommand(163,4));//not sure
   		break;
   		}

	}
	
 	switch(usePathSorter){   
    case 1:
    	addParallel(new DriveStraightDistanceCommand(20,3));  //was17
     	
    	addParallel(new AutonShootCommand()); 
     	addParallel(new RaiseDeflectorCommand());
     	addSequential(new WaitForShooterCommand());
    	addSequential(new CheckTime());
    	addSequential(new IndexerLoadCommand());
    	
    	addSequential(new WaitCommand(1));
    	addSequential(new AutonStopShooterCommand());
    	addSequential(new StopMotorsCommand());
    	
    	break;
   
    case 2:
    	//shoots in low goal
    	
    	addSequential(new LowerArmCommand());
    	//may need to be loading position
        addParallel(new GoToArmPosition(ArmPosition.LOAD, false, false));//may need to be down position
        //addSequential(new AimCommand());//make sure robot is aligned

    	addSequential(new LowerArmCommand());
       	addSequential(new AutonExpelCommand());
    	
       	break;
    
    case 3:
    	break;
    	}
    }   
}