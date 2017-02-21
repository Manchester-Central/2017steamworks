package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	AutoController auto;
//	final String defaultAuto = "Default";
//	final String customAuto = "My Auto";
//	final String driveForward = "drive forward";
//	final String placeGear = "place gear";
//	String autoSelected;
//	SendableChooser<String> chooser = new SendableChooser<>();
	Relay cameraLED;
	Climber climber;
	ControllerOverseer CO;
	DriveBase drive;
	BallIntakeShooter shumper;
	Compressor compressor;
	GearFlopper gearFlopper;
	
	double offset;
	
	boolean compressorIsManuallyStopped;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		cameraLED = new Relay (0);
		compressorIsManuallyStopped = false;
		compressor = new Compressor();
		CO = new ControllerOverseer ();
		climber = new Climber ();
		drive = new DriveBase ();
		shumper = new BallIntakeShooter ();
		gearFlopper = new GearFlopper();		
		gearFlopper.retractGearFlopper();
		auto = new AutoController (drive, gearFlopper);
//		chooser.addDefault("Default Auto", defaultAuto);
//		chooser.addObject("My Auto", customAuto);
//		chooser.addObject("drive forward", driveForward);
//		chooser.addObject("place gear", placeGear);
//		SmartDashboard.putData("Auto choices", chooser);
		drive.resetEncoders();
		auto.setUpDashboard();
		compressor.start();
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
//		autoSelected = chooser.getSelected();
//		 autoSelected = SmartDashboard.getString("Auto Selector",
//		 defaultAuto);
//		System.out.println("Auto selected: " + autoSelected);
		auto.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		drive.displayDriveBaseStats();
	
		auto.run();
//		switch (autoSelected) {
//		case customAuto:
//			// Put custom auto code here
//			break;
//		case placeGear:
//			auto.placeGear(drive, gearFlopper, offset, 20.0, 0.0);
//			break;
//		case driveForward:
//			//auto.driveStraight(drive, 20000.0);
//			break;
//		case defaultAuto:
//		default:
//			// Put default auto code here
//			break;
//		}

	}

//	boolean compressorState = false;
	
//	toggle compressor logic
//	void processStartButton () 
//	{
//		if (CO.driver.buttonPressed(Controller.START_BUTTON))
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
//	
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopInit()
	{
	}
	
	public void teleopPeriodic()
	{
		drive.displayDriveBaseStats();
		// puts the offset to the dashboard
		//SmartDashboard.putNumber("offset", offset);
		
		// auto places the gear
		if (CO.driver.buttonPressed(Controller.RIGHT_TRIGGER)) {
			drive.resetEncoders();
			//auto.placeGear(drive, gearFlopper, offset, 30.0, 0.0);
		}
		cameraLED.set(Relay.Value.kForward);
		// for the physiognomy transmogufier		//the light
	    if (CO.operator.buttonPressed(Controller.LEFT_X_ABXY)) {
	    	cameraLED.set(Relay.Value.kForward);
		} else {
			cameraLED.set(Relay.Value.kOff);
		}
		
		//compressor regulation
		/*if (CO.operator.buttonPressed(Controller.UP_Y_ABXY)) {
			compressor.stop();
		} else if (compressor.enabled() == false) {
			compressor.start();
		}*/
		
		// shoots the with the shumper
		if (CO.operator.buttonPressed(Controller.RIGHT_BUMPER)) {
			shumper.shoot();
		} else if (CO.operator.buttonPressed(Controller.RIGHT_TRIGGER)) {// intakes with the shumper
			shumper.intake();
		} else {
			shumper.stop();
		}
		// sets drive speed
		drive.setSpeed(CO.driver.getLeftY(), CO.driver.getRightY());
		
		//sets climber speed
		climber.setClimberSpeed(CO.operator.getLeftY());
		
		//ejects gear 		// Uses the spring sensor to auto place gear
		if (gearFlopper.springActivated() == true || CO.operator.buttonPressed(Controller.LEFT_TRIGGER)) {
			gearFlopper.ejectGear();
		} else {
			gearFlopper.retractGearFlopper();
		}
		
		//cover positioning
		if (CO.operator.buttonPressed(Controller.RIGHT_B_ABXY)){
			//unsheaths cover
			gearFlopper.coverSet(DoubleSolenoid.Value.kForward);
		} else if (CO.operator.buttonPressed(Controller.DOWN_A_ABXY)){
			//sheaths cover
			gearFlopper.coverSet(DoubleSolenoid.Value.kReverse);
		} else {
			//
			if (gearFlopper.gearIsPresent() == true) {
				gearFlopper.coverSet(DoubleSolenoid.Value.kForward);
			} else {
				gearFlopper.coverSet(DoubleSolenoid.Value.kReverse);
			}
		}
		
		if (CO.driver.buttonPressed(Controller.RIGHT_BUMPER)) {
			drive.followLight();
		}
		
		//SmartDashboard.putString("Light Sensor Big Zambonie", Boolean.toString(gearFlopper.gearIsPresent()));
		//SmartDashboard.putString("Light Sensor Small Zambonie", Boolean.toString(gearFlopper.springActivated()));

		
		// toggles the compressor
//		processStartButton();

		
		//processAButton();
		
		//processBButton();
	
// Hola
	}
	
	@Override
	public void disabledPeriodic () {
		SmartDashboard.putString("Gear In", Boolean.toString(gearFlopper.gearIsPresent()));
		SmartDashboard.putString("Spring In", Boolean.toString(gearFlopper.springActivated()));
		drive.displayDriveBaseStats();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}

	
}

