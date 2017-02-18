package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class BallIntakeShooter {
	
	SpeedController intake;
	SpeedController shooter;
	SpeedController choice;
	SpeedController agitator;
	
	
	public BallIntakeShooter() {
		
		intake = new Spark(PortConstants.INTAKE_SPARK_PORT);
		shooter = new Spark(PortConstants.SHOOTER_SPARK_PORT);
		choice = new Spark(PortConstants.CHOICE_ROLLER_PORT);
		agitator = new Spark(PortConstants.AGITATOR_IS_BACK);
		
	}
	
	// sets the speed of each speed controller
		public void setIntakeSpeed (double speed) {
			intake.set(speed);
			
		}
	// sets the speed of each speed controller
		public void setShooterSpeed (double speed) {
			shooter.set(speed);
			
		}
		
	// sets the speed of each speed controller
		public void setChoiceSpeed (double speed) {
			choice.set(speed);
			
		}
	// 
		public void setAgitatorSpeed (double speed) {
			agitator.set(speed);
		}
}
