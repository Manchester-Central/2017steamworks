package org.usfirst.frc.team131.robot;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team131.robot.autostates.*;
import org.usfirst.frc.team131.robot.autoconditions.*;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoController {
	
	Map<String, IState> states = new HashMap<String, IState>();
	Map<String, ICondition> conditions = new HashMap<String, ICondition>();
	Preferences prefs;
	IState currentState;
	int stage = 1;
	ICondition currentCondition;
	String currentVariable;
	
	public AutoController(DriveBase drive, GearFlopper gear){
		states.put("forward", new DriveForward(drive));
		states.put("backward", new DriveBackward(drive));
		states.put("right", new TurnRight(drive));
		states.put("left", new TurnLeft(drive));
		states.put("wait", new Wait());
		states.put("follow", new LightFollow(drive));
		states.put("none", new DoNothing());
		states.put("eject", new PlaceGear(gear));
		states.put("retract", new RetractGear(gear));
		
		conditions.put("distance", new Distance(drive));
		conditions.put("time", new Time());
		conditions.put("angle", new Angle(drive));
		conditions.put("gearin", new GearIn(gear));
		conditions.put("none", new None());
		conditions.put("springin", new SpringIn(gear));
		prefs = Preferences.getInstance();
	}
	
	public String getAutoStageString(int i) {
		return prefs.getString("Auto Stage " + i, "none;none=hi");
	}
	
	public void setUpDashboard () {
		// sets column tables
		for (int i = 1; i <= 8; i++) {
			
			String s = getAutoStageString(i);
			
			//SmartDashboard.putString("AutoStage" + (i), s);
			
		}
	}
	
	public void reset() {
		stage = 1;
	}
	
	public void run() {
		if (currentState == null){
			String currentStageString = getAutoStageString(stage);
			System.out.println("test " + currentStageString );
			if (currentStageString == null){
				stage++;
				return;
			}
			String[] currentStageArgs = currentStageString.split("(=)|(;)");
			if (currentStageArgs.length != 3){
				stage++;
				return;
			}
			currentState = states.get(currentStageArgs[0]);
			currentCondition = conditions.get(currentStageArgs[1]);
			currentVariable = currentStageArgs[2];
			if (currentState == null || currentCondition == null || currentVariable == null){
				stage++;
				currentState = null;
				currentCondition = null;
				currentVariable = null;
				return;
			}
			currentCondition.init(currentVariable);
			SmartDashboard.putString("AutoController test", SmartDashboard.getString("AutoController test", "") + currentStageString);
			
		}
		currentState.run();
		if (currentCondition.check()){
			currentState.stop();
			stage++;
			currentState = null;
			currentCondition = null;
			currentVariable = null;
		}
		
	}
	
	//Drives Straight	*O*
//	public void driveStraight (DriveBase drive, double distance) 
//	{
//		switch (state) 
//		{
//			case INITIAL:
//				state = State.DRIVE_FORWARD;
//				break;
//			case DRIVE_FORWARD:
//				drive.driveStraightDistance(distance);
//				if (drive.getLeftWheelDistance() >= distance) 
//				{
//					state = State.DO_NOTHING;
//				}
//				break;
//			case DO_NOTHING:
//			default:
//				drive.setSpeed(0.0, 0.0);
//				break;
//			
//		}
//	}
//	
//	// Drives(backwards) and Places Gear 
//	public void placeGear (DriveBase drive, GearFlopper gearFlopper, double distanceOffset, double distanceCanOffset, double waitTime)
//	{
//		String s;
//		switch (state)
//		{
//			case INITIAL:
//				s = "initial";
//				state = State.TURN;
//				break;
//			case TURN:
//				s = "turn";
//				if (Math.abs(distanceOffset) > distanceCanOffset) {
//					if (distanceOffset < 0) {
//						drive.turnLeft(0.2);
//					} else {
//						drive.turnRight(0.2);
//					}
//				} else {
//					state = State.DRIVE_FORWARD;
//				}
//				break;
//			case DRIVE_FORWARD:
//				s = "drive forward";
//				drive.driveStraight();
//				if (gearFlopper.springActivated())
//				{
//					state = State.PLACE_GEAR;
//				}
//				break;
//			case PLACE_GEAR:
//				s = "place gear";
//				if (gearFlopper.gearIsPresent() == false) 
//				{
//					gearFlopper.retractGearFlopper(2000L);
//					state = State.DO_NOTHING;
//				}
//				else 
//				{
//					gearFlopper.ejectGear();
//				}
//			case DO_NOTHING:
//			default:
//				s = "do nothing";
//				drive.setSpeed(0.0, 0.0);
//				gearFlopper.retractGearFlopper(2000L);
//
//		}
//		SmartDashboard.putString("auto state oogabooga where da souls at", s);
//	}
	
}
