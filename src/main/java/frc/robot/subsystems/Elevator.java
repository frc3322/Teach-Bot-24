// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.beans.Encoder;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIds;
import frc.robot.Constants.DriveConstants.ElevatorConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;

public class Elevator extends SubsystemBase implements Loggable{

  private double setpoint = 0;

  CANSparkMax ElevatorMotor = new CANSparkMax(CANIds.elevatorMotor, MotorType.kBrushless);
  ProfiledPIDController elevatorPID = new ProfiledPIDController(
    ElevatorConstants.elevatorP,
    ElevatorConstants.elevatorI,
    ElevatorConstants.elevatorD,
    new TrapezoidProfile.Constraints(.1, .1));
  RelativeEncoder elevatorEncoder = ElevatorMotor.getEncoder();

/** Creates a new Elevator. */
  public Elevator() {
    ElevatorMotor.restoreFactoryDefaults();

    ElevatorMotor.setIdleMode(IdleMode.kBrake);

    ElevatorMotor.setSmartCurrentLimit(80);

    elevatorEncoder.setPositionConversionFactor(ElevatorConstants.elevatorGearRatio);

    ElevatorMotor.burnFlash();
    
  }

  public double boundedOutput(double input) {
    if(getEncoderPos() - this.setpoint < ElevatorConstants.FreemoveRestraints && input > 0){
      return 0;
    };
    if(getEncoderPos()  - this.setpoint > -ElevatorConstants.FreemoveRestraints && input < 0){
      return 0;
    };
    return input;
  } 

  public void setElevatorPower(double power) {
    ElevatorMotor.set(power);
  }

  public void stopMotor() {
    ElevatorMotor.stopMotor();
  }

@Config
  public void setElevatorGoal(double setpoint) {
    elevatorPID.setGoal(setpoint);
  }

  public double getEncoderPos() {
    return elevatorEncoder.getPosition();
  }

  public Command goToSetpoint() {
    return new RunCommand(
      () -> {
        ElevatorMotor.set(elevatorPID.calculate(getEncoderPos()));
      }, 
      this
    );
  }

  public Command goToShelf1Command() {
    return new SequentialCommandGroup(
      new InstantCommand( 
        () -> setElevatorGoal(ElevatorConstants.shelf1Height)
      ),
      goToSetpoint()
    );
  }

  public Command goToShelf2Command() {
    return new SequentialCommandGroup(
      new InstantCommand( 
        () -> setElevatorGoal(ElevatorConstants.shelf2Height)
      ),
      goToSetpoint()
    );
  }


  public Command FreemoveRestraints(double power) {
    return new RunCommand(
    () -> setElevatorPower(boundedOutput(power))
    );
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
