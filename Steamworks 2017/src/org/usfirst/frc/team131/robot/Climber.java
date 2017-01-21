package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Climber {
	
	private SpeedController speedController;
	
	public Climber () {
		speedController = new Victor(PortConstants.CLIMBER_PORT);
	}
	
	// sets climber speed
	public void setClimberSpeed (double speed) {
		speedController.set (speed);
	}
	
	// gets climber speed
	public double getClimberSpeed (){
		return speedController.get();
	}
	
	// returns true if the climber is faster than 0.1 or smaller than -0.1
	public boolean isClimbing (){
		return !((speedController.get () < 0.1) && (speedController.get() > -0.1));
	}
	
}
