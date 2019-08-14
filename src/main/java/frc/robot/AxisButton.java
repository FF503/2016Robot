package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	int axis;
	Joystick joystick;
	public AxisButton(Joystick joystick,int axis){
		this.axis = axis;
		this.joystick = joystick;
	}
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return joystick.getRawAxis(axis) >= .15;
	}

}
