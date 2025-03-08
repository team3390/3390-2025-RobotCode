// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.studica.frc.AHRS;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private static Drivetrain instance;

  private boolean isBreakMode = false;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX leftMaster, rightMaster, leftSlave, rightSlave;
  private final AHRS navX;
  private final DifferentialDrive driveController;


  public synchronized static Drivetrain getInstance() {
    if (instance == null) {
      instance = new Drivetrain();
    }
    return instance;
  }


  /** Creates a new Drivetrain. */
  public Drivetrain() {

    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    leftMaster = TalonSRXCreator.createTalon(Constants.DRIVE_LEFT_MASTER_ID, talonConfiguration);
    leftSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.DRIVE_LEFT_SLAVE_ID, Constants.DRIVE_LEFT_MASTER_ID, talonConfiguration);
    rightMaster = TalonSRXCreator.createTalon(Constants.DRIVE_RIGHT_MASTER_ID, talonConfiguration);
    rightSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.DRIVE_RIGHT_SLAVE_ID, Constants.DRIVE_RIGHT_MASTER_ID, talonConfiguration);

    leftMaster.setInverted(Constants.DRIVE_LEFT_INVERTED);
    leftSlave.setInverted(Constants.DRIVE_LEFT_INVERTED);
    rightMaster.setInverted(Constants.DRIVE_RIGHT_INVERTED);
    rightSlave.setInverted(Constants.DRIVE_RIGHT_INVERTED);

    navX = new AHRS(Constants.SENSOR_NAVX_PORT);


    driveController = new DifferentialDrive(leftMaster, rightMaster);

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("heading", getHeading());
    // This method will be called once per scheduler run
  }

  public void stopMotors() {
    driveController.stopMotor();
  }

  public boolean isBreakMode() {
    return isBreakMode;
  }

  public void setBrakeMode(boolean shouldEnable) {
    if (isBreakMode != shouldEnable) {
      isBreakMode = shouldEnable;
      NeutralMode mode = shouldEnable ? NeutralMode.Brake : NeutralMode.Coast;

      leftMaster.setNeutralMode(mode);
      leftSlave.setNeutralMode(mode);

      rightMaster.setNeutralMode(mode);
      rightSlave.setNeutralMode(mode);
    }
  }

  public void drive(double leftSpeed, double rightSpeed){
    driveController.tankDrive(leftSpeed, rightSpeed);
  }

  public double getHeading() {
    return navX.getAngle();
  }

  public boolean isAtAngle(double angle) {
    if (angle == getHeading()) {
      return true;
    }
    else {
      return false;
    }
  }

}
