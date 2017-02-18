package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

public class BallIntakeShooter {
	
	SpeedController intake;
	SpeedController shooter;
	SpeedController backer;
	SpeedController agitator;
	
	
	public BallIntakeShooter() {
		
		intake = new Spark(PortConstants.INTAKE_SPARK_PORT);
		shooter = new Spark(PortConstants.SHOOTER_SPARK_PORT);
		backer = new Spark(PortConstants.CHOICE_ROLLER_PORT);
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
		public void setBackerSpeed (double speed) {
			backer.set(speed);
			
		}
	// 
		public void setAgitatorSpeed (double speed) {
			agitator.set(speed);
		}
		
		public void shoot () {
			setShooterSpeed (1.0);
			setIntakeSpeed (1.0);
			setBackerSpeed (0.0);
			setAgitatorSpeed(1.0);
		}
		
		public void intake () {
			setShooterSpeed (-1.0);
			setIntakeSpeed (1.0);
			setBackerSpeed (1.0);
			setAgitatorSpeed(1.0);
		}
		
}
