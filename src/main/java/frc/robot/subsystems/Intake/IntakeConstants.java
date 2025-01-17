package frc.robot.subsystems.Intake;

public class IntakeConstants {
    public class CANIds {
        public static final int kIntakeMotor = 14;
        public static final int kShiftMotor = 18;
    }

    public class IntakeSpeeds {
        public static final double kIntakeSpeed = 0.6;
        public static final double kIntakeShiftSpeed = 0.1;
        public static final double kShiftSpeed = 1;
    }

    public enum IntakeState {
        kIntake, kOuttake, kOff, kShiftRight, kShiftLeft
    }
}
