package org.usfirst.frc.team131.robot.autoconditions;

import org.usfirst.frc.team131.robot.GearFlopper;
import org.usfirst.frc.team131.robot.ICondition;

public class GearIn implements ICondition{

	boolean checkGearPresent;
	GearFlopper gear;
	
	public GearIn (GearFlopper gear) {
		this.gear = gear;
	}
	
	@Override
	public void init(String conditionValue) {
		checkGearPresent = conditionValue.equalsIgnoreCase("true");
		
	}

	@Override
	public boolean check() {
		
		return gear.checkIfEjected();
	}

}
