// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

  private static Elevator instance;

  private boolean isBreakMode = false;
  private double encoderAngle;
  private boolean isAtTop;
  private boolean isAtBottom;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX elevatorMotorMaster, elevatorMotorSlave;
  private final Encoder elevatorEncoder;
  private final PID elevatorPID;
  private final DigitalInput bottomSwitch = new DigitalInput(Constants.ELEVATOR_BOTTOM_SWITCH_ID);
  private final DigitalInput topSwitch = new DigitalInput(Constants.ELEVATOR_TOP_SWITCH_ID);

  public synchronized static Elevator getInstance() {
    if(instance == null) {
      instance = new Elevator();
    }
    return instance;
  }

  /** Creates a new Arm. */
  public Elevator() {
    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    elevatorMotorMaster = TalonSRXCreator.createTalon(Constants.ELEVATOR_MOTOR_MASTER_ID, talonConfiguration);
    elevatorMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.ELEVATOR_MOTOR_SLAVE_ID,
    Constants.ELEVATOR_MOTOR_MASTER_ID, talonConfiguration);
    elevatorEncoder = new Encoder(Constants.ELEVATOR_ENCODER_ID[0], Constants.ELEVATOR_ENCODER_ID[1],
    Constants.ELEVATOR_ENCODER_INVERTED, Constants.ELEVATOR_ENCODER_ENCODING_TYPE);
    elevatorPID = new PID(Constants.ELEVATOR_PID_KP, Constants.ELEVATOR_PID_KI, Constants.ELEVATOR_PID_KD, 
    Constants.ELEVATOR_PID_TOLERANCE, Constants.ELEVATOR_PID_MAXOUT, Constants.ELEVATOR_PID_MINOUT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void encoderReset() {
    elevatorEncoder.reset();
  }

  public void setEncoderAngle(double angle) {
    angle = elevatorEncoder.getRate();
    this.encoderAngle = angle;
  }

  public double getEncoderAngle() {
    return encoderAngle;
  }

  public void setSpeed(double speed) {
    if(speed != 0) {
      elevatorMotorMaster.set(speed);
    }
  }

  public void stopMotors() {
    elevatorMotorMaster.stopMotor();
  }

  public void setElevatorPos(double input, double pos) {
    elevatorPID.setSetpoint(pos);
    double output = elevatorPID.output(elevatorPID.calculate(input, pos));
    elevatorMotorMaster.set(output);
  }

  public boolean atBorder() {
    if(encoderAngle == Constants.ELEVATOR_BORDER) {
      return true;
    }
    else {
      return false;
    }
  }

  public void setTopSwitch(boolean isAtBorder) {
    isAtBorder = topSwitch.get();
    this.isAtTop = isAtBorder;
  }

  public boolean AtTop() {
    return isAtTop;
  }

  public void setBottomSwitch(boolean isAtBorder) {
    isAtBorder = bottomSwitch.get();
    this.isAtBottom = isAtBorder;
  }

  public boolean AtBottom() {
    return isAtBottom;
  }

}
