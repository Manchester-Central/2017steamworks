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
	DriveBase drive;
	
	final int INITIAL = 0;
	final int FOLLOWING = 1;
	final int PLACE_GEAR = 2;
	final int BACK_OFF = 3;
	final int NOTHING = 4;
	int state = INITIAL;
	
	final double CONTINUAL_DRIVE = 1000000.0;
	
	public AutoController(DriveBase passedDrive , GearFlopper gear, BallIntakeShooter shumper){
		this.drive = passedDrive;
		states.put("forward", new DriveForward(drive));
		states.put("right", new TurnRight(drive));
		states.put("wait", new Wait());
		states.put("follow", new LightFollow(drive));
		states.put("none", new DoNothing());
//		states.put("eject", new PlaceGear(gear));
		states.put("retract", new RetractGear(gear));
		states.put("shoot", new Shoot(shumper));
//		
		conditions.put("distance", new Distance(drive));
		conditions.put("time", new Time());
		conditions.put("angle", new Angle(drive));
		conditions.put("none", new None());
		conditions.put("distanceAngle", new EncoderAngle (drive));
		conditions.put("gearout", new GearIn(gear));
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
			
			if (currentStageArgs[1].equals("distanceAngle") == false && currentStageArgs[1].equals("distance") == false) {
				//System.out.println("within neither distanceAngle or distance and state = " + currentStageArgs[0]);
				if (currentStageArgs[0].equals("forward")) {
					drive.setTargetInches(CONTINUAL_DRIVE, CONTINUAL_DRIVE);
					//System.out.println("within forward state");
				} else if (currentStageArgs[0].equals("right")) {
					drive.setTargetInches(CONTINUAL_DRIVE, -CONTINUAL_DRIVE);
					//System.out.println("within backwards state");
				}
			}
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
	
	public void resetState()
	{
		state = INITIAL;
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
	public void placeGear (DriveBase drive, GearFlopper gearFlopper, double distanceOffset)
	{
		String s = "-----";
		switch (state)
		{
			case INITIAL:
				s = "initial";
				state = FOLLOWING;
				drive.resetEncoders();
				break;
				
			case FOLLOWING:
			{
				if(gearFlopper.springActivated()){
					state = PLACE_GEAR;
					drive.setSpeed(0, 0);
					break;
				}
				
				s = "following";
				double leftTarget = 50 + distanceOffset;
				double rightTarget = 50 - distanceOffset;
				drive.setTargetInches(rightTarget, leftTarget);
				drive.tankCorrectedDrive(drive.getLeftWheelDistance(), drive.getRightWheelDistance());
			}
				break;
				
			case PLACE_GEAR:
				s = "place gear";
				if (gearFlopper.gearIsPresent() == false) 
				{
					gearFlopper.retractGearFlopper();
					drive.resetEncoders();
					drive.setTargetInches(-24, -24);
					state = BACK_OFF;
				}
				else 
				{
					gearFlopper.ejectGear();
				}
				break;
				
			case BACK_OFF:
				s = "back off";
				drive.tankCorrectedDrive(drive.getLeftWheelDistance(), drive.getRightWheelDistance());
				if(Math.abs(drive.getLeftWheelDistance() - drive.getLeftTarget()) < 2.0){
					state = NOTHING;
				}
				break;
				
			default:
				s = "GEAR PLACED";
				drive.setSpeed(0.0, 0.0);
				break;
		}
	}
	
}
