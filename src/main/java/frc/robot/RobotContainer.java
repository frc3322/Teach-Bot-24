// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.DriveConstants.OIConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake.Intake;
import frc.robot.subsystems.Intake.IntakeConstants.IntakeState;

import java.util.concurrent.Callable;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class RobotContainer {
  // Subsystem Declarations
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Intake m_intake = new Intake();
   
  SendableChooser<Callable<Command>> autoSelector = new SendableChooser<Callable<Command>>();
  // Driver Controller declaration
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandXboxController m_secondaryController =
      new CommandXboxController(OperatorConstants.kSecondaryControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    autoSelector.addOption("No auto", null);
    autoSelector.addOption("driveForward", ()->  m_drivetrain.driveForwardCommand());

    m_drivetrain.setDefaultCommand(
    // The left stick controls translation of the robot.
    // Turning is controlled by the X axis of the right stick.
    new RunCommand(
        () -> m_drivetrain.drive(
            -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
            -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
            -MathUtil.applyDeadband(m_driverController.getRightX() / 1.2, OIConstants.kDriveDeadband),
            true, true),
        m_drivetrain));
  }


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    m_driverController.start().onTrue(new InstantCommand(()->m_drivetrain.zeroHeading()));
    
    m_driverController.rightTrigger().onTrue(m_intake.setStateCommand(IntakeState.kIntake));
    m_driverController.rightTrigger().onFalse(m_intake.setStateCommand(IntakeState.kOff));

    m_driverController.leftTrigger().onTrue(m_intake.setStateCommand(IntakeState.kOuttake));
    m_driverController.leftTrigger().onFalse(m_intake.setStateCommand(IntakeState.kOff));

    m_driverController.leftBumper().onTrue(m_intake.setStateCommand(IntakeState.kShiftLeft));
    m_driverController.leftBumper().onFalse(m_intake.setStateCommand(IntakeState.kOff));

    m_driverController.rightBumper().onTrue(m_intake.setStateCommand(IntakeState.kShiftRight));
    m_driverController.rightBumper().onFalse(m_intake.setStateCommand(IntakeState.kOff));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}