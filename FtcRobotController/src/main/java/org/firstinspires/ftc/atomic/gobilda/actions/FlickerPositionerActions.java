package org.firstinspires.ftc.atomic.gobilda.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.utilities.ConfigConstants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlickerPositionerActions {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Servo flicker;
    public Servo positioner;

    private double flicker_position = 0.8;
    private double positioner_position = 0.8;

    // Constructor
    public FlickerPositionerActions(Telemetry opModeTelemetry, HardwareMap opModeHardware) {

        this.telemetry = opModeTelemetry;
        this.hardwareMap = opModeHardware;

        // 1. Hardware config
        flicker = hardwareMap.get(Servo.class, ConfigConstants.FLICKER);
        positioner = hardwareMap.get(Servo.class, ConfigConstants.POSITIONER);


        // 2. Set direction
        flicker.setDirection(Servo.Direction.REVERSE);
        positioner.setDirection(Servo.Direction.FORWARD);

        // 3. Set beginning position
        flicker.setPosition(flicker_position);
        positioner.setPosition(positioner_position);
    }


    public void flipper_Forward_Backward(boolean forward, boolean backward) {

        if (forward) {  //forward move arm inwards (towards the linear slide)

            flicker_position = 0.8;
            telemetry.addData("Left Hook - DOWN Position x: ", flicker_position);

        } else if (backward) {  //backward moves arm outwards (taps the skystone)

            flicker_position = 0;
            telemetry.addData("Left Hook - UP Position y: ", flicker_position);
        }

        flicker.setPosition(flicker_position);
        telemetry.update();
    }

    public void positioner_Forward_Backward(boolean forward, boolean backward) {

        if (forward) {  //forward move arm inwards (towards the linear slide)

            positioner_position = 0.8;
            telemetry.addData("Left Hook - DOWN Position x: ", positioner_position);

        } else if (backward) {  //backward moves arm outwards (taps the skystone)

            positioner_position = 0.5;
            telemetry.addData("Left Hook - UP Position y: ", positioner_position);
        }

        positioner.setPosition(positioner_position);
        telemetry.update();
    }

    public void reset_Flicker_Positioner(boolean reset) {
        if (reset == true){
            positioner_position = 0.8;
            flicker_position = 0.8;
            positioner.setPosition(positioner_position);
            flicker.setPosition(flicker_position);
        }
    }



}