package org.usfirst.frc.team131.robot.autostates;

import org.usfirst.frc.team131.robot.GearFlopper;
import org.usfirst.frc.team131.robot.IState;

public class RetractGear implements IState{

	GearFlopper gear;
	
	public RetractGear (GearFlopper gear) {
		this.gear = gear;
	}
	@Override
	public void run() {
		gear.retractGearFlopper();
	}

	@Override
	public void stop() {

		
	}

}
