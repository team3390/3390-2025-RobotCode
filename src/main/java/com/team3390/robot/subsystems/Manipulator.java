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

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Manipulator extends SubsystemBase {

  private static Manipulator instance;

  private boolean isBreakMode = false;
  private double encoderAngle;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX pivotMotorMaster, pivotMotorSlave, intakeMotorMaster, intakeMotorSlave;
  private final Encoder manipulatorEncoder;
  private final PID manipulatorPID;

  public synchronized static Manipulator getInstance(){
    if(instance == null) {
      instance = new Manipulator();
    }
    return instance;
  }

  /** Creates a new Manipulator. */
  public Manipulator() {
    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotorMaster = TalonSRXCreator.createTalon(Constants.MANIPULATOR_PIVOT_MOTOR_MASTER_ID, talonConfiguration);
    pivotMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.MANIPULATOR_PIVOT_MOTOR_SLAVE_ID,
    Constants.MANIPULATOR_PIVOT_MOTOR_MASTER_ID, talonConfiguration);
    intakeMotorMaster = TalonSRXCreator.createTalon(Constants.MANIPULATOR_INTAKE_MOTOR_MASTER_ID, talonConfiguration);
    intakeMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.MANIPULATOR_INTAKE_MOTOR_SLAVE_ID,
    Constants.MANIPULATOR_INTAKE_MOTOR_MASTER_ID, talonConfiguration);
    manipulatorEncoder = new Encoder(Constants.MANIPULATOR_ENCODER_ID[0], Constants.MANIPULATOR_ENCODER_ID[1],
    Constants.MANIPULATOR_ENCODER_INVERTED, Constants.MANIPULATOR_ENCODER_ENCODING_TYPE);
    manipulatorPID = new PID(Constants.MANIPULATOR_PID_KP, Constants.MANIPULATOR_PID_KI, Constants.MANIPULATOR_PID_KD, 
    Constants.MANIPULATOR_PID_TOLERANCE, Constants.MANIPULATOR_PID_MAXOUT, Constants.MANIPULATOR_PID_MINOUT);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("manipulator encoder", encoderAngle);
    // This method will be called once per scheduler run
  }

  public void encoderReset() {
    manipulatorEncoder.reset();
  }

  public void setEncoderAngle() {
    encoderAngle = manipulatorEncoder.getRate();
  }

  public double getEncoderAngle() {
    return encoderAngle;
  }

  public void setPivotSpeed(double speed) {
    if(speed != 0) {
      pivotMotorMaster.set(speed);
    }
  }

  public void stopPivotMotors() {
    pivotMotorMaster.stopMotor();
  }

  public void setIntakeMotors(double speed) {
    if(speed != 0) {
      intakeMotorMaster.set(speed);
    }
  }

  public void stopIntakeMotors() {
    intakeMotorMaster.stopMotor();
  }

  public void setManipulatorPos(double input, double pos) {
    manipulatorPID.setSetpoint(pos);
    double output = manipulatorPID.output(manipulatorPID.calculate(input, pos));
    pivotMotorMaster.set(output);
  }

  public boolean atSetpoint() {
    return manipulatorPID.atSetpoint();
  }

}
