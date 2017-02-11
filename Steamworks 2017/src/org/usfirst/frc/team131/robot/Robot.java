package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//final int PORT = 3;
	//AutoController auto;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	final String driveForward = "drive forward";
	final String placeGear = "place gear";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	//SpeedController test;
	
	Climber climber;
	ControllerOverseer CO;
	DriveBase drive;
	//Compressor compressor;
	//GearFlopper gearFlopper;
	//SensorController sensor;
	//CameraServer cv;

	boolean compressorIsManuallyStopped;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//test = new Victor (PORT);
		//auto = new AutoController ();
		compressorIsManuallyStopped = false;
		//compressor = new Compressor();
		CO = new ControllerOverseer ();
		climber = new Climber ();
		drive = new DriveBase ();
		//gearFlopper = new GearFlopper();
		//sensor = new SensorController();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		chooser.addObject("drive forward", driveForward);
		chooser.addObject("place gear", placeGear);
		SmartDashboard.putData("Auto choices", chooser);
		//drive.resetEncoders();
		//cv.getInstance().startAutomaticCapture();
	}
	

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		drive.resetEncoders();
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("right wheel", drive.getRightWheelDistance());
		SmartDashboard.putNumber("left wheel", drive.getLeftWheelDistance());

		
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case placeGear:
			//auto.placeGear(drive, gearFlopper, 10.0);
			break;
		case driveForward:
			//auto.driveStraight(drive, 20000.0);
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	boolean compressorState = false;
	
	boolean upperSolenoidState = false;
	
	boolean lowerSolenoidState = false;
	
//	public void processStartButton () 
//	{
//		if (CO.operator.buttonPressed(Controller.START_BUTTON))
//		{
//			if (compressorState == compressor.enabled())
//			{	
//				if (compressorState == false)
//				{
//					compressor.start();
//				}
//				else
//				{
//					compressor.stop();
//				}
//			}
//		}
//		else
//		{
//			compressorState = compressor.enabled();
//		}
//	}
	
	
//	private void processAButton()
//	{
//		if (CO.operator.buttonPressed(Controller.DOWN_A_ABXY))
//		{
//			if (upperSolenoidState == gearFlopper.getGearPusher())
//			{	
//				if (upperSolenoidState == false)
//				{
//					gearFlopper.gearPusherSet(true);
//				}
//				else
//				{
//					gearFlopper.gearPusherSet(false);
//				}
//			}
//		}
//		else
//		{
//			upperSolenoidState = gearFlopper.getGearPusher();
//		}
//	}
	
//	public void processBButton () 
//	{
//		if (CO.operator.buttonPressed(Controller.RIGHT_B_ABXY))
//		{
//			if (lowerSolenoidState == gearFlopper.getDoor())
//			{	
//				if (lowerSolenoidState == false)
//				{
//					gearFlopper.doorSet(true);
//				}
//				else
//				{
//					gearFlopper.doorSet(false);
//				}
//			}
//		}
//		else
//		{
//			lowerSolenoidState = gearFlopper.getDoor();
//		}
//	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		
		drive.setSpeed(CO.driver.getLeftY(), CO.driver.getRightY());
		
		climber.setClimberSpeed(CO.operator.getLeftY());
		
		//SmartDashboard.putBoolean("compressor is low", compressor.getPressureSwitchValue());
		//SmartDashboard.putNumber("current used by compressor", compressor.getCompressorCurrent()); 
		//SmartDashboard.putNumber("Analog Sensor", sensor.getAnalogUltrasonic());
		//SmartDashboard.putBoolean("right optical", sensor.getRightOptical());
		//SmartDashboard.putBoolean("left optical", sensor.getleftOptical());
		/*
		SmartDashboard.putNumber("Vex Sensor", sensor.getVexUltrasonic());
		*/
		//test.set(CO.operator.getLeftY());
		
		
		//processStartButton ();
		
		//processAButton ();
		
		//processBButton ();
		//controls the direction of the climber
//		if (CO.operator.buttonPressed(CO.operator.LEFT_TRIGGER)) {
//			climber.setClimberSpeed(1.0);
//		} else if (CO.operator.buttonPressed(CO.operator.LEFT_BUMPER)) {
//			climber.setClimberSpeed(-1.0);
//		} else {
//			climber.setClimberSpeed(0.0);
//		}
		
//		drive.setSpeed(CO.driver.getLeftY(), CO.driver.getRightY());
		
		//SmartDashboard.putNumber("left distance traveled", drive.getLeftWheelDistance());
		//SmartDashboard.putNumber("right distance traveled", drive.getRightWheelDistance());
		
//		if (compressor.enabled()) {
//			if (climber.isClimbing() || compressorIsManuallyStopped) {
//				compressor.stop();
//				SmartDashboard.putBoolean("compressor is started", false);
//			}
//		} else {
//			if (!(climber.isClimbing() || compressorIsManuallyStopped)) {
//				compressor.start();
//				SmartDashboard.putBoolean("compressor is started", true);
//			}
//		}
		
		//gearFlopper.setSpeed(CO.operator.getRightY());
	}
	
	@Override
	public void disabledPeriodic () {
		//compressor.stop();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}

