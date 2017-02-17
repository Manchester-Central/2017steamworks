package org.usfirst.frc.team131.robot;

public interface PortConstants {
	
	boolean isRaftMode = true; // true if raft mode, false if robot mode
	
	// ROBOT-MODE -------------------------------------------+
	//                                                       |                                          |
	// RAFT-MODE  -------------------------------------+     |
	//                                                 |     |
	//                                                 |     |
	
	//Pneumatics
	int LOWER_FRONT_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 5  : -1;
	int LOWER_BACK_PNEUMATIC_CHANNEL  /*---*/ = isRaftMode ? 6  : -1;
	int UPPER_FRONT_PNEUMATIC_CHANNEL /*---*/ = isRaftMode ? 7  : -1;
	int UPPER_BACK_PNEUMATIC_CHANNEL  /*---*/ = isRaftMode ? 4  : -1;
	
	
	//CAN constants
	int RIGHT_FRONT_TALON		        = isRaftMode ? -1  :  12;
	int RIGHT_MID_TALON			        = isRaftMode ? -1  :  14;
	int RIGHT_BACK_TALON		        = isRaftMode ? -1  :  16;
	
	int LEFT_FRONT_TALON		        = isRaftMode ? -1  :  15;
	int LEFT_MID_TALON		    	    = isRaftMode ? -1  :  13; 	
	int LEFT_BACK_TALON		   	 	    = isRaftMode ? -1  :  11;

	int INTAKE_SPARK_PORT				= isRaftMode ? 6 :  3;
	int SHOOTER_SPARK_PORT				= isRaftMode ? 7 :  2;
	int CHOICE_ROLLER_PORT				= isRaftMode ? 8 :  1;
	int AGITATOR_IS_BACK				= isRaftMode ? 9 :	0;
	
	int SRX_TALON_ONE_PORT				= isRaftMode ?  3 : -1;
	
	int CLIMBER_PORT_ONE                = isRaftMode ? 5  :  4;
	int CLIMBER_PORT_TWO                = isRaftMode ? 5  :  5;
	
	// digital constants
	int RIGHT_ENCODER_PORT_A            = isRaftMode ? -1  :  1;
	int RIGHT_ENCODER_PORT_B            = isRaftMode ? -1  :  0;
	
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
