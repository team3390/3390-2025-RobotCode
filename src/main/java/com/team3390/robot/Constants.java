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
  public static final int[] ELEVATOR_ENCODER_ID = {0,1};
  public static final boolean ELEVATOR_ENCODER_INVERTED = false;
  public static final EncodingType ELEVATOR_ENCODER_ENCODING_TYPE = EncodingType.k2X;
}
