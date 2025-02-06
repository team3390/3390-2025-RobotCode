package com.team3390.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public final class Constants {
  public static final int DRIVE_LEFT_MASTER_ID = 0;
  public static final int DRIVE_LEFT_SLAVE_ID = 1;
  public static final int DRIVE_RIGHT_MASTER_ID = 2;
  public static final int DRIVE_RIGHT_SLAVE_ID = 3;
  public static final boolean DRIVE_LEFT_INVERTED = true;
  public static final boolean DRIVE_RIGHT_INVERTED = false;

  public static final int JOYSTICK_LEFT_PORT = 0;
  public static final int JOYSTICK_RIGHT_PORT = 1;
  public static final int JOYSTICK_GAMEPAD_PORT = 2;

  public static final int ELEVATOR_MOTOR_MASTER_ID = 4;
  public static final int ELEVATOR_MOTOR_SLAVE_ID = 5;
  public static final int ELEVATOR_TOP_SWITCH_ID = 0;
  public static final int ELEVATOR_BOTTOM_SWITCH_ID = 1;
  public static final int[] ELEVATOR_ENCODER_ID = {0,1};
  public static final boolean ELEVATOR_ENCODER_INVERTED = false;
  public static final EncodingType ELEVATOR_ENCODER_ENCODING_TYPE = EncodingType.k2X;
  public static final double ELEVATOR_PID_KP = 0.1;
  public static final double ELEVATOR_PID_KI = 0.1;
  public static final double ELEVATOR_PID_KD = 0.1;
  public static final double ELEVATOR_PID_MAXOUT = 1;
  public static final double ELEVATOR_PID_MINOUT = -1;
  public static final double ELEVATOR_PID_TOLERANCE = 10;
  public static final double ELEVATOR_L1_POS = 0;
  public static final double ELEVATOR_L2_POS = 1;
  public static final double ELEVATOR_L3_POS = 2;
  public static final double ELEVATOR_L4_POS = 3;
  public static final double ELEVATOR_BORDER = 5;

  public static final int MANIPULATOR_PIVOT_MOTOR_MASTER_ID = 6;
  public static final int MANIPULATOR_PIVOT_MOTOR_SLAVE_ID = 7;
  public static final int MANIPULATOR_INTAKE_MOTOR_MASTER_ID = 8;
  public static final int MANIPULATOR_INTAKE_MOTOR_SLAVE_ID = 9;
  public static final int[] MANIPULATOR_ENCODER_ID = {2,3};
  public static final boolean MANIPULATOR_ENCODER_INVERTED = false;
  public static final EncodingType MANIPULATOR_ENCODER_ENCODING_TYPE = EncodingType.k2X;
  public static final double MANIPULATOR_PID_KP = 0.1;
  public static final double MANIPULATOR_PID_KI = 0.1;
  public static final double MANIPULATOR_PID_KD = 0.1;
  public static final double MANIPULATOR_PID_MAXOUT = 1;
  public static final double MANIPULATOR_PID_MINOUT = -1;
  public static final double MANIPULATOR_PID_TOLERANCE = 10;
  public static final double MANIPULATOR_L1_POS = 0;
  public static final double MANIPULATOR_L2_POS = 1;
  public static final double MANIPULATOR_L3_POS = 2;
  public static final double MANIPULATOR_L4_POS = 3;
}
