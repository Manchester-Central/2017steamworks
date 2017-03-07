package org.usfirst.frc.team131.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {
	
	//private final double OFFSET_TOLERANCE = 10;

	private final double WHEEL_CIRCUMFERENCE = 4.0 * Math.PI;
	private static final double WHEEL_SPACING = 26.0;
	
	public static final double TOLERANCE = 1.0;
	public static final double TURN_TOLERANCE = 0.4;
	
	private final double PULSES_PER_REVOLUTION = 360.0;
	
	int counter = 0;

	//private final double ROBOT_RADIUS = 1.0;
	
	private double rightTarget = 0.0;
	private double leftTarget = 0.0;
	
	
	double forwardGain = 0.0;
	double turnGain = 0.0;
	
	final double minSpeed = 0.3;
	final double maxSpeed = 0.5;
	
	CANTalon leftFrontTalon;
	CANTalon leftMidTalon;
	CANTalon leftBackTalon;
	CANTalon rightFrontTalon;
	CANTalon rightMidTalon;
	CANTalon rightBackTalon;

	Victor raftMotorLeft;
	Victor raftMotorRight;
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
//	private Gyro gyro;
	
	

	public DriveBase() {

		
		
		setGains(0.01, 0.2);
		
		rightFrontTalon = new CANTalon(PortConstants.RIGHT_FRONT_TALON);
		rightMidTalon = new CANTalon(PortConstants.RIGHT_MID_TALON);
		rightBackTalon = new CANTalon(PortConstants.RIGHT_BACK_TALON);

		leftFrontTalon = new CANTalon(PortConstants.LEFT_FRONT_TALON);
		leftMidTalon = new CANTalon(PortConstants.LEFT_MID_TALON);
		leftBackTalon = new CANTalon(PortConstants.LEFT_BACK_TALON);
		
//		raftMotorLeft = new Victor(2);
//		raftMotorRight = new Victor(0);

		leftEncoder = new Encoder(PortConstants.LEFT_ENCODER_PORT_A, PortConstants.LEFT_ENCODER_PORT_B);
		rightEncoder = new Encoder(PortConstants.RIGHT_ENCODER_PORT_A, PortConstants.RIGHT_ENCODER_PORT_B);

		leftEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		rightEncoder.setDistancePerPulse(WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION);
		leftEncoder.setReverseDirection(true);
		//rightEncoder.setReverseDirection(true);
		
//		gyro = new ADXRS450_Gyro();

	}

	// sets the speed of each speed controller
	public void setSpeed(double leftSpeed, double rightSpeed) {
		leftFrontTalon.set(leftSpeed);
		leftMidTalon.set(leftSpeed);
		leftBackTalon.set(leftSpeed);
		rightFrontTalon.set(-rightSpeed);
		rightMidTalon.set(-rightSpeed);
		rightBackTalon.set(-rightSpeed);
		
//		raftMotorRight.set(rightSpeed);
//		raftMotorLeft.set(-leftSpeed);

	}

	// get the speed of the left wheel
	public double getLeftSpeed() {
		return leftFrontTalon.get();
//		return raftMotorLeft.get();
		
	}

	// get the speed of the right wheel
	public double getRightSpeed() {
		return rightFrontTalon.get(); // return 0.0;
//		return raftMotorRight.get();
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
	
	
//	public void drive(double rightDistance, double leftDistance) {
//		rightEncoder.getDistance();
//		setSpeed(0.3, 0.3);
//			
//	}
	
	public void setStraightTargetInches(double distance) {
		setTargetInches(distance, distance);
	}
	
	public void setTargetInches(double rightDistance, double leftDistance) {
		rightTarget = rightDistance;
		leftTarget = leftDistance;
	}
	
	public double getLeftTarget(){
		return leftTarget;
	}
	
	public double getRightTarget(){
		return rightTarget;
	}
	
	// Positive angles move right(clockwise)
	public void setPointTurnDegrees (double angle) {
//		leftTarget = (angle/360.0) * (WHEEL_SPACING * Math.PI) / 
//										(WHEEL_CIRCUMFERENCE);
//		rightTarget = (-angle/360.0) * (WHEEL_SPACING * Math.PI) / 
//				(WHEEL_CIRCUMFERENCE);
		leftTarget = (angle/360.0) * (WHEEL_SPACING * Math.PI);
		rightTarget = (-angle/360.0) * (WHEEL_SPACING * Math.PI);
	}
	
	public double getRightDistanceFromDegrees (double target) {
		//target *= WHEEL_CIRCUMFERENCE;
		target /= (WHEEL_SPACING * Math.PI);
		target *= -360;
		return target; // angle
	}
	
	public double getLeftDistanceFromDegrees (double target) {
		//target *= WHEEL_CIRCUMFERENCE;
		target /= (WHEEL_SPACING * Math.PI);
		target *= 360;
		return target; // angle
	}
	
	public void setGains(double forwardGain, double turnGain) {
		this.forwardGain = forwardGain;
		this.turnGain = turnGain;
	}
	
	public void tankCorrectedDrive (double leftPosition, double rightPosition)
	{
		double leftError  = leftTarget  - leftPosition;
		double rightError = rightTarget - rightPosition;
		
		double leftScaled  = (leftTarget  != 0) ? (leftError  / leftTarget)  : 0;
		double rightScaled = (rightTarget != 0) ? (rightError / rightTarget) : 0;
		double turnError  = (leftScaled - rightScaled) *
				            (leftTarget + rightTarget) /
				             2.0;
		
		double leftOutput  = (leftError  * forwardGain) + (turnError * turnGain);
		double rightOutput = (rightError * forwardGain) - (turnError * turnGain);
		
		double absLeftOutput  = Math.abs(leftOutput);
		double absRightOutput = Math.abs(rightOutput);
		
		if (absLeftOutput < minSpeed || absRightOutput < minSpeed) {
			if (absRightOutput > absLeftOutput) {
				if (absRightOutput != 0) {
					rightOutput = rightOutput * (minSpeed / absRightOutput);
					leftOutput  = leftOutput  * (minSpeed / absRightOutput);
				}
			} else {
				if (absLeftOutput != 0) {
					rightOutput = rightOutput * (minSpeed / absLeftOutput);
					leftOutput  = leftOutput  * (minSpeed / absLeftOutput);
				}
			}
		}
		else if (absRightOutput > maxSpeed || absLeftOutput > maxSpeed) {
			if (absRightOutput > absLeftOutput) {
				if (absRightOutput != 0) {
					rightOutput = rightOutput * (maxSpeed / absRightOutput);
					leftOutput  = leftOutput  * (maxSpeed / absRightOutput);
				}
			} else {
				if (absLeftOutput != 0) {
					rightOutput = rightOutput * (maxSpeed / absLeftOutput);
					leftOutput  = leftOutput  * (maxSpeed / absLeftOutput);
				}
			}
		}
		
		if (counter%1 == 0)
		{
			System.out.println("Left  Targ   = " + leftTarget);
			System.out.println("Right Targ   = " + rightTarget);
			System.out.println("Left  Input  = " + leftPosition);
			System.out.println("Right Input  = " + rightPosition);
			System.out.println("Left  Error  = " + leftError);
			System.out.println("Right Error  = " + rightError);
			System.out.println("Output = " + leftOutput + ",  " + rightOutput);
//			System.out.println("Right Output = " + rightOutput + "\n");
		}
		counter++;
		
		setSpeed (leftOutput, rightOutput);
	}
	
//	public void followCamera () {
//		double offset = table.getNumber("offset", 0.0);
//		//if ()
//	}
	
	public void arcadeCorrectedDrive (double forwardPosition, double turnPosition){
		leftTarget = forwardPosition - turnPosition;
		rightTarget = forwardPosition + turnPosition;
	}
	
//	public void driveStraight() {
//		setSpeed(0.3, 0.3);
//	}

	// give positive number
//	public void driveStraightBackwards(double distance) {
//		setSpeed(-0.3, -0.3);
//	}

//	public void turnLeft (double speed) {
//		setSpeed (-speed, speed);
//	}
	
//	public void turnRight (double speed) {
//		setSpeed (speed, -speed);
//	}
	
//	public void turn (double degrees) 
//	{
//		if (Math.abs(rightEncoder.getDistance()) < getAngle (degrees)) 
//		{
//			if (degrees < 0) 
//			{
//				turnLeft (0.2);
//			} 
//			else 
//			{
//				turnRight (0.2);
//			}
//		}
//		else
//		{
//			setSpeed (0, 0);
//		}
//	}

	public double getAveragedDistance () {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}
	
//	public double getAngle (double degrees) {
//		double radians = Math.toRadians(degrees);
//		double distanceToTurn = radians * ROBOT_RADIUS;
//		return Math.abs(distanceToTurn);
//	}
	
//	public double getGyroAngle () {
//		return gyro.getAngle();
//	}
//	
	//TODO change for robot
	public void resetGyro (){
	//	gyro.reset();
	}
	
//	public void followLight () {
//		double offset = table.getNumber("displacement", Double.POSITIVE_INFINITY);
//		double height = table.getNumber("height", 0.0);
//		
//		double variableSpeed = Math.max(0.2, Math.min(1.0, height/240.0));
//		
//		if (Math.abs(offset) > OFFSET_TOLERANCE) {
//			if (offset < 0) {
//				turnLeft(variableSpeed);
//			} else {
//				turnRight(variableSpeed);
//			}
//		} else {
//			setSpeed (variableSpeed, variableSpeed);
//		}
//		
//	}
	
	public void displayDriveBaseStats () {
		SmartDashboard.putNumber("left encoder", leftEncoder.get());
		SmartDashboard.putNumber("right encoder", rightEncoder.get());
		SmartDashboard.putNumber("left encoder inches", leftEncoder.getDistance());
		SmartDashboard.putNumber("right encoder inches", rightEncoder.getDistance());
		//SmartDashboard.putNumber("gyro", gyro.getAngle());
	}
	
}
