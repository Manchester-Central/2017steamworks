package org.usfirst.frc.team131.robot;

public interface PortConstants {
	
	boolean isRaftMode = true; // true if raft mode, false if robot mode
	
	// ROBOT-MODE -------------------------------------------+
	//                                                       |                                          |
	// RAFT-MODE  -------------------------------------+     |
	//                                                 |     |
	//                                                 |     |
	int UPPER_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 5  : -1;
	int LOWER_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 6  : -1;
	
	// analog constants
	int LEFT_SPEED_CONTROLLER_PORT      = isRaftMode ? 2  : -1;
	int RIGHT_SPEED_CONTROLLER_PORT     = isRaftMode ? 0  : -1;
	
	int INTAKE_SPARK_PORT				= isRaftMode ? -1 :  0;
	int SHOOTER_SPARK_PORT				= isRaftMode ? -1 :  1;
	int CHOICE_ROLLER_PORT				= isRaftMode ? -1 :  2;
	int AGITATOR_SPARK_PORT		    	= isRaftMode ? -1 :  3;
	
	int UPPER_GEAR_FLOPPER_LIMIT_SWITCH = isRaftMode ? 3  : -1;
	int LOWER_GEAR_FLOPPER_LIMIT_SWITCH = isRaftMode ? 4  : -1;

	int CLIMBER_PORT                    = isRaftMode ? 6  : -1;
	
	// digital constants
	int RIGHT_ENCODER_PORT_ONE  /*---*/ = isRaftMode ? 0  : -1;
	int RIGHT_ENCODER_PORT_TWO          = isRaftMode ? 1  : -1;
	int LEFT_ENCODER_PORT_ONE /*-----*/ = isRaftMode ? 2  : -1;
	int LEFT_ENCODER_PORT_TWO           = isRaftMode ? 3  : -1;
	
	int RIGHT_OPTICAL_SENSOR		    = isRaftMode ? 4  : -1;
	int LEFT_OPTICAL_SENSOR 			= isRaftMode ? 5  : -1;
	
	int LEFT_ULTRASONIC_SENSOR_INPUT 	= isRaftMode ? 6  : -1;
	int LEFT_ULTRASONIC_SENSOR_OUTPUT 	= isRaftMode ? 7  : -1;
	
	int RIGHT_ULTRASONIC_SENSOR_INPUT 	= isRaftMode ? 8  : -1;
	int RIGHT_ULTRASONIC_SENSOR_OUTPUT 	= isRaftMode ? 9  : -1;
	
	
	int ANALOG_ULTRASONIC_SENSOR 		= isRaftMode ? 0 : -1;
}
