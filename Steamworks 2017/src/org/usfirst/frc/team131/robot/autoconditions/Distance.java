package org.usfirst.frc.team131.robot.autoconditions;

import org.usfirst.frc.team131.robot.DriveBase;
import org.usfirst.frc.team131.robot.ICondition;

public class Distance implements ICondition {

	DriveBase drive;
	
	double distance;
	
	public Distance(DriveBase drive) {
		this.drive = drive;
	}
	
	@Override
	public void init(String conditionValue) {
		//System.out.println ("condition value " + conditionValue);
		drive.resetEncoders();
		try {
			distance = Double.parseDouble(conditionValue);
		} catch (Exception e) {
			distance = 0.0;
		}
		//System.out.println ("after init " + distance);
		drive.setTargetInches(distance, distance);
	}

	@Override
	public boolean check() {
		//System.out.println("check " + drive.getAveragedDistance());
		//return drive.getAveragedDistance() > distance;
		return     Math.abs(drive.getLeftWheelDistance()  - distance) <= DriveBase.TOLERANCE 
				&& Math.abs(drive.getRightWheelDistance() - distance) <= DriveBase.TOLERANCE;
	}

}
