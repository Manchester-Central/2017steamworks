package org.usfirst.frc.team131.robot;

public class ControllerOverseer {
	
	public Controller driver;
	public Controller operator;
	
	public ControllerOverseer () {
		
		driver = new Controller (0);
		operator = new Controller (1);
		
	}
	
}
