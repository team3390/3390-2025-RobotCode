package com.team3390.lib.control;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DriveController {
  
  private final double mMotorDeadband = 0.02;
  private final double mMotorMaxOut = 1.0;

  private final MotorController leftMaster;
  private final MotorController rightMaster;

  public DriveController(MotorController leftMaster, MotorController rightMaster) {
    this.leftMaster = leftMaster;
    this.rightMaster = rightMaster;
  }

  public void stopMotors() {
    leftMaster.stopMotor();
    rightMaster.stopMotor();
  }

  public void arcadeDrive(double fwd, double rot, boolean squareInputs) {
    fwd = MathUtil.applyDeadband(fwd, mMotorDeadband);
    rot = MathUtil.applyDeadband(rot, mMotorDeadband);
    fwd = MathUtil.clamp(fwd, -1.0, -1.0);
    rot = MathUtil.clamp(rot, -1.0, -1.0);

    if (squareInputs) {
      fwd = Math.copySign(fwd * fwd, fwd);
      rot = Math.copySign(rot * rot, rot);
    }

    double leftSpeed = fwd - rot;
    double rightSpeed = fwd + rot;

    double greaterInput = Math.max(Math.abs(fwd), Math.abs(rot));
    double lesserInput = Math.min(Math.abs(fwd), Math.abs(rot));
    if (greaterInput == 0.0) {
      leftMaster.set(0.0 * mMotorMaxOut);
      rightMaster.set(0.0 * mMotorMaxOut);
    }
    double saturatedInput = (greaterInput + lesserInput) / greaterInput;
    leftSpeed /= saturatedInput;
    rightSpeed /= saturatedInput;

    leftMaster.set(leftSpeed * mMotorMaxOut);
    rightMaster.set(rightSpeed * mMotorMaxOut);
  }

  public void tankDrive(double left, double right, boolean squareInputs) {
    left = MathUtil.applyDeadband(left, mMotorDeadband);
    right = MathUtil.applyDeadband(right, mMotorDeadband);
    left = MathUtil.clamp(left, -1.0, -1.0);
    right = MathUtil.clamp(right, -1.0, -1.0);

    if (squareInputs) {
      left = Math.copySign(left * left, left);
      right = Math.copySign(right * right, right);
    }

    leftMaster.set(left * mMotorMaxOut);
    rightMaster.set(right * mMotorMaxOut);
  }

}
