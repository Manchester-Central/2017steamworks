package org.usfirst.frc.team131.robot.autoconditions;

import org.usfirst.frc.team131.robot.GearFlopper;
import org.usfirst.frc.team131.robot.ICondition;

public class SpringIn implements ICondition{
	GearFlopper gear;
	
	public SpringIn (GearFlopper gear) {
		this.gear = gear;
	}
	
	@Override
	public void init(String conditionValue) {
		
	}

	@Override
	public boolean check() {
		return gear.springActivated();
	}

}
