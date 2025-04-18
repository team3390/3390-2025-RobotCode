// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

  private static Elevator instance;

  private boolean isBreakMode = false;
  private boolean isAtTop = true;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX elevatorMotorMaster, elevatorMotorSlave;
  private final Solenoid brake; 
  private final DigitalInput topSwitch; 

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
    elevatorMotorSlave = TalonSRXCreator.createTalon(Constants.ELEVATOR_MOTOR_SLAVE_ID, talonConfiguration);
    brake = new Solenoid(PneumaticsModuleType.REVPH, 14);
    topSwitch = new DigitalInput(Constants.ELEVATOR_TOP_SWITCH_ID);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("elevator border", AtTop());
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed) {
    if(speed != 0) {
      elevatorMotorMaster.set(speed);
      elevatorMotorSlave.set(speed);
    }
  }

  public void stopMotors() {
    elevatorMotorMaster.stopMotor();
    elevatorMotorSlave.stopMotor();
  }

  public void setBrake(boolean on) {
    brake.set(!on);
  }

  public void setTopSwitch(boolean isAtBorder) {
    isAtBorder = topSwitch.get();
    isAtTop = isAtBorder;
  }

  public boolean AtTop() {
    setTopSwitch(isAtTop);
    return isAtTop;
  }
}
