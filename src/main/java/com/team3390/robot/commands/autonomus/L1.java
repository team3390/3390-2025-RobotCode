// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.autonomus;

import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.Manipulator3;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class L1 extends SequentialCommandGroup {
  /** Creates a new L1. */
  public L1(Drivetrain drivetrain, Manipulator3 manipulator) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelDeadlineGroup(
      new WaitCommand(2.3), 
      new AutoDrive(drivetrain)),
    new AutoShoot(manipulator)
    );
  }
}
