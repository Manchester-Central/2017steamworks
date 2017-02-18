package org.usfirst.frc.team131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.DigitalInput;

//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Victor;

public class GearFlopper {
	
	//private Victor gearFlopper;
	//private DigitalInput upperFlopperPosition;
	//private DigitalInput lowerFlopperPosition;
	private PneumaticActuator gearPusher;
	private PneumaticActuator gearCover;
	private PneumaticActuator leftDoor;
	private PneumaticActuator rightDoor;
	
	DigitalInput gearIn;
	DigitalInput springIn;
	
	boolean pushed;
	
	public PneumaticActuator getGearPusherPosition()
	{
		return gearPusher;
	}
	
	public PneumaticActuator getGearCoverPosition() 
	{
		return gearCover;
	}
	
	public PneumaticActuator getDoorPosition() 
	{
		return leftDoor;
	}
	
	public GearFlopper ()  {
		pushed = false;
		
		gearIn = new DigitalInput(PortConstants.GEAR_SENSOR);
		springIn = new DigitalInput(PortConstants.SPRING_SENSOR);
		gearPusher = new PneumaticActuator(PortConstants.GEAR_PUSHER_P_CHANNEL_A, PortConstants.GEAR_PUSHER_P_CHANNEL_B);
		
		gearCover = new PneumaticActuator(PortConstants.GEAR_COVER_P_CHANNEL_A, PortConstants.GEAR_COVER_P_CHANNEL_B);
		
		leftDoor = new PneumaticActuator(PortConstants.LEFT_GEAR_DOOR_P_CHANNEL_A, PortConstants.LEFT_GEAR_DOOR_P_CHANNEL_B);
		rightDoor = new PneumaticActuator(PortConstants.RIGHT_GEAR_DOOR_P_CHANNEL_A, PortConstants.RIGHT_GEAR_DOOR_P_CHANNEL_B);
	}
	
	public void gearPusherSet(boolean state) 
	{
		gearPusher.set(state);
	}
	
	public void coverSet (boolean state) 
	{
		gearCover.set(state);
	}
	
	public void doorSet (boolean state) 
	{
		leftDoor.set(state);
		rightDoor.set(state);
	}
	
	public boolean getDoor () {
		return leftDoor.isActivated();
	}
	
	public boolean getCover () {
		return gearCover.isActivated();
	}
	
	public boolean getGearPusher () {
		return gearPusher.isActivated();
	}
	
	Date time = new Date ();
	
	long firstTime = 0L;
	
	// delay is milliseconds	
	public void ejectGear (long delay) {
		if (pushed = false) {
			pushed = true;
			firstTime = time.getTime();
		}
		doorSet (true);
		if (firstTime + delay < time.getTime()) {
			gearPusherSet (true);			
		}
	}
	// delay is milliseconds
	public void retractGearFlopper (long delay) {
		if (pushed = true) {
			pushed = false;
			firstTime = time.getTime();
		}
		gearPusherSet (false);
		if (firstTime + delay < time.getTime()) {
			doorSet (false);			
		}
	}
	
	public boolean gearIsPresent () {
		return gearIn.get();
	}
	
	public boolean springActivated () {
		return springIn.get();
	}
	
}


