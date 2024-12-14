// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.Constants.CANIds;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.DriveConstants.AutoConstants;
import frc.robot.Constants.DriveConstants.OIConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import java.util.concurrent.Callable;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class RobotContainer {
  // Subsystem Declarations
  private final Shooter m_shooter = new Shooter();
  private final Elevator m_elevator = new Elevator();
  private final Intake m_intakeleft = new Intake(CANIds.intakeTopMotorLeft, CANIds.intakeBottomMotorLeft);
  private final Intake m_intakeright = new Intake(CANIds.intakeTopMotorRight, CANIds.intakeBottomMotorRight);
  private final DriveTrain m_drivetrain = new DriveTrain();
   
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
    autoSelector.addOption("driveForward", () -> m_drivetrain.driveForwardCommand());

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


    m_elevator.setDefaultCommand(
    //The left joystick on the secondary controller controls the vertical position of the elevator (manually).
    new RunCommand(
      () -> m_elevator.setElevatorPower(
        -MathUtil.applyDeadband(m_secondaryController.getLeftY() / 10, OIConstants.kElevatorDeadband)
      ),
      m_elevator ));
    
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


    //Shooter
    m_secondaryController.leftTrigger()
    .whileTrue(m_shooter.shootCommand(.5, .5))
    .whileFalse(m_shooter.shootCommand(0, 0));

    m_secondaryController.rightBumper()
    .whileTrue(m_intakeright.setIntakeCommand(0.5))
    .whileFalse(m_intakeright.stopIntakeCommand());

    m_secondaryController.leftBumper()
    .whileTrue(m_intakeleft.setIntakeCommand(0.5))
    .whileFalse(m_intakeleft.stopIntakeCommand());
    //Sets the elevator height, the heights are numbered from bottom to top (bottom shelf is shelf 1)
    m_secondaryController.a().onTrue(m_elevator.goToShelf1Command());
    m_secondaryController.b().onTrue(m_elevator.goToShelf2Command());

    m_secondaryController.x().onTrue(m_shooter.servosStartEndCommand());
    
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