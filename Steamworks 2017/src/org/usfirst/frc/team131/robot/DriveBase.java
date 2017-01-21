package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class DriveBase {
	
	private final double WHEEL_CIRCUMFERENCE = 1;
	private final double PULSES_PER_REVOLUTION = 1;
	
	private Victor leftVictor;
	private Victor rightVictor;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	public DriveBase () {
		
		leftVictor = new Victor (PortConstants.LEFT_SPEED_CONTROLLER_PORT);
		rightVictor = new Victor (PortConstants.RIGHT_SPEED_CONTROLLER_PORT);
		
		leftEncoder = new Encoder (PortConstants.LEFT_ENCODER_PORT_ONE, PortConstants.LEFT_ENCODER_PORT_TWO);
		rightEncoder = new Encoder (PortConstants.RIGHT_ENCODER_PORT_ONE, PortConstants.RIGHT_ENCODER_PORT_TWO);
		
		leftEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		rightEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		
	}
	
	public void setSpeed (double leftSpeed, double rightSpeed) {
		leftVictor.set(leftSpeed);
		rightVictor.set(rightSpeed);
	}
	
	public double getleftSpeed (){
		return leftVictor.get(); 
	}
	
	public double getrightSpeed (){
		return rightVictor.get();
	}
	
	public double getRightWheelDistance () {
		return rightEncoder.getDistance();
	}
	
	public double getLeftWheelDistance (){
		return leftEncoder.getDistance();
	}
	
}