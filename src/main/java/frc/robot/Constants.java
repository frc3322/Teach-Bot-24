// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kSecondaryControllerPort = 1;
  }

  public static class CANIds {
    public static final int shooterTopMotor = 1;
    public static final int shooterBottomMotor = 2;
    public static final int kRearRightDrivingCanId = 0;
    public static final int kRearRightTurningCanId = 0;
    public static final int kRearLeftTurningCanId = 0;
    public static final int kRearLeftDrivingCanId = 0;
    public static final int kFrontRightTurningCanId = 0;
    public static final int kFrontRightDrivingCanId = 0;
    public static final int kFrontLeftTurningCanId = 0;
    public static final int kFrontLeftDrivingCanId = 0;
  }

  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 5.74;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    public static final double kDirectionSlewRate = 9; // radians per second //old is 9
    public static final double kMagnitudeSlewRate = 5; // percent per second (1 = 100%) //old is 2.6
    public static final double kRotationalSlewRate = 3.0; // percent per second (1 = 100%) //0ld is 2.0

    // Chassis configuration
    // Distance between centers of right and left wheels on robots
    public static final double kTrackWidth = Units.inchesToMeters(24.375);
    // Distance between front and back wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(24.375);
    // Distance from center of robot to furthest wheel
    public static final double kWheelRadius = Units.inchesToMeters(34.5);
    
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // Works now, reverses gyro everywhere in drivetrain
    public static final boolean kGyroReversed = true;


    public static final double kDistanceThreshold = .3;
    public static final double kAngleThreshold = 15;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 5.74;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAccelerationMetersPerSecondSquaredSlow = 0.2;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 0.02;
    public static final double kIThetaController = 0.00;
    public static final double kDThetaController = 0.002;

    public static final double kPHoloTranslationController = 5; //old is 5
    public static final double kPHoloRotationController = 10;


  }

  public static final class FieldConstants {

    // Amp poses. Both halves of the field together are 651.25 in long, and amp is 4 ft 1.5 in from the wall.
    // The top wall that the amp is in is 161.625 from the center of the field. Needs to have an offset subtracted from it later
    public static final Pose2d redAmpPose = new Pose2d(
      new Translation2d(Units.inchesToMeters(325.625 - 49.5), Units.inchesToMeters(161.625)),
      new Rotation2d(-90)
    );
    
    public static final Pose2d blueAmpPose = new Pose2d(
      new Translation2d(-Units.inchesToMeters(325.625 - 49.5), Units.inchesToMeters(161.625)),
      new Rotation2d(90)
    );

    public static final Pose2d blueCenterShootPose = new Pose2d(1.33, 5.56, new Rotation2d(Math.toRadians(0)));
    public static final Pose2d redCenterShootPose = new Pose2d(15.33, 5.56, new Rotation2d(Math.toRadians(0)));

    public static final Translation2d redSpeakerTranslation = new Translation2d(
      Units.inchesToMeters(652.73),
      Units.inchesToMeters(217)
    );

    public static final Translation2d blueSpeakerTranslation = new Translation2d(
      Units.inchesToMeters(-1.50),
      Units.inchesToMeters(217)
    );

    public static final Pose2d topShootPose = new Pose2d(.82, 6.66, new Rotation2d(Math.toRadians(60)));
    public static final Pose2d centerShootPose = new Pose2d(1.33, 5.56, new Rotation2d(Math.toRadians(0)));
    public static final Pose2d bottomShootPose = new Pose2d(.82, 4.46, new Rotation2d(Math.toRadians(-60)));

    public static final Pose2d blueTopNotePose = new Pose2d(2.00, 6.50, new Rotation2d(Math.toRadians(0)));
    public static final Pose2d blueMiddleNotePose = new Pose2d(2, 5.55, new Rotation2d(Math.toRadians(0)));
    public static final Pose2d blueBottomNotePose = new Pose2d(2, 4.1, new Rotation2d(Math.toRadians(0)));
    
    public static final Pose2d redTopNotePose = new Pose2d();
    public static final Pose2d redMiddleNotePose = new Pose2d();
    public static final Pose2d redBottomNotePose = new Pose2d();
  
//centerline note poses
    public static final Pose2d centerTopPose = new Pose2d(8.29, 7.43, new Rotation2d(0));
    public static final Pose2d centerMidTopPose = new Pose2d(8.29, 5.79, new Rotation2d(0));
    public static final Pose2d centerMidPose = new Pose2d(8.29, 4.11, new Rotation2d(0));
    public static final Pose2d centerMidBottomPose = new Pose2d(8.29, 2.44, new Rotation2d(0));
    public static final Pose2d centerBottomPose = new Pose2d(8.29, .77, new Rotation2d(0));

  }


  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T, 13T, or 14T.
    // This changes the drive speed of the module (a pinion gear with more teeth will result in a
    // robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 14;

    // Invert the turning encoder, since the output shaft rotates in the opposite direction of
    // the steering motor in the MAXSwerve Module.
    public static final boolean kTurningEncoderInverted = true;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kVortexFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15 teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;

    public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction) / 60.0; // meters per second

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

    public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
    public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians
    
    public static final double kDrivingP = 0.04;
    public static final double kDrivingI = 0;
    public static final double kDrivingD = 0;
    public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
    public static final double kDrivingMinOutput = -1;
    public static final double kDrivingMaxOutput = 1;

    public static final double kTurningP = 1;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningFF = 0;
    public static final double kTurningMinOutput = -1;
    public static final double kTurningMaxOutput = 1;

    public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
    public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

    public static final int kDrivingMotorCurrentLimit = 50; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps

  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 5676;
    public static final double kVortexFreeSpeedRpm = 6784;

    public static final int neo550CurrentLimitAmps = 20;
    public static final int currentLimit = 50;
    public static final int intakeTopMotor = 3;
    public static final int intakeBottomMotor = 4;
    public static final int elevatorMotor = 3;
  }

  public static class elevatorConstants {
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;

    public static final double elevatorGearRatio = 5;
    public static final double shelf1Height = 0;
    public static final double shelf2Height = 1;
    public static final double shelf3Height = 2;
    public static final double shelf4Height = 3;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kDriveDeadband = 0.09;
    public static int kSecondaryControllerPort = 1;
  }

}
