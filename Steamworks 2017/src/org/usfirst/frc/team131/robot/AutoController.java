package org.usfirst.frc.team131.robot;

public class AutoController {
	
	private enum State {
		INITIAL, DO_NOTHING, DRIVE_FORWARD, DRIVE_BACKWARDS, PLACE_GEAR, TURN
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
				drive.driveStraight(distance);
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
	public void placeGear (DriveBase drive, GearFlopper gearFlopper, double distance)
	{
		switch (state)
		{
			case INITIAL:
				state = State.DRIVE_BACKWARDS;
				break;
			case DRIVE_BACKWARDS:
				drive.driveStraightBackwards(distance);
				if (drive.getLeftWheelDistance() >= distance)
				{
					state = State.PLACE_GEAR;
				}
				break;
			case PLACE_GEAR:
				if (gearFlopper.gearIsPresent == false) 
				{
					gearFlopper.retractGearFlopper();
					state = State.DO_NOTHING;
				}
				else 
				{
					gearFlopper.ejectGear();
				}
			case DO_NOTHING:
			default:
				drive.setSpeed(0.0, 0.0);
				gearFlopper.retractGearFlopper();

		}
	}
	
}
