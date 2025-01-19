// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private static Arm instance;

  private boolean isBreakMode = false;
  private final Configuration talonConfiguration = new Configuration();
  private final LazyTalonSRX armMotorMaster, armMotorSlave;

  public synchronized static Arm getInstance(){
    if(instance == null){
      instance = new Arm();
    }
    return instance;
  }

  /** Creates a new Arm. */
  public Arm() {
    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    armMotorMaster = TalonSRXCreator.createTalon(Constants.ARM_MOTOR_MASTER_ID, talonConfiguration);
    armMotorSlave = TalonSRXCreator.createTalon(Constants.ARM_MOTOR_SLAVE_ID, talonConfiguration);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
