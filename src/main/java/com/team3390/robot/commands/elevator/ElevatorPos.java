// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.elevator;

import com.team3390.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ElevatorPos extends Command {

  private final Elevator elevator;
  private final double pos;
  /** Creates a new ElevatorPos. */
  public ElevatorPos(Elevator elevator, double pos) {
    this.elevator = elevator;
    this.pos = pos;
    addRequirements(elevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(elevator.getEncoderAngle() != pos) {
      elevator.setElevatorPos(elevator.getEncoderAngle(), pos);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevator.atLowBorder() || elevator.atHighBorder() || elevator.atSetpoint();
  }
}
