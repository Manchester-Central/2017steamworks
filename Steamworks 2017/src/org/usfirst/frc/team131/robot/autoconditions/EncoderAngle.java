package org.usfirst.frc.team131.robot.autoconditions;

import org.usfirst.frc.team131.robot.DriveBase;
import org.usfirst.frc.team131.robot.ICondition;

public class EncoderAngle implements ICondition {

	DriveBase drive;
	
	double angle;
	
	public EncoderAngle(DriveBase drive) {
		this.drive = drive;
	}
	
	@Override
	public void init(String conditionValue) {
		//System.out.println ("condition value " + conditionValue);
		drive.resetEncoders();
		try {
			angle = Double.parseDouble(conditionValue);
		} catch (Exception e) {
			angle = 0.0;
		}
		//System.out.println ("after init " + distance);
		drive.setPointTurnDegrees(angle);
	}

	@Override
	public boolean check() {
		//System.out.println("check " + drive.getAveragedDistance());
		//return drive.getAveragedDistance() > distance;
		//TODO fix in distance maybe?
		//double leftTarget = drive.getLeftDistanceFromDegrees(angle); // drive.getLeftTarget();
		//double rightTarget = drive.getRightDistanceFromDegrees(angle); // drive.getRightTarget()
		return     Math.abs(drive.getLeftWheelDistance()  - drive.getLeftTarget()) <= DriveBase.TURN_TOLERANCE 
				&& Math.abs(drive.getRightWheelDistance() - drive.getRightTarget()) <= DriveBase.TURN_TOLERANCE;
	}

}
