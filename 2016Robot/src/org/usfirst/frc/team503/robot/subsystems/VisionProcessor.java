package org.usfirst.frc.team503.robot.subsystems;

import org.usfirst.frc.team503.robot.RobotMap;

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
    private static double[] areas;
    private static double[] centerXs;	
    //private static double[] centerYs;
    private static double[] widths;
    private static double[] heights;
    public static final double IMAGE_CENTER_X = 160;
    public static final double PIXEL_RATIO = 1/5.5; //1/5333
    public static final double GOAL_HEIGHT = 304.8; //millimeters 12 in inches
    public static final double GOAL_WIDTH = 20;
    public static final double AXIS_CAMERA_ANGLE = 67.0;
    public static final double FOCAL_LENGTH = 2.8; //millimeters
    public static final int IMAGE_HEIGHT = 240;
    public static final double CAMERA_HEIGHT = 495.3; // mm
    
    private static double lastCenterX = 190;
    private static double lastHeight = 0;
    
    public static VisionProcessor instance = new VisionProcessor();
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private VisionProcessor(){
    	table = NetworkTable.getTable("GRIP/myContoursReport");
    	defaultValue = new double[0];
    	refresh();
    }
    
    private void refresh(){  	
    	
    	areas = table.getNumberArray("area", defaultValue);
    	centerXs = table.getNumberArray("centerX", defaultValue);
    	//centerYs = table.getNumberArray("centerY", defaultValue);
    	widths = table.getNumberArray("width", defaultValue);
    	heights = table.getNumberArray("height", defaultValue);
    	//return instance;
    }
    
    //this index can be used for all arrays to specify the desired target
    
    //public boolean onTarget(){
   // 	return Math.abs((getCenterX() - IMAGE_CENTER_X)) * PIXEL_RATIO < RobotMap.COMPASS_TOLERANCE;
   // }
    
    private int findGoal(){
    	refresh();
    	int goalIndex = -1; //-1
    	int i = 0;
    	double goal=0;
    	while (i<areas.length)
    	{
    		if (areas[i]>goal)
    		{
    			goal = areas[i];
    			goalIndex=i;
    		}
    		i++;
    	}
		return goalIndex;
    }
    
    public double approximateDistance(){
    	int foundGoal = findGoal();
    	double distance = ((FOCAL_LENGTH * GOAL_HEIGHT * IMAGE_HEIGHT)/(myHeight(foundGoal)*CAMERA_HEIGHT)/25.4);// distance in inches
    	return distance;
    	//return GOAL_WIDTH* 240.0 * ((2*widths[foundGoal]*Math.tan(AXIS_CAMERA_ANGLE/2)));
		//return GOAL_HEIGHT/(Math.tan(.28*heights[foundGoal]));
    	
    }
    private double myHeight(int goal){
    	try{
    		double myTarget = heights[goal];
    		lastHeight = myTarget;
    		return myTarget;
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		return lastHeight;
    	}
    }
    
    public double heightInInches(){
    	int foundGoal = findGoal();
    	return (AXIS_CAMERA_ANGLE/ 240.0) *heights[foundGoal];
    }
    
    public boolean targetFound(){
    	return (findGoal() != -1);
    	
    }
    
    public double getCenterX(){
    	//return centerXs[0];
    	int foundGoal = findGoal();
    	return myCenterX(foundGoal);
    	//if (foundGoal == -1){
    	//	return lastCenterX;
    	//}
    	//else{
    	//	lastCenterX = myCenterX(foundGoal);
    	
    	//}
    	    	
    	
    	
    	
    	//return 160;
    }
    private double myCenterX(int goal){
    	try{
    		double myTarget = centerXs[goal];
    		lastCenterX = myTarget;
    		return myTarget;
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		return lastCenterX;
    	}
    }
    public double getAngle(){
    	double degrees;
    	double centerX = getCenterX();
    	
    	degrees = NavSensorSubsystem.ahrs.getYaw() + PIXEL_RATIO * (centerX - VisionProcessor.IMAGE_CENTER_X);
    	
    	
    	
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
    
    public String getAreas(){
    	String areaList = "";
    	for(double i: areas){
    		areaList = areaList + i + " , ";
    	}
    	return areaList;
    	//return "lol";
    	
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

