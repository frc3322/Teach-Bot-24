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
  CANSparkMax topMotor2 = new CANSparkMax(CANIds.intakeTopMotor2, MotorType.kBrushless);
  CANSparkMax bottomMotor2 = new CANSparkMax(CANIds.intakeBottomMotor2, MotorType.kBrushless);

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

 // Remove void once we have CANIDS or code wont work
  public void Intake2() {
    topMotor2.restoreFactoryDefaults();
    bottomMotor2.restoreFactoryDefaults();
    
    topMotor2.setIdleMode(IdleMode.kCoast);
    bottomMotor2.setIdleMode(IdleMode.kCoast);
    
    topMotor2.setInverted(true);
    bottomMotor2.follow(topMotor2, true);

    topMotor2.burnFlash();
    bottomMotor2.burnFlash();
  }

  private void setIntakeSpeed2(double speed) {
    topMotor2.set(0);
  }

  private void stopIntakeMotor2() {
    topMotor2.set(0);
  }

  public Command setIntake2Command(double speed) {
    return new RunCommand(()-> setIntakeSpeed2(speed), this);
  }

  public Command stopIntake2Command() {
    return new RunCommand(()-> stopIntakeMotor2(), this);
  }
    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}