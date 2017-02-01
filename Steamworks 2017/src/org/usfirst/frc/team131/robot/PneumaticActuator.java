package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticActuator
{
	
	private Solenoid front;
	private Solenoid back;
	
	public PneumaticActuator (int firstPort, int secondPort) 
	{
		front = new Solenoid (firstPort);
		back = new Solenoid (secondPort);
	}
	
	public void set(boolean state) 
	{
		front.set(state);
		back.set(!state);
	}
	
	public boolean isStarted () {
		return front.get();
	}
	
	 
	public void start(){
		front.set(true);
		back.set(false);
	}
	
	public void stop(){
		front.set(false);
		back.set(true);
	}
	
}
