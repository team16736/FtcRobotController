package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.utilities.ConfigConstants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WobbleGripperActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Servo armServo;
    public Servo gripper;
    public Servo distanceSensorServo;
    public DistanceSensor distanceSensor;
    private double arm_position = 0.0;
    private double gripper_position = 1.0;
    private double distanceSensor_position = 0;

    public WobbleGripperActions(Telemetry opModeTelemetry, HardwareMap opModeHardware) {

        this.telemetry = opModeTelemetry;
        this.hardwareMap = opModeHardware;

        // 1. Hardware config
        armServo = hardwareMap.get(Servo.class, ConfigConstants.ARM_SERVO);
        gripper = hardwareMap.get(Servo.class, ConfigConstants.GRIPPER_SERVO);
        distanceSensorServo = hardwareMap.get(Servo.class, ConfigConstants.DISTANCE_SERVO);
        distanceSensor = hardwareMap.get(DistanceSensor.class, ConfigConstants.DONUT_COUNTER);


        // 2. Set direction
        armServo.setDirection(Servo.Direction.REVERSE);
        gripper.setDirection(Servo.Direction.REVERSE);
        distanceSensorServo.setDirection(Servo.Direction.REVERSE);

        // 3. Set beginning position
        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        distanceSensorServo.setPosition(distanceSensor_position);
    }

    public void armUpDown(boolean leftPadPressed, boolean rightPadPressed) {

        if (leftPadPressed) {  //down
            arm_position = 0.0;
            telemetry.addData("Arm - DOWN Position x: ", armServo);

        } else if (rightPadPressed) {  //up
            arm_position = 1.0;
            telemetry.addData("Arm - UP Position y: ", arm_position);
        }

        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        distanceSensorServo.setPosition(distanceSensor_position);
        telemetry.update();
    }
    public void gripperOpenClose(boolean leftPadPressed, boolean rightPadPressed) {

        if (leftPadPressed) {  //down
            gripper_position = 1.0;
            telemetry.addData("Gripper - Closed Position x: ", gripper_position);

        } else if (rightPadPressed) {  //up
            gripper_position = 0;
            telemetry.addData("Gripper - Open Position y: ", gripper_position);
        }

        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        distanceSensorServo.setPosition(distanceSensor_position);
        telemetry.update();
    }
    public void moveArmDown() {

        armUpDown(true, false);
    }
    public void moveArmUp() {

        armUpDown(false, true);
    }

    public void closeGripper() {

        gripperOpenClose(true, false);
    }
    public void openGripper() {

        gripperOpenClose(false, true);
    }
    public void moveDistanceSensorDown() {

        distanceSensor_position = 1.0;
        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        distanceSensorServo.setPosition(distanceSensor_position);
        telemetry.update();
    }
    public void moveDistanceSensorUp() {

        distanceSensor_position = 0.0;
        armServo.setPosition(arm_position);
        gripper.setPosition(gripper_position);
        distanceSensorServo.setPosition(distanceSensor_position);
        telemetry.update();
    }
}
