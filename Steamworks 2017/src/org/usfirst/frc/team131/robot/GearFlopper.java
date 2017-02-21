package org.usfirst.frc.team131.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Victor;

public class GearFlopper {
	
	//private Victor gearFlopper;
	//private DigitalInput upperFlopperPosition;
	//private DigitalInput lowerFlopperPosition;
	private DoubleSolenoid gearPusher;
	private DoubleSolenoid gearCover;
	private DoubleSolenoid door;
	
	DigitalInput gearIn;
	DigitalInput springIn;
	
	boolean isEjected;
	
	public GearFlopper ()  {
		isEjected = false;
		
		gearIn = new DigitalInput(PortConstants.GEAR_SENSOR);
		springIn = new DigitalInput(PortConstants.SPRING_SENSOR);
		
		gearPusher = new DoubleSolenoid(PortConstants.GEAR_PUSHER_P_CHANNEL_A, PortConstants.GEAR_PUSHER_P_CHANNEL_B);
		
		gearCover = new DoubleSolenoid(PortConstants.GEAR_COVER_P_CHANNEL_A, PortConstants.GEAR_COVER_P_CHANNEL_B);
		
		door = new DoubleSolenoid(PortConstants.GEAR_DOOR_P_CHANNEL_A, PortConstants.GEAR_DOOR_P_CHANNEL_B);
	}
	
	public void gearPusherSet(DoubleSolenoid.Value state) 
	{
		gearPusher.set(state);
	}
	
	public void coverSet (DoubleSolenoid.Value state) 
	{
		gearCover.set(state);
	}
	
	public void doorSet (DoubleSolenoid.Value state) 
	{
		door.set(state);
	}
	
	public DoubleSolenoid.Value getDoor () {
		return door.get();
	}
	
	public DoubleSolenoid.Value getCover () {
		return gearCover.get();
	}
	
	public DoubleSolenoid.Value getGearPusher () {
		return gearPusher.get();
	}
	

	
	long firstTime = 0L;
	
	// delay is milliseconds	
	public void ejectGear () {
		Date time = new Date ();
		if (isEjected == false) {
			isEjected = true;
			firstTime = time.getTime();
		}
		doorSet (DoubleSolenoid.Value.kForward);
		//isEjected = true;
		if (firstTime + 1250 < time.getTime()) {
			gearPusherSet (DoubleSolenoid.Value.kReverse);			
		}
		else if (firstTime + 250 < time.getTime()){
			gearPusherSet (DoubleSolenoid.Value.kForward);
		}

	}
	// delay is milliseconds
	public void retractGearFlopper () {
		Date time = new Date ();
		if (isEjected == true) {
			isEjected = false;
			firstTime = time.getTime();
		}
		gearPusherSet (DoubleSolenoid.Value.kReverse);
		if (firstTime + 2000 < time.getTime()) {
			doorSet (DoubleSolenoid.Value.kReverse);			
		}
	}
	
	public boolean gearIsPresent () {
		return gearIn.get();
	}
	
	public boolean springActivated () {
		return springIn.get();
	}
	
}


