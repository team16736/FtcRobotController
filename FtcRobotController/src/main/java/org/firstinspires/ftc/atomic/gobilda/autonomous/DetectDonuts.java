package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.HookActions;
import org.firstinspires.ftc.atomic.gobilda.actions.WobbleGripperActions;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


/**
 * Purpose: Pull RED foundation to the building site
 */
@Autonomous(name = "Detect Donuts", group = "GoBilda")
public class DetectDonuts extends HelperAction {

    DistanceSensor sensorDistance;
    @Override
    public void runOpMode() {

        DriveWheelActions driveActions = new DriveWheelActions(telemetry, hardwareMap);
        WobbleGripperActions wobbleGripperActions = new WobbleGripperActions(telemetry, hardwareMap);

        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        driveActions.applySensorSpeed = true;

        wobbleGripperActions.closeGripper();
        wobbleGripperActions.moveArmUp();
        sleep(1000);

        //Step 2: Drive REVERSE towards the building zone
        drive_ReverseAndStop(driveActions, SPEED, 1);
        drive_ReverseAndStop(driveActions, 0.25, .7);
        sleep(1000);

        int height = detectHeight();
         telemetry.update();
        sleep(500);
        drive_ForwardAndStop(driveActions, SPEED, .05);
        moveBot(height, driveActions);


        driveActions.applySensorSpeed = false;// we have altered the speed for the forwards movement

    }
    public int detectHeight(){
        DriveWheelActions driveActions = new DriveWheelActions(telemetry, hardwareMap);
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        telemetry.addData("Distance (cm)",
                String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));

        double distance = sensorDistance.getDistance(DistanceUnit.CM);
        if (distance < 2){
            telemetry.addData("4 donuts", 4);
            return 4;
        } else if (distance > 4.5){
            telemetry.addData("0 donuts", 0);
            return 0;
        } else {
            telemetry.addData("1 donut", 1);
            return 1;
        }


    }
    public void moveBot(int height, DriveWheelActions driveActions){

        strafe_LeftAndStop(driveActions, 0.4, 1.75);
        if (height == 0){
            drive_ReverseAndStop(driveActions, SPEED, 0.8);
        }
        if (height == 1){
            drive_ReverseAndStop(driveActions, SPEED, 2.3);
            strafe_RightAndStop(driveActions, 0.4, 1.5);
        }
        if (height == 4){
            drive_ReverseAndStop(driveActions, SPEED, 3.3);
        }
    }


}

