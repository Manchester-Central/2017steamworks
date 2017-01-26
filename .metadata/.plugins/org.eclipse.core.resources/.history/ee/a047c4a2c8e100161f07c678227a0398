package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class GearFlopper {
	
	private Victor GearFlopper;
	private DigitalInput UpperFlopperPosition;
	private DigitalInput LowerFlopperPosition;
	
	public GearFlopper ()  {
		
		GearFlopper = new Victor (PortConstants.GEAR_FLOPPER_CONTROLLER_PORT);
		UpperFlopperPosition = new DigitalInput (PortConstants.UPPER_GEAR_FLOPPER_LIMIT_SWITCH);
		LowerFlopperPosition = new DigitalInput (PortConstants.LOWER_GEAR_FLOPPER_LIMIT_SWITCH);
	}
	
	public void setSpeed(double flopperSpeed) {
		
		if (UpperFlopperPosition.get()){
			
			if (flopperSpeed > 0) {
				
				flopperSpeed = 0;
			}
			
		}
		
		if (LowerFlopperPosition.get()){
			
			if (flopperSpeed < 0) {
				
				flopperSpeed = 0;
			}
			
		}	
		
		GearFlopper.set(flopperSpeed);
		
	}
	
}


