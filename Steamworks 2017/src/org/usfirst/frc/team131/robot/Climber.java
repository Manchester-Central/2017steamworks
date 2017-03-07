package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Climber {
	
	private SpeedController speedController1;
	private SpeedController speedController2;
	
	public Climber () { 
	
		speedController1 = new Victor(PortConstants.CLIMBER_PORT_ONE);
		speedController2 = new Victor(PortConstants.CLIMBER_PORT_TWO);	

		speedController1.setInverted(true);
		speedController2.setInverted(true);
	}
	
	// sets climber speed
	public void setClimberSpeed (double speed) {
		speed *= -1;
		speedController1.set (Math.min(0, speed));
		speedController2.set(Math.min(0, speed));
	}
	
	// gets climber speed
	public double getClimberSpeed (){
		return speedController1.get();
	}
	
	// returns true if the climber is faster than 0.1 or smaller than -0.1
	public boolean isClimbing (){
		return ((getClimberSpeed() < 0.1) && (getClimberSpeed() > -0.1)); 
	}
	
}
