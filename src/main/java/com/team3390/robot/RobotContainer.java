/**
 * @author Doruk Karakoç
 * @since 05.01.25
 */

package com.team3390.robot;

import com.team3390.robot.commands.drive.TankDrive;
import com.team3390.robot.commands.elevator.ElevatorPos;
import com.team3390.robot.commands.elevator.ElevatorUp;
import com.team3390.robot.commands.elevator.ElevatorDown;
import com.team3390.robot.commands.manipulator.ManipulatorPos;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.Elevator;
import com.team3390.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

    private final Drivetrain driveSubsystem = Drivetrain.getInstance();
    private final Elevator elevatorSubsystem = Elevator.getInstance();
    private final Manipulator manipulatorSubsystem = Manipulator.getInstance();
    
    private final Joystick leftStick = new Joystick(Constants.JOYSTICK_LEFT_PORT);
    private final Joystick rightStick = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
    private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

    private final Command ElevatorUp = new ElevatorUp(elevatorSubsystem);
    private final Command ElevatorDown = new ElevatorDown(elevatorSubsystem);
    private final Command ElevatorPosL1 = new ElevatorPos(elevatorSubsystem, Constants.ELEVATOR_L1_POS);
    private final Command ManipulatorPosL1 = new ManipulatorPos(manipulatorSubsystem, Constants.MANIPULATOR_L1_POS);
    private final ParallelCommandGroup autoL1Pos = new ParallelCommandGroup(ElevatorPosL1, ManipulatorPosL1);

    public RobotContainer(){
        driveSubsystem.setDefaultCommand(new TankDrive(driveSubsystem,
        () -> leftStick.getY(),
        () -> rightStick.getY()));

        new Trigger(() -> gamepad.getRawButton(0)).whileTrue(ElevatorUp);
        new Trigger(() -> gamepad.getRawButton(1)).whileTrue(ElevatorDown);
        new Trigger(() -> gamepad.getRawButton(2)).whileTrue(autoL1Pos);

    }

    public Command getAutonomousCommand() {
        throw new UnsupportedOperationException("Unimplemented method 'getAutonomousCommand'");
    }

}
