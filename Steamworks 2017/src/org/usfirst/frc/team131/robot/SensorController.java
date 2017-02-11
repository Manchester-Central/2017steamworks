package org.usfirst.frc.team131.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;

public class SensorController {
	
	private Ultrasonic vexUltrasonic;
	
	private AnalogInput analogUltrasonic;
	
	//private Ultrasonic rightUltrasonic;
	private DigitalInput leftOpticalSensor;
	private DigitalInput rightOpticalSensor;
	
	public SensorController () {
		vexUltrasonic = new Ultrasonic(PortConstants.LEFT_ULTRASONIC_SENSOR_OUTPUT, PortConstants.LEFT_ULTRASONIC_SENSOR_INPUT);
		//rightUltrasonic = new Ultrasonic(PortConstants.RIGHT_ULTRASONIC_SENSOR_OUTPUT, PortConstants.RIGHT_ULTRASONIC_SENSOR_INPUT);
		
		analogUltrasonic = new AnalogInput(PortConstants.ANALOG_ULTRASONIC_SENSOR);
		leftOpticalSensor = new DigitalInput(PortConstants.LEFT_OPTICAL_SENSOR);
		rightOpticalSensor = new DigitalInput(PortConstants.RIGHT_OPTICAL_SENSOR);
		
		
	}
	
	/**
	 * TODO test range for analog ultrasonic sensor
	 * 
	 * @return
	 */
	public double getAnalogUltrasonic() {
		return analogUltrasonic.getVoltage() * 105; // ~.3 less (usually)
	}
	

	
	public boolean getleftOptical () {
		return leftOpticalSensor.get();
	}
	
	public boolean getRightOptical () {
		return rightOpticalSensor.get();
	}
	
}
