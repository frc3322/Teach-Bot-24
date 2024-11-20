// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CANIds;


public class Shooter extends SubsystemBase {

  CANSparkFlex topMotor = new CANSparkFlex(CANIds.shooterTopMotor, MotorType.kBrushless);
  CANSparkFlex bottomMotor = new CANSparkFlex(CANIds.shooterBottomMotor, MotorType.kBrushless);

  /** Creates a new Shooter. */
  public Shooter() {
    topMotor.restoreFactoryDefaults();
    bottomMotor.restoreFactoryDefaults();
    
    topMotor.setIdleMode(IdleMode.kCoast);
    bottomMotor.setIdleMode(IdleMode.kCoast);
    
    topMotor.burnFlash();
    bottomMotor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
