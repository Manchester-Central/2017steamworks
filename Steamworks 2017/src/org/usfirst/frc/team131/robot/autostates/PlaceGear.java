package org.usfirst.frc.team131.robot.autostates;

import org.usfirst.frc.team131.robot.GearFlopper;
import org.usfirst.frc.team131.robot.IState;

public class PlaceGear implements IState{

	GearFlopper gear;
	
	public PlaceGear(GearFlopper gear) {
		this.gear = gear;
	}
	@Override
	public void run() {
		gear.ejectGear();
	}

	@Override
	public void stop() {
		
		
	}

	
	
}
