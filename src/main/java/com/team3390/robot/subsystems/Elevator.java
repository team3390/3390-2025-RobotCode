// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

  private static Elevator instance;

  private boolean isBreakMode = false;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX elevatorMotorMaster, elevatorMotorSlave;
  private final Encoder elevatorEncoder;

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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void encoderReset() {
    elevatorEncoder.reset();
  }

  public double encoderAngle() {
    return elevatorEncoder.getRate();
  }

  public void setSpeed(double speed) {
    if(speed != 0) {
      elevatorMotorMaster.set(speed);
    }
  }

  public void stopMotors() {
    elevatorMotorMaster.stopMotor();
  }

}
