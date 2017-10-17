package org.usfirst.frc.team131.robot.autostates;

import org.usfirst.frc.team131.robot.BallIntakeShooter;
import org.usfirst.frc.team131.robot.IState;

public class Shoot implements IState {

	private BallIntakeShooter shumper;
	
	public Shoot (BallIntakeShooter otherShumper) {
		shumper = otherShumper;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		shumper.shoot();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		shumper.stop();
	}

}
