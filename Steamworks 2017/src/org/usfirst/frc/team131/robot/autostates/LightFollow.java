package org.usfirst.frc.team131.robot.autostates;

import org.usfirst.frc.team131.robot.DriveBase;
import org.usfirst.frc.team131.robot.GearFlopper;
import org.usfirst.frc.team131.robot.IState;

public class LightFollow implements IState{

	DriveBase drive;
	
	public LightFollow (DriveBase drive) {
		this.drive = drive;
	}
	
	@Override
	public void run() {
		//drive.followLight();
	}
	@Override
	public void stop() {
		drive.setSpeed(0, 0);
	}
}
