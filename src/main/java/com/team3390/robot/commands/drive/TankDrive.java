// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.drive;

import java.util.function.Supplier;

import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TankDrive extends Command {

  private final Drivetrain drivetrain;
  private final Supplier<Double> left, right;

  public TankDrive(Drivetrain drivetrain, Supplier<Double> left, Supplier<Double> right) {

    this.drivetrain = drivetrain;
    this.left = left;
    this.right = right;
    addRequirements(drivetrain);

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drivetrain.drive(left.get(), right.get());
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
