// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.team3390.lib.math.PID;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private static Limelight instance;

  private final NetworkTable networkTable;

  private final NetworkTableEntry tX; // Derece cinsinden yatay
  private final NetworkTableEntry tY; // Derece cinsinden dikey
  private final NetworkTableEntry tV; // Herhangi bir hedef var mı? (0, 1)
  private final NetworkTableEntry tA; // Hedefin kamerada ne kadar alan kapladığı

  private final PID xPID;
  private final PID yPID;
  private final PID zPID;

  public static synchronized Limelight getInstance() {
    if(instance == null) {
      instance = new Limelight();
    }
    return instance;
  }

  /** Creates a new Limelight. */
  public Limelight() {
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");

    tX = networkTable.getEntry("tx");
    tY = networkTable.getEntry("ty");
    tV = networkTable.getEntry("tv");
    tA = networkTable.getEntry("tA");

    xPID = new PID(0.01, 0, 0.00007, 0.05, 0.35, -0.35);
    yPID = new PID(0.05, 0.002, 0.00007, 0, 1, -1);
    zPID = new PID(0.05, 0.002, 0.00007, 0.05, 1, -1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public NetworkTableEntry getValue(String key) {
    return networkTable.getEntry(key);
  }

  public boolean hasTarget() {
    return tV.getDouble(0) == 1;
  }

  public boolean isXAtSetpoint() {
    return xPID.atSetpoint();
  }

  public boolean isYAtSetpoint() {
    return yPID.atSetpoint();
  }
  
  public boolean isZAtSetpoint() {
    return zPID.atSetpoint();
  }

  public double getXOutput() {
    if (this.hasTarget() && !xPID.atSetpoint()) {
      double x = tX.getDouble(0);
      return xPID.output(xPID.calculate(x, 0));
    }
    return 0;
  }

  public double getYOutput() {
    if (this.hasTarget() && !yPID.atSetpoint()) {
      double y = tY.getDouble(0);
      return yPID.output(yPID.calculate(y, 0));
    }
    return 0;
  }

  public double getZOutput() {
    if(this.hasTarget() && !zPID.atSetpoint()) {
      double z = 100 - tA.getDouble(0);
      return zPID.output(zPID.calculate(z, 5));
    }
    return 0;
  }
}
