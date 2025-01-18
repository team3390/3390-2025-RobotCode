/**
 * @author Doruk Karakoç
 * @since 05.01.25
 */

package com.team3390.robot;

import com.team3390.robot.commands.drive.TankDrive;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

    private final Drivetrain driveSubsystem = Drivetrain.getInstance();
    private final Joystick leftStick = new Joystick(Constants.JOYSTICK_LEFT_PORT);
    private final Joystick rightStick = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
    private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

    public RobotContainer(){
        driveSubsystem.setDefaultCommand(new TankDrive(driveSubsystem,
        () -> leftStick.getY(),
        () -> rightStick.getY()));
    }

    public Command getAutonomousCommand() {
        throw new UnsupportedOperationException("Unimplemented method 'getAutonomousCommand'");
    }

}
