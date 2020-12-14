package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.utilities.ConfigConstants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleGripperActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Servo armServo;
    public Servo gripper;

    private double arm_position = 0.0;
    private double gripper_position = 1.0;

    public WobbleGripperActions(Telemetry opModeTelemetry, HardwareMap opModeHardware) {

        this.telemetry = opModeTelemetry;
        this.hardwareMap = opModeHardware;

        // 1. Hardware config
        armServo = hardwareMap.get(Servo.class, ConfigConstants.ARM_SERVO);
        gripper = hardwareMap.get(Servo.class, ConfigConstants.GRIPPER_SERVO);

        // 2. Set direction
        armServo.setDirection(Servo.Direction.REVERSE);
        gripper.setDirection(Servo.Direction.REVERSE);

        // 3. Set beginning position
        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
    }

    public void armUpDown(boolean leftPadPressed, boolean rightPadPressed) {

        if (leftPadPressed) {  //down
            arm_position = 0;
            telemetry.addData("Arm - DOWN Position x: ", armServo);

        } else if (rightPadPressed) {  //up
            arm_position = 1.0;
            telemetry.addData("Arm - UP Position y: ", arm_position);
        }

        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        telemetry.update();
    }
    public void gripperOpenClose(boolean leftPadPressed, boolean rightPadPressed) {

        if (leftPadPressed) {  //down
            gripper_position = 0;
            telemetry.addData("Gripper - Closed Position x: ", gripper_position);

        } else if (rightPadPressed) {  //up
            gripper_position = 1.0;
            telemetry.addData("Gripper - Open Position y: ", gripper_position);
        }

        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        telemetry.update();
    }
    public void moveArmDown() {

        armUpDown(true, false);
    }
    public void moveArmUp() {

        armUpDown(false, true);
    }

    public void closeGripper() {

        armUpDown(true, false);
    }
    public void openGripper() {

        armUpDown(false, true);
    }

}
