package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class DriveBase {
	
	private final double WHEEL_CIRCUMFERENCE = 4.0 * Math.PI;
	
	private final double PULSES_PER_REVOLUTION = 360.0;
	
	private final double ALLOWANCE = 5.0;
	
	private SpeedController leftVictor;
	private SpeedController rightVictor;


	
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
	
	// sets the speed of each speed controller
	public void setSpeed (double leftSpeed, double rightSpeed) {
		leftVictor.set(leftSpeed);
		rightVictor.set(rightSpeed);

	}
	
	// get the speed of the left wheel
	public double getleftSpeed (){
		return leftVictor.get(); 
	}
	
	// get the speed of the right wheel
	public double getrightSpeed (){
		 return rightVictor.get(); //return 0.0;
	}
	
	// get the distance in inches of the right wheel
	public double getRightWheelDistance () {
		return rightEncoder.getDistance();
	}
	
	// get the distance in inches of the left wheel
	public double getLeftWheelDistance (){
		return leftEncoder.getDistance();
	}
	
	// resets both encoders to zero 
	public void resetEncoders () {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void driveStraight (double distance) 
	{
		if (rightEncoder.getDistance() < distance && leftEncoder.getDistance() < distance) 
		{
			if (leftEncoder.getDistance() + ALLOWANCE < rightEncoder.getDistance()) 
			{
				setSpeed (0.5, 0.0);
			} 
			else if (leftEncoder.getDistance() - ALLOWANCE > Math.abs(rightEncoder.getDistance())) 
			{
				setSpeed (0.0, 0.5);
			}
			else
			{
				setSpeed (0.5, 0.5);
			}
		}
	}
	
	// give positive number
	public void driveStraightBackwards (double distance) 
	{
		if (Math.abs(rightEncoder.getDistance()) < distance && Math.abs(leftEncoder.getDistance()) < distance) 
		{
			if (Math.abs(leftEncoder.getDistance()) + ALLOWANCE < Math.abs(rightEncoder.getDistance())) 
			{
				setSpeed (-0.5, 0.0);
			} 
			else if (Math.abs(leftEncoder.getDistance()) - ALLOWANCE > Math.abs(rightEncoder.getDistance())) 
			{
				setSpeed (0.0, -0.5);
			}
			else
			{
				setSpeed (-0.5, -0.5);
			}
		}
	}
	
	public void turn (double distance) 
	{
		if (Math.abs(leftEncoder.getDistance()) < Math.abs(distance)) 
		{
			if (distance < 0) 
			{
				setSpeed (0.5, -0.5);
			} 
			else 
			{
				setSpeed (-0.5, 0.5);
			}
		}
	}
	
}
