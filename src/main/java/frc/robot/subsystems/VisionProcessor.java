package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//10.5.4.23
/**
 *
 */
public class VisionProcessor extends Subsystem {
	
    private static NetworkTable table;
    private static double[] defaultValue;
    //private static double[] areas;
    private static double[] centerXs = new double[240];	
    private static double[] centerYs = new double[240];
    //private static double[] widths;
    //private static double[] heights;
    private static double[] MEP_COORDINATES = new double[500];
    private static double distance;
    private static double horizontalDistance = 140;
    private static double[] lastCoordinates = new double[240];
    public static final double IMAGE_CENTER_X = 120;//120
    public static final double PIXEL_RATIO = 1/5.5; //1/5333
    public static final double GOAL_HEIGHT = 304.8; //millimeters 12 in inches
    public static final double GOAL_WIDTH = 20;
    public static final double AXIS_CAMERA_ANGLE = 1.16937;//rad
    public static final double FOCAL_LENGTH = 2.8; //millimeters
    public static final int IMAGE_HEIGHT = 320;
    public static final double CAMERA_HEIGHT = 368.3; // mm
    
    private static double lastCenterX = 190;
    private static double lastHeight = 0;
    
    public static VisionProcessor instance = new VisionProcessor();
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private VisionProcessor(){
//    	table = NetworkTable.getTable("GRIP/myContoursReport");
    	table = NetworkTable.getTable("SmartDashboard");
    	
    	//MEP_COORDINATES = table.getNumberArray("MEP_COORDINATES",defaultValue);
    	defaultValue = new double[0];
    	refresh();
    }
    public double getCameraDistance(){
    	return distance;
    }
    public void refresh(){
    	//RoboRealm 
    	//table = NetworkTable.getTable("SmartDashboard");
    	distance = table.getNumber("Distance", 14);
    	horizontalDistance = Math.sqrt(Math.pow(distance, 2) - Math.pow(70, 2)) - 25;
    	
    	MEP_COORDINATES = table.getNumberArray("MEP_COORDINATES", defaultValue);
    
    	SmartDashboard.putString("MEPCoordinates", getCoordinates());
    	SmartDashboard.putString("Got New Array", "Yes");
    	SmartDashboard.putNumber("Vision Distance to Goal", distance);
    	
    	try{
    		
    		centerXs[0] = MEP_COORDINATES[0];   //point 1  
    		SmartDashboard.putNumber("CENTERX", centerXs[0]);
    		centerYs[0] = MEP_COORDINATES[1]; 
    	
    		centerXs[1] = MEP_COORDINATES[2];   //point 2  
    		centerYs[1] = MEP_COORDINATES[3]; 
    	
    		centerXs[2] = MEP_COORDINATES[4];   //point 3  
    		centerYs[2] = MEP_COORDINATES[5]; 
    
    		centerXs[3] = MEP_COORDINATES[6];   //point 4  
    		centerYs[3] = MEP_COORDINATES[7]; 
        	lastCoordinates = MEP_COORDINATES;
        	SmartDashboard.putBoolean("Out of bounds", false);
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		SmartDashboard.putBoolean("Out of bounds", true);
    		/*centerXs[0] = 160;   //point 1  
    		centerYs[0] = 120; 
    	
    		centerXs[1] = 160;   //point 2  
    		centerYs[1] = 120; 
    	
    		centerXs[2] = 160;   //point 3  
    		centerYs[2] = 120; 
    
    		centerXs[3] = 160;   //point 4  
    		centerYs[3] = 120;*/
    		//MEP_COORDINATES = lastCoordinates;
    		centerXs[0] = lastCoordinates[0];   //point 1  
    		centerYs[0] = lastCoordinates[1]; 
    	
    		centerXs[1] = lastCoordinates[2];   //point 2  
    		centerYs[1] = lastCoordinates[3]; 
    	
    		centerXs[2] = lastCoordinates[4];   //point 3  
    		centerYs[2] = lastCoordinates[5]; 
    
    		centerXs[3] = lastCoordinates[6];   //point 4  
    		centerYs[3] = lastCoordinates[7]; 
    	}
    	
    	
    	//GRIP
    	//areas = table.getNumberArray("area", defaultValue);
    	//centerXs = table.getNumberArray("centerX", defaultValue);
    	//centerYs = table.getNumberArray("centerY", defaultValue);
    	//widths = table.getNumberArray("width", defaultValue);
    	//heights = table.getNumberArray("height", defaultValue);
    	//return instance;
    }
    
    //this index can be used for all arrays to specify the desired target
    
