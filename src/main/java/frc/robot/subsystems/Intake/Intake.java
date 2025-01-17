// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Intake.IntakeConstants.IntakeState;

public class Intake extends SubsystemBase {
  public IntakeState intakeState = IntakeState.kOff;

  private final CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.CANIds.kIntakeMotor, MotorType.kBrushless);
  private final CANSparkMax shiftMotor = new CANSparkMax(IntakeConstants.CANIds.kShiftMotor, MotorType.kBrushless);

  /** Creates a new Intake. */
  public Intake() {
    intakeMotor.restoreFactoryDefaults();
    shiftMotor.restoreFactoryDefaults();

    // intakeMotor.setSmartCurrentLimit(40);
    // shiftMotor.setSmartCurrentLimit(40);

    intakeMotor.setIdleMode(CANSparkBase.IdleMode.kBrake);
    shiftMotor.setIdleMode(CANSparkBase.IdleMode.kBrake);

    intakeMotor.burnFlash();
    shiftMotor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    switch (intakeState) {
      case kIntake:
        intakeMotor.set(IntakeConstants.IntakeSpeeds.kIntakeSpeed);
        shiftMotor.set(0);
        break;
      case kOuttake:
        intakeMotor.set(-IntakeConstants.IntakeSpeeds.kIntakeSpeed);
        shiftMotor.set(0);
        break;
      case kOff:
        intakeMotor.set(0);
        shiftMotor.set(0);
        break;
      case kShiftRight:
        intakeMotor.set(IntakeConstants.IntakeSpeeds.kIntakeSpeed);
        shiftMotor.set(IntakeConstants.IntakeSpeeds.kShiftSpeed);
        break;
      case kShiftLeft:
        intakeMotor.set(IntakeConstants.IntakeSpeeds.kIntakeSpeed);
        shiftMotor.set(-IntakeConstants.IntakeSpeeds.kShiftSpeed);
        break;
    }
  }

  public Command setStateCommand(IntakeState state) {
    return new InstantCommand(
      () -> intakeState = state, this
    );
  }
}
