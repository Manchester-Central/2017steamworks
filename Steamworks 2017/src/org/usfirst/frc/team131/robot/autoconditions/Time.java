package org.usfirst.frc.team131.robot.autoconditions;

import java.util.Date;

import org.usfirst.frc.team131.robot.ICondition;

public class Time implements ICondition {

	Date time;
	long firstTime;
	long delay;
	
	@Override
	public void init(String conditionValue) {
		time = new Date ();
		firstTime = time.getTime();
		try {
			delay = Long.parseLong(conditionValue);
		} catch (Exception e) {
			delay = 0;
		}
	}

	@Override
	public boolean check() {
		time = new Date ();
		return firstTime + delay < time.getTime();
	}

}
