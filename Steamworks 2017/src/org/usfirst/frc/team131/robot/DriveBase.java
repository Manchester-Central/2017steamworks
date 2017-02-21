package org.usfirst.frc.team131.robot;

import org.usfirst.frc.team131.robot.autostates.DriveForward;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
	
	private final double OFFSET_TOLERANCE = 10;

	private final double WHEEL_CIRCUMFERENCE = 4.0 * Math.PI;

	private final double PULSES_PER_REVOLUTION = 360.0;

	private final double ALLOWANCE = 5.0;

	private final double ROBOT_RADIUS = 1.0;

	CANTalon leftFrontTalon;
	CANTalon leftMidTalon;
	CANTalon leftBackTalon;
	CANTalon rightFrontTalon;
	CANTalon rightMidTalon;
	CANTalon rightBackTalon;

	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	private Gyro gyro;
	
	NetworkTable table;

	public DriveBase() {

		table = NetworkTable.getTable("camera");
		
		rightFrontTalon = new CANTalon(PortConstants.RIGHT_FRONT_TALON);
		rightMidTalon = new CANTalon(PortConstants.RIGHT_MID_TALON);
		rightBackTalon = new CANTalon(PortConstants.RIGHT_BACK_TALON);

		leftFrontTalon = new CANTalon(PortConstants.LEFT_FRONT_TALON);
		leftMidTalon = new CANTalon(PortConstants.LEFT_MID_TALON);
		leftBackTalon = new CANTalon(PortConstants.LEFT_BACK_TALON);

		leftEncoder = new Encoder(PortConstants.LEFT_ENCODER_PORT_A, PortConstants.LEFT_ENCODER_PORT_B);
		rightEncoder = new Encoder(PortConstants.RIGHT_ENCODER_PORT_A, PortConstants.RIGHT_ENCODER_PORT_B);

		leftEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		rightEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		rightEncoder.setReverseDirection(true);
		
		gyro = new ADXRS450_Gyro();

	}

	// sets the speed of each speed controller
	public void setSpeed(double leftSpeed, double rightSpeed) {
		leftFrontTalon.set(-leftSpeed);
		leftMidTalon.set(-leftSpeed);
		leftBackTalon.set(-leftSpeed);
		rightFrontTalon.set(rightSpeed);
		rightMidTalon.set(rightSpeed);
		rightBackTalon.set(rightSpeed);

	}

	// get the speed of the left wheel
	public double getLeftSpeed() {
		return leftFrontTalon.get();
	}

	// get the speed of the right wheel
	public double getRightSpeed() {
		return rightFrontTalon.get(); // return 0.0;
	}

	// get the distance in inches of the right wheel
	public double getRightWheelDistance() {
		return rightEncoder.getDistance();
	}

	// get the distance in inches of the left wheel
	public double getLeftWheelDistance() {
		return leftEncoder.getDistance();
	}

	// resets both encoders to zero
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void driveStraightDistance(double distance) {
		if (rightEncoder.getDistance() < distance && leftEncoder.getDistance() < distance) {
			if (leftEncoder.getDistance() + ALLOWANCE < rightEncoder.getDistance()) {
				setSpeed(0.3, 0.3);
			}
		}
	}
	
	public void driveStraight() {
		setSpeed(0.3, 0.3);
	}

	// give positive number
	public void driveStraightBackwards(double distance) {
		setSpeed(-0.3, -0.3);
	}

	public void turnLeft (double speed) {
		setSpeed (-speed, speed);
	}
	
	public void turnRight (double speed) {
		setSpeed (speed, -speed);
	}
	
	public void turn (double degrees) 
	{
		if (Math.abs(rightEncoder.getDistance()) < getAngle (degrees)) 
		{
			if (degrees < 0) 
			{
				turnLeft (0.2);
			} 
			else 
			{
				turnRight (0.2);
			}
		}
		else
		{
			setSpeed (0, 0);
		}
	}

	public double getAveragedDistance () {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}
	
	public double getAngle (double degrees) {
		double radians = Math.toRadians(degrees);
		double distanceToTurn = radians * ROBOT_RADIUS;
		return Math.abs(distanceToTurn);
	}
	
	public double getGyroAngle () {
		return gyro.getAngle();
	}
	
	public void resetGyro (){
		gyro.reset();
	}
	
	public void followLight () {
		double offset = table.getNumber("displacement", Double.POSITIVE_INFINITY);
		double height = table.getNumber("height", 0.0);
		
		double variableSpeed = Math.max(0.2, Math.min(1.0, height/240.0));
		
		if (Math.abs(offset) > OFFSET_TOLERANCE) {
			if (offset < 0) {
				turnLeft(variableSpeed);
			} else {
				turnRight(variableSpeed);
			}
		} else {
			setSpeed (variableSpeed, variableSpeed);
		}
		
	}
	
	public void displayDriveBaseStats () {
		SmartDashboard.putNumber("offset", table.getNumber("displacement", Double.POSITIVE_INFINITY));
		SmartDashboard.putNumber("left encoder", leftEncoder.get());
		SmartDashboard.putNumber("right encoder", rightEncoder.get());
		SmartDashboard.putNumber("left encoder inches", leftEncoder.getDistance());
		SmartDashboard.putNumber("right encoder inches", rightEncoder.getDistance());
		SmartDashboard.putNumber("height", table.getNumber("height", Double.POSITIVE_INFINITY));
		SmartDashboard.putNumber("size", table.getNumber("size", Double.POSITIVE_INFINITY));
		SmartDashboard.putNumber("gyro", gyro.getAngle());
	}
	
}
