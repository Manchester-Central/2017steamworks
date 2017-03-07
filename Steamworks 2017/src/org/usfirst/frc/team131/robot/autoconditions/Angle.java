package org.usfirst.frc.team131.robot.autoconditions;

import org.usfirst.frc.team131.robot.DriveBase;
import org.usfirst.frc.team131.robot.ICondition;

public class Angle implements ICondition{

	DriveBase drive;
	double angle;
	
	public Angle (DriveBase drive) {
		this.drive = drive;
	}
	
	@Override
	public void init(String conditionValue) {
		drive.resetGyro();
		try {
			angle = Math.abs(Double.parseDouble(conditionValue));
		} catch (Exception e) {
			angle = 0.0;
		}
	}

	@Override
	public boolean check() {
	//	System.out.println("get distance to drive " + (drive.getAngle (angle) < Math.abs(drive.getLeftWheelDistance())));
	//	System.out.println(Math.abs(drive.getLeftWheelDistance()));
	//	System.out.println(drive.getAngle (angle));
		return /*Math.abs(drive.getGyroAngle()) > angle*/ false;
	}

}
