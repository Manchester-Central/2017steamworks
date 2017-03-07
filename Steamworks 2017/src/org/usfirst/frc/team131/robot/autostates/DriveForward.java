package org.usfirst.frc.team131.robot.autostates;

import org.usfirst.frc.team131.robot.DriveBase;
import org.usfirst.frc.team131.robot.IState;

public class DriveForward implements IState{

	DriveBase drive;
	
	public DriveForward(DriveBase drive) {
		this.drive = drive;
	}
	@Override
	public void run() {
		drive.tankCorrectedDrive(drive.getLeftWheelDistance(), drive.getRightWheelDistance());
	}
	@Override
	public void stop() {
		drive.resetEncoders();
		drive.setSpeed(0, 0);
	}
}
