package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoController {
	
	private enum State {
		INITIAL, DO_NOTHING, DRIVE_FORWARD, DRIVE_BACKWARD, PLACE_GEAR, TURN, DRIVE_FORWARD_TWO, TURN_TWO, WAIT
	}
	
	State state = State.INITIAL;
	
	//Drives Straight
	public void driveStraight (DriveBase drive, double distance) 
	{
		switch (state) 
		{
			case INITIAL:
				state = State.DRIVE_FORWARD;
				break;
			case DRIVE_FORWARD:
				drive.driveStraightDistance(distance);
				if (drive.getLeftWheelDistance() >= distance) 
				{
					state = State.DO_NOTHING;
				}
				break;
			case DO_NOTHING:
			default:
				drive.setSpeed(0.0, 0.0);
				break;
			
		}
	}
	
	// Drives(backwards) and Places Gear 
	public void placeGear (DriveBase drive, GearFlopper gearFlopper, double distanceOffset, double distanceCanOffset, double waitTime)
	{
		String s;
		switch (state)
		{
			case INITIAL:
				s = "initial";
				state = State.TURN;
				break;
			case TURN:
				s = "turn";
				if (Math.abs(distanceOffset) > distanceCanOffset) {
					if (distanceOffset < 0) {
						drive.turnLeft(0.2);
					} else {
						drive.turnRight(0.2);
					}
				} else {
					state = State.DRIVE_FORWARD;
				}
				break;
			case DRIVE_FORWARD:
				s = "drive forward";
				drive.driveStraight();
				if (gearFlopper.springActivated())
				{
					state = State.PLACE_GEAR;
				}
				break;
			case PLACE_GEAR:
				s = "place gear";
				if (gearFlopper.gearIsPresent() == false) 
				{
					gearFlopper.retractGearFlopper(2000L);
					state = State.DO_NOTHING;
				}
				else 
				{
					gearFlopper.ejectGear();
				}
			case DO_NOTHING:
			default:
				s = "do nothing";
				drive.setSpeed(0.0, 0.0);
				gearFlopper.retractGearFlopper(2000L);

		}
		SmartDashboard.putString("auto state oogabooga where da souls at", s);
	}
	
}
