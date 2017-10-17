package org.usfirst.frc.team131.robot;

public interface PortConstants {
	
	boolean isRaftMode = false; // true if raft mode, false if robot mode
	
	// ROBOT-MODE -------------------------------------------------+
	//                                                             |                                          |
	// RAFT-MODE  -------------------------------------------+     |
	//                                                       |     |
	//      NEGATIVE ONES IN TELEOP CALLED ELSEWHERE KILLS THE RIO CODE!!!                                                 |     |
	
	//Pneumatics
	int GEAR_COVER_P_CHANNEL_A        /*---*/ = isRaftMode ? 5   : 2;
	int GEAR_COVER_P_CHANNEL_B        /*---*/ = isRaftMode ? 6   : 3;
	int GEAR_PUSHER_P_CHANNEL_A       /*---*/ = isRaftMode ? 5   : 0;
	int GEAR_PUSHER_P_CHANNEL_B       /*---*/ = isRaftMode ? 6   : 1;
	int GEAR_DOOR_P_CHANNEL_A         /*---*/ = isRaftMode ? -1  : 7;
	int GEAR_DOOR_P_CHANNEL_B         /*---*/ = isRaftMode ? -1  : 6;
	
	//CAN constants
	int RIGHT_FRONT_TALON		            = isRaftMode ? -1  :  12;
	int RIGHT_MID_TALON			            = isRaftMode ? -1  :  14;
	int RIGHT_BACK_TALON		            = isRaftMode ? -1  :  16;
	
	int LEFT_FRONT_TALON		            = isRaftMode ? -1  :  15;
	int LEFT_MID_TALON		    	        = isRaftMode ? -1  :  13; 	
	int LEFT_BACK_TALON		   	 	        = isRaftMode ? -1  :  11;

	int FRONT_SPARK_PORT				       = isRaftMode ? 6 :  3;
	int BACK_SPARK_PORT				       = isRaftMode ? 7 :  2;
	int TOP_SPARK_PORT				       = isRaftMode ? 8 :  1;
	int AGITATOR_SPARK_PORT			           = isRaftMode ? 9 :  0;
	
	int SRX_TALON_ONE_PORT				      = isRaftMode ?  3 :  3;
	
	int CLIMBER_PORT_ONE                      = isRaftMode ? 5  :  4;
	int CLIMBER_PORT_TWO                      = isRaftMode ? 5  :  5;
	
	// digital constants
	int RIGHT_ENCODER_PORT_A                 = isRaftMode ? -1  :  1;
	int RIGHT_ENCODER_PORT_B                 = isRaftMode ? -1  :  0;
	
	int LEFT_ENCODER_PORT_A                   = isRaftMode ? 2  :  3;
	int LEFT_ENCODER_PORT_B                   = isRaftMode ? 3  :  2;
	
	int GEAR_SENSOR		                      = isRaftMode ? 4  :  4;
	int SPRING_SENSOR 		       	          = isRaftMode ? 5  :  5;
	
	int LEFT_ULTRASONIC_SENSOR_INPUT 	      = isRaftMode ? 6  : -1;
	int LEFT_ULTRASONIC_SENSOR_OUTPUT 	      = isRaftMode ? 7  : -1;
	
	int RIGHT_ULTRASONIC_SENSOR_INPUT 	      = isRaftMode ? 8  : -1;
	int RIGHT_ULTRASONIC_SENSOR_OUTPUT 	      = isRaftMode ? 9  : -1;
	
	// analog constants
	int ANALOG_ULTRASONIC_SENSOR 		       = isRaftMode ? 0 : -1;
}