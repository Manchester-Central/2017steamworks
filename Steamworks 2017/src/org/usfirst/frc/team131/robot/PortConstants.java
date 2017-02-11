package org.usfirst.frc.team131.robot;

public interface PortConstants {
	
	boolean isRaftMode = false; // true if raft mode, false if robot mode
	
	// ROBOT-MODE -------------------------------------------+
	//                                                       |                                          |
	// RAFT-MODE  -------------------------------------+     |
	//                                                 |     |
	//                                                 |     |
	
	//Pneumatics
	int UPPER_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 5  : -1;
	int LOWER_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 6  : -1;
	
	//CAN constants
	int RIGHT_FRONT_TALON		        = isRaftMode ? 2  :  3;
	int RIGHT_MID_TALON			        = isRaftMode ? 2  :  4;
	int RIGHT_BACK_TALON		        = isRaftMode ? 2  :  5;
	
	int LEFT_FRONT_TALON		        = isRaftMode ? 2  :  6;
	int LEFT_MID_TALON		    	    = isRaftMode ? 2  :  7; 	
	int LEFT_BACK_TALON		   	 	    = isRaftMode ? 2  :  8;

	int INTAKE_SPARK_PORT				= isRaftMode ? -1 :  3;
	int SHOOTER_SPARK_PORT				= isRaftMode ? -1 :  2;
	int CHOICE_ROLLER_PORT				= isRaftMode ? -1 :  1;
	int AGITATOR_SPARK_PORT		    	= isRaftMode ? -1 :  0;
	
	int SRX_TALON_ONE_PORT				= isRaftMode ?  3 : -1;
	
	int CLIMBER_PORT_ONE                = isRaftMode ? 6  :  4;
	int CLIMBER_PORT_TWO                = isRaftMode ? 6  :  5;
	
	// digital constants
	int RIGHT_ENCODER_PORT_A            = isRaftMode ? 0  :  1;
	int RIGHT_ENCODER_PORT_B            = isRaftMode ? 1  :  0;
	
	int LEFT_ENCODER_PORT_A             = isRaftMode ? 2  :  3;
	int LEFT_ENCODER_PORT_B             = isRaftMode ? 3  :  2;
	
	int RIGHT_OPTICAL_SENSOR		    = isRaftMode ? 4  : -1;
	int LEFT_OPTICAL_SENSOR 			= isRaftMode ? 5  : -1;
	
	int LEFT_ULTRASONIC_SENSOR_INPUT 	= isRaftMode ? 6  : -1;
	int LEFT_ULTRASONIC_SENSOR_OUTPUT 	= isRaftMode ? 7  : -1;
	
	int RIGHT_ULTRASONIC_SENSOR_INPUT 	= isRaftMode ? 8  : -1;
	int RIGHT_ULTRASONIC_SENSOR_OUTPUT 	= isRaftMode ? 9  : -1;
	
	// analog constants
	int ANALOG_ULTRASONIC_SENSOR 		= isRaftMode ? 0 : -1;
}
