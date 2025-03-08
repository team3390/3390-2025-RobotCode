/**
 * @author Doruk Karakoç
 * @since 05.01.25
 */

package com.team3390.robot;

import com.team3390.robot.commands.drive.TankDrive;
import com.team3390.robot.commands.drive.TurnToAngle;
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

    private final Command driveTo60Deg = new TurnToAngle(driveSubsystem, 60);

    private final Command elevatorUp = new ElevatorUp(elevatorSubsystem);
    private final Command elevatorDown = new ElevatorDown(elevatorSubsystem);
    private final Command elevatorPosL1 = new ElevatorPos(elevatorSubsystem, Constants.ELEVATOR_L1_POS);
    private final Command manipulatorPosL1 = new ManipulatorPos(manipulatorSubsystem, Constants.MANIPULATOR_L1_POS);
    private final ParallelCommandGroup autoL1Pos = new ParallelCommandGroup(elevatorPosL1, manipulatorPosL1);

    public RobotContainer(){
        driveSubsystem.setDefaultCommand(new TankDrive(driveSubsystem,
        () -> leftStick.getY(),
        () -> rightStick.getY()));

        new Trigger(() -> gamepad.getRawButton(7)).whileTrue(elevatorUp);
        new Trigger(() -> gamepad.getRawButton(8)).whileTrue(elevatorDown);
        new Trigger(() -> gamepad.getRawButton(2)).whileTrue(autoL1Pos);
        new Trigger(() -> leftStick.getRawButton(1)).onTrue(driveTo60Deg);

    }

    public Command getAutonomousCommand() {
        throw new UnsupportedOperationException("Unimplemented method 'getAutonomousCommand'");
    }

}
