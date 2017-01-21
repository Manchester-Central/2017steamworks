package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Victor;

public class Climber {
	
	private Victor speedController;
	
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
	
}
