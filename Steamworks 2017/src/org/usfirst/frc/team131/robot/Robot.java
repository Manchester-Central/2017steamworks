package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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
	private static final double CAMERA_CETROID = 0;
	AutoController auto;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	final String driveForward = "drive forward";
	final String placeGear = "place gear";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	NetworkTable table;
	Climber climber;
	ControllerOverseer CO;
	DriveBase drive;
	BallIntakeShooter shumper;
	Compressor compressor;
	GearFlopper gearFlopper;
	SensorController SC;
	
	private enum State {
		INITIAL, DO_NOTHING, DRIVE_FORWARD, DRIVE_BACKWARD, PLACE_GEAR, TURN
	}
	
	double offset;
	
	boolean compressorIsManuallyStopped;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		auto = new AutoController ();
		compressorIsManuallyStopped = false;
		compressor = new Compressor(1);
		CO = new ControllerOverseer ();
		climber = new Climber ();
		drive = new DriveBase ();
		shumper = new BallIntakeShooter ();
		gearFlopper = new GearFlopper();		
		table = NetworkTable.getTable("camera");
		gearFlopper = new GearFlopper();
		SC = new SensorController ();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		chooser.addObject("drive forward", driveForward);
		chooser.addObject("place gear", placeGear);
		SmartDashboard.putData("Auto choices", chooser);
		drive.resetEncoders();
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
	
	void processOffset () {

		offset = table.getNumber("displacement", Double.POSITIVE_INFINITY); 
		
	}
	
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
		
		processOffset();
		
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case placeGear:
			auto.placeGear(drive, gearFlopper, SC, offset, 20.0);
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
	
	public void processStartButton () 
	{
		if (CO.driver.buttonPressed(Controller.START_BUTTON))
		{
			if (compressorState == compressor.enabled())
			{	
				if (compressorState == false)
				{
					compressor.start();
				}
				else
				{
					compressor.stop();
				}
			}
		}
		else
		{
			compressorState = compressor.enabled();
		}
	}
	
	
	private void processAButton()
	{
		if (CO.driver.buttonPressed(Controller.DOWN_A_ABXY))
		{
			if (upperSolenoidState == gearFlopper.getGearPusher())
			{	
				if (upperSolenoidState == false)
				{
					gearFlopper.gearPusherSet(true);
				}
				else
				{
					gearFlopper.gearPusherSet(false);
				}
			}
		}
		else
		{
			upperSolenoidState = gearFlopper.getGearPusher();
		}
	}
	
	public void processBButton () 
	{
		if (CO.driver.buttonPressed(Controller.RIGHT_B_ABXY))
		{
			if (lowerSolenoidState == gearFlopper.getDoor())
			{	
				if (lowerSolenoidState == false)
				{
					gearFlopper.doorSet(true);
				}
				else
				{
					gearFlopper.doorSet(false);
				}
			}
		}
		else
		{
			lowerSolenoidState = gearFlopper.getDoor();
		}
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		
		// puts the offset to the dashboard
		SmartDashboard.putNumber("offset", offset);
		
		// processes camera centroid offset
		processOffset();
		
		State state = State.INITIAL;
		
		double distanceCanOffset = 7;
		double distToTower = SC.getAnalogUltrasonic();
		switch (state)
		{
			case INITIAL:
				state = State.TURN;
				SmartDashboard.putString("Initial", "done");
				break;
			case TURN:
				if (Math.abs(SC.getAnalogUltrasonic()) > distanceCanOffset) {
				} else {
					SmartDashboard.putString("Turn", "done");
					state = state.DRIVE_FORWARD;
				}
				break;
			case DRIVE_FORWARD:
				SmartDashboard.putString("Drive Forward", "done");
				break;
			case PLACE_GEAR:
				state = State.DO_NOTHING;
				SmartDashboard.putString("Place Gear", "done");
			case DO_NOTHING:
			default:
				SmartDashboard.putString("Do Nothing", "done");

		}
		
		//
		if (CO.driver.buttonPressed(Controller.RIGHT_TRIGGER)) {
			drive.resetEncoders();
			auto.placeGear(drive, gearFlopper, SC, offset, 20.0);
		}
		
		if (CO.operator.buttonPressed(Controller.RIGHT_BUMPER)) {
			shumper.shoot();
		}
		
		if (CO.operator.buttonPressed(Controller.RIGHT_TRIGGER)) {
			shumper.intake();
		}
		
		drive.setSpeed(CO.driver.getLeftY(), CO.driver.getRightY());
		
		climber.setClimberSpeed(CO.operator.getLeftY());
		
		if (CO.operator.buttonPressed(Controller.LEFT_TRIGGER)){
			gearFlopper.ejectGear(2000L);
		}
		
		if (CO.operator.buttonPressed(Controller.RIGHT_B_ABXY)){
			gearFlopper.coverSet(true);
		}
		
		if (CO.operator.buttonPressed(Controller.DOWN_A_ABXY)){
			gearFlopper.coverSet(false);
		}

		// test code used for testing
//		shumper.setAgitatorSpeed(CO.operator.getLeftX());
//		shumper.setBackerSpeed(CO.operator.getRightX());
//		shumper.setShooterSpeed(CO.operator.getLeftY());
//		shumper.setIntakeSpeed(CO.operator.getRightY());
		// automatic cover 
		if (gearFlopper.gearIsPresent()){
			gearFlopper.coverSet(true);
		}
		else {
			gearFlopper.coverSet(false);
		}
		
		processStartButton();

		processAButton();
		
		processBButton();
	
// Hola
	}
	
	@Override
	public void disabledPeriodic () {
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}

	
}

