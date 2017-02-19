package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

public class BallIntakeShooter {
	
	SpeedController front;
	SpeedController back;
	SpeedController top;
	SpeedController agitator;
	
	
	public BallIntakeShooter() {
		
		front = new Spark(PortConstants.FRONT_SPARK_PORT);
		back = new Spark(PortConstants.BACK_SPARK_PORT);
		top = new Spark(PortConstants.TOP_SPARK_PORT);
		agitator = new Spark(PortConstants.AGITATOR_SPARK_PORT);
		
	}
	
	// sets the speed of each speed controller
		public void setFrontSpeed (double speed) {
			front.set(speed);
			
		}
	// sets the speed of each speed controller
		public void setBackSpeed (double speed) {
			back.set(speed);
			
		}
		
	// sets the speed of each speed controller
		public void setTopSpeed (double speed) {
			top.set(speed);
			
		}
	// 
		public void setAgitatorSpeed (double speed) {
			agitator.set(speed);
		}
		
		public void shoot () {
			setBackSpeed (1.0);
			setFrontSpeed (-1.0);
			setTopSpeed (-1.0);
			setAgitatorSpeed(1.0);
		}
		
		public void intake () {
			setBackSpeed (0.0);
			setFrontSpeed (-1.0);
			setTopSpeed (1.0);
			setAgitatorSpeed(0.0);
		}
		
		public void stop () {
			setBackSpeed (0.0);
			setFrontSpeed (0.0);
			setTopSpeed (0.0);
			setAgitatorSpeed(0.0);
		}
}
