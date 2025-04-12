/**
 * @author Doruk Karakoç
 * @since 05.01.25
 */

package com.team3390.robot;

import com.team3390.robot.commands.autonomus.L1;
import com.team3390.robot.commands.autonomus.Taxi;
import com.team3390.robot.commands.drive.TankDrive;
import com.team3390.robot.commands.elevator.ElevatorUp;
import com.team3390.robot.commands.elevator.BreakToggle;
import com.team3390.robot.commands.elevator.ElevatorDown;
import com.team3390.robot.commands.manipulator.Intake;
import com.team3390.robot.commands.manipulator.ManipulatorAnkleControl;
import com.team3390.robot.commands.manipulator.ManipulatorAnkleDown;
import com.team3390.robot.commands.manipulator.ManipulatorPivotControl;
import com.team3390.robot.commands.manipulator.Shoot;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.Elevator;
import com.team3390.robot.subsystems.Manipulator;
import com.team3390.robot.subsystems.Manipulator2;
import com.team3390.robot.subsystems.Manipulator3;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

    private final Drivetrain driveSubsystem = Drivetrain.getInstance();
    private final Elevator elevatorSubsystem = Elevator.getInstance();
    private final Manipulator manipulatorSubsystem = Manipulator.getInstance();
    private final Manipulator2 manipulator2Subsystem = Manipulator2.getInstance();
    private final Manipulator3 manipulator3Subsystem = Manipulator3.getInstance();
    
    private final Joystick gamepad2 = new Joystick(Constants.JOYSTICK_GAMEPAD2_PORT);
    private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

    private final Command elevatorUp = new ElevatorUp(elevatorSubsystem);
    private final Command elevatorDown = new ElevatorDown(elevatorSubsystem);
    private final Command manipulatorAnkleUp = new ManipulatorAnkleControl(manipulator2Subsystem);
    private final Command manipulatorAnkleDown = new ManipulatorAnkleDown(manipulator2Subsystem);
    private final Command intake = new Intake(manipulator3Subsystem);
    private final Command shoot = new Shoot(manipulator3Subsystem);
    private final Command breakOn = new BreakToggle(elevatorSubsystem, false);
    private final Command breakOff = new BreakToggle(elevatorSubsystem, true);

      private final Compressor comp = new Compressor(PneumaticsModuleType.REVPH);

    private final SendableChooser<Integer> autoModeChooser = new SendableChooser<>();
    private Command selectedAuto;

    public RobotContainer(){

        comp.enableDigital();

        driveSubsystem.setDefaultCommand(new TankDrive(driveSubsystem,
            () -> gamepad2.getRawAxis(1),
            () -> gamepad2.getRawAxis(3)
        ));

        manipulatorSubsystem.setDefaultCommand(new ManipulatorPivotControl(manipulatorSubsystem,
            () -> gamepad.getRawAxis(1)/2
        ));
        
        new Trigger(() -> gamepad.getRawButton(7)).whileTrue(elevatorUp);
        new Trigger(() -> gamepad.getRawButton(8)).whileTrue(elevatorDown);
        new Trigger(() -> gamepad.getRawButton(5)).whileTrue(intake);
        new Trigger(() -> gamepad.getRawButton(6)).whileTrue(shoot);
        new Trigger(() -> gamepad.getRawButton(2)).whileTrue(manipulatorAnkleUp);
        new Trigger(() -> gamepad.getRawButton(3)).whileTrue(manipulatorAnkleDown);
        new Trigger(() -> gamepad2.getRawButton(7)).whileTrue(breakOff);
        new Trigger(() -> gamepad2.getRawButton(8)).whileTrue(breakOn);

        autoModeChooser.addOption("Nothing", 1);
        autoModeChooser.addOption("L1", 2);
        autoModeChooser.addOption("Taxi", 3);

        SmartDashboard.putData("Auto Mode", autoModeChooser);

    }

    public Command getAutonomousCommand() {
        int sellected = autoModeChooser.getSelected();
        switch (sellected) {
          case 1:
            selectedAuto = null;
            break;
    
          case 2:
            selectedAuto = new L1(driveSubsystem, manipulator3Subsystem);
            break;
    
          case 3:
            selectedAuto = new Taxi(driveSubsystem);
            break;
          default:
            selectedAuto = new L1(driveSubsystem, manipulator3Subsystem);
        }    
        return selectedAuto;
      }
    }


