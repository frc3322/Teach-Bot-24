// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIds;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  CANSparkMax topMotor = new CANSparkMax(CANIds.intakeTopMotor, MotorType.kBrushless);
  CANSparkMax bottomMotor = new CANSparkMax(CANIds.intakeBottomMotor, MotorType.kBrushless);
  CANSparkMax topMotor2 = new CANSparkMax(CANIds.intakeTopMotorLeft, MotorType.kBrushless);
  CANSparkMax bottomMotor2 = new CANSparkMax(CANIds.intakeBottomMotorLeft, MotorType.kBrushless);

  public Intake() {
    topMotor.restoreFactoryDefaults();
    bottomMotor.restoreFactoryDefaults();
    
    topMotor.setIdleMode(IdleMode.kCoast);
    bottomMotor.setIdleMode(IdleMode.kCoast);
    
    topMotor.setInverted(true);
    bottomMotor.follow(topMotor, true);

    topMotor.burnFlash();
    bottomMotor.burnFlash();
  }

  private void setIntakeSpeed(double speed) {
    topMotor.set(speed);
  }

  private void stopIntakeMotor() {
    topMotor.set(0);
  }

  public Command setIntakeCommand(double speed) {
    return new RunCommand(()-> setIntakeSpeed(speed), this);
  }

  public Command stopIntakeCommand() {
    return new RunCommand(()-> stopIntakeMotor(), this);
  }

  private void setIntakeSpeedLeft(double speed) {
    topMotor2.set(0);
  }

  private void stopIntakeMotorLeft() {
    topMotor2.set(0);
  }

  public Command setIntakeLeft2Command(double speed) {
    return new RunCommand(()-> setIntakeSpeedLeft(speed), this);
  }

  public Command stopIntakeLeftCommand() {
    return new RunCommand(()-> stopIntakeMotorLeft(), this);
  }
    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}