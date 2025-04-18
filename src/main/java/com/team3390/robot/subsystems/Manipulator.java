// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Manipulator extends SubsystemBase {

  private static Manipulator instance;

  private boolean isBreakMode = true;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX pivotMotor;

  public synchronized static Manipulator getInstance(){
    if(instance == null) {
      instance = new Manipulator();
    }
    return instance;
  }

  /** Creates a new Manipulator. */
  public Manipulator() {
    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotor = TalonSRXCreator.createTalon(Constants.MANIPULATOR_PIVOT_MOTOR_ID, talonConfiguration);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPivotSpeed(double speed) {
    if(speed != 0) {
      pivotMotor.set(speed);
    }
  }

  public void stopPivotMotors() {
    pivotMotor.stopMotor();
  }

}
