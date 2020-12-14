package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.atomic.gobilda.utilities.ConfigConstants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Purpose:
 * 1. Intake collects ring
 * 2. shooter shoots the ring
 * 3.
 */
public class IntakeShooterActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor IntakeMotor =  null;
    private DcMotor ShooterServo = null;


    private DigitalChannel limit_switch;
    DigitalChannel digitalTouch;


    public IntakeShooterActions(Telemetry opModeTelemetry, HardwareMap opModeHardware) {

        this.telemetry = opModeTelemetry;
        this.hardwareMap = opModeHardware;

        // 1. Hardware config
        IntakeMotor = hardwareMap.get(DcMotor.class, ConfigConstants.INTAKE);
        ShooterServo = hardwareMap.get(DcMotor.class, ConfigConstants.SHOOTER);

        // 2. Set direction
        IntakeMotor.setDirection(DcMotor.Direction.REVERSE);
        ShooterServo.setDirection(DcMotor.Direction.REVERSE);
    }

    public void IntakeRing() {

        double intakePower = 0;

    }
}
