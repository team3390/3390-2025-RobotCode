// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.manipulator;

import com.team3390.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManipulatorPos extends Command {

  private final Manipulator manipulator;
  private final double pos;

  /** Creates a new ManipulatorPos. */
  public ManipulatorPos(Manipulator manipulator, double pos) {
    this.manipulator = manipulator;
    this.pos = pos;
    addRequirements(manipulator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(manipulator.encoderAngle() != pos) {
      manipulator.setManipulatorPos(manipulator.encoderAngle(), pos);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    manipulator.stopPivotMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
