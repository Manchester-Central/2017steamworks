package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class GearFlopper {
	
	private Victor gearFlopper;
	private DigitalInput upperFlopperPosition;
	private DigitalInput lowerFlopperPosition;
	private PneumaticActuator upperSolenoid;
	private PneumaticActuator lowerSolenoid;
	
	public PneumaticActuator getUpperSolenoid()
	{
		return upperSolenoid;
	}
	
	public PneumaticActuator getLowerSolenoid () 
	{
		return lowerSolenoid;
	}
	
	public boolean gearIsPresent;
	
	public GearFlopper ()  {
		upperSolenoid = new PneumaticActuator(PortConstants.UPPER_PNEUMATIC_CHANNEL,PortConstants.LOWER_PNEUMATIC_CHANNEL);
		
		gearIsPresent = false;
		
		lowerSolenoid = new PneumaticActuator(4, 7);
		//GearFlopper = new Victor (PortConstants.GEAR_FLOPPER_CONTROLLER_PORT);
		//UpperFlopperPosition = new DigitalInput (PortConstants.UPPER_GEAR_FLOPPER_LIMIT_SWITCH);
		//LowerFlopperPosition = new DigitalInput (PortConstants.LOWER_GEAR_FLOPPER_LIMIT_SWITCH);
	}
	
	public void gearPusherSet(boolean state) 
	{
		upperSolenoid.set(state);
	}
	
	public void doorSet (boolean state) 
	{
		lowerSolenoid.set(state);
	}
	
	public boolean getDoor () {
		return lowerSolenoid.isActivated();
	}
	
	public boolean getGearPusher () {
		return upperSolenoid.isActivated();
	}
	
	public void ejectGear () {
		doorSet (true);
		gearPusherSet (true);
	}
	
	public void retractGearFlopper () {
		doorSet (false);
		gearPusherSet (false);
	}
	
//	public void setSpeed(double flopperSpeed) {
//		
//		if (upperFlopperPosition.get()){
//			
//			if (flopperSpeed > 0) {
//				
//				flopperSpeed = 0;
//			}
//			
//		}
//		
//		if (lowerFlopperPosition.get()){
//			
//			if (flopperSpeed < 0) {
//				
//				flopperSpeed = 0;
//			}
//			
//		}	
//		
//		gearFlopper.set(flopperSpeed);
//		
//	}
	
}