    //public boolean onTarget(){
   // 	return Math.abs((getCenterX() - IMAGE_CENTER_X)) * PIXEL_RATIO < RobotMap.COMPASS_TOLERANCE;
   // }
    
//    private int findGoal(){
//    	refresh();
//    	int goalIndex = -1; //-1
//    	int i = 0;
//    	double goal=0;
//    	//while (i<centerXs.length)
//    	//{
//    	//	if (areas[i]>goal)
//    	//	{
//    	//		goal = areas[i];
//    	//		goalIndex=i;
//    	//	}
//    	//	i++;
//    	//}
//		return 0;
//    }
    public double getDriveDistance(){
    	return horizontalDistance;
    }
    public double approximateDistance(){
    	refresh();
    	//double height = centerYs[0];
    	double height = (centerYs[0] + centerYs[1] - centerYs[2] - centerYs[3]) /2;
    	SmartDashboard.putNumber("HEIGHT OF GOAL", height);
    	//double distance = ((FOCAL_LENGTH * GOAL_HEIGHT * IMAGE_HEIGHT)/(myHeight(foundGoal)*CAMERA_HEIGHT)/25.4);// distance in inches
    	//double distance = 1.0*240.0/(2*heights[foundGoal]*Math.tan(0.855211));
    	double distance = Math.tan(0.986111)*240/height; 
    	SmartDashboard.putNumber("Anil Distance to target", distance);
    	return distance;
   
    	
    }
   // private double myHeight(int goal){
   // 	try{
   // 		double myTarget = heights[goal];
   // 		lastHeight = myTarget;
   // 		return myTarget;
   // 	}
   // 	catch(ArrayIndexOutOfBoundsException e){
   // 		return lastHeight;
   // 	}
  //  }
    
    public double heightInInches(){
    //	int foundGoal = findGoal();
    	return (AXIS_CAMERA_ANGLE/ 240.0) * centerYs[0];
    }
    
    public boolean targetFound(){
    	refresh();
    	return (MEP_COORDINATES.length != 0);
    	
    }
    
    public double getCenterX(){
    	//average all x coordinates together to get the center of x 
    	//refresh();
    	double totalx = centerXs[0]+ centerXs[1]+centerXs[2]+centerXs[3];
    	SmartDashboard.putNumber("centerX", totalx);
    	return totalx/4;
    	
    	//int foundGoal = findGoal();
    	//return myCenterX(0);
    	//if (foundGoal == -1){
    	//	return lastCenterX;
    	//}
    	//else{
    	//	lastCenterX = myCenterX(foundGoal);
    	
    	//}
    	//return 160;
    }
//    private double myCenterX(int goal){
//    	try{
//    		double myTarget = centerXs[goal];
//    		lastCenterX = myTarget;
//    		return myTarget;
//    	}
//    	catch(ArrayIndexOutOfBoundsException e){
//    		return lastCenterX;
//    	}
//    }
    public double getAngle(){
    	refresh();
    	double degrees;
    	double centerX = getCenterX();
    	double count = 0;
    	double totalDegrees = 0;
    	/*while(count < 101){
    		totalDegrees+=NavSensorSubsystem.ahrs.getYaw() + PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X);
    		count ++;
    	}*/
    	
    	//degrees = totalDegrees/count;
    	if(targetFound() && getCameraDistance()<8){
    		degrees = (PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X));
    	}
    	else if(targetFound()){
    		/*if(centerX > 120){
    			degrees = (PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X ))+10;
    		}*/
    		
    			degrees = (PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X))+1;
    	}
    	
    	else{
    		degrees = 0;
    	}
    	
    	
    	//if(Math.abs((PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X))) >= RobotMap.COMPASS_TOLERANCE)
    	//	degrees = NavSensorSubsystem.ahrs.getYaw() + PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X);
    	//else{
    	//	degrees = 0;
    	//}
    	//if (degrees < 0){
    	//	degrees += 360;
    	//}
    	//else if (degrees > 360){
    	//	degrees %= 360;
    	//}
    	return degrees;
    }
   // public double getAngle(){
    	//double degrees;
    	//double centerX = getCenterX();
    	
    	
    	//if(Math.abs((PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X))) >= RobotMap.COMPASS_TOLERANCE)
    	//	degrees = NavSensorSubsystem.ahrs.getFusedHeading() + PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X);
    	//else{
    	//	degrees = NavSensorSubsystem.ahrs.getFusedHeading();
    	//}
    	//if (degrees < 0){
    	//	degrees += 360;
    	//}
    	//else if (degrees > 360){
    	//	degrees %= 360;
    	//}
    	//return degrees;
   // }
    //public double getCenterY(){
    //	return centerYs[findGoal()];
    //}
    
    public String getCoordinates(){
    	String coordinateList = "";
    	for(double i: MEP_COORDINATES){
    		coordinateList = coordinateList + i + " , ";
    	}
    	return coordinateList;
    	
   	
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

