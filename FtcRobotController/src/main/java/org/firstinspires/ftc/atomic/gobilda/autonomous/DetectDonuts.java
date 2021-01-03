package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.HookActions;
import org.firstinspires.ftc.atomic.gobilda.actions.WobbleGripperActions;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


/**
 * Starts with robot facing away from the wall and touching the wall. Put the right side of the frame just to the left of the foam piece farthest to the right(not counting the tabs on the end of the piece. The right wheels should be on the starting line.
 */
@Autonomous(name = "Detect Donuts", group = "GoBilda")
public class DetectDonuts extends HelperAction {

    DistanceSensor sensorDistance;
    @Override
        public void runOpMode() {

            DriveWheelActions driveActions = new DriveWheelActions(telemetry, hardwareMap);
            WobbleGripperActions wobbleGripperActions = new WobbleGripperActions(telemetry, hardwareMap);

            sensorDistance = hardwareMap.get(DistanceSensor.class, "donut_counter");

            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            driveActions.applySensorSpeed = true;
            wobbleGripperActions.closeGripper();

            wobbleGripperActions.moveArmUp();
            wobbleGripperActions.moveDistanceSensorDown();
            telemetry.update();
            sleep(500);


            //Step 2: Drive REVERSE towards the building zone
            drive_ForwardAndStop(driveActions, SPEED, 1.5);
            drive_ForwardAndStop(driveActions, 0.25,  .7);
            strafe_RightAndStop(driveActions, 0.1, 0.5);
            sleep(100);

            int height = detectHeight();
             telemetry.update();
            sleep(1000);
            wobbleGripperActions.moveDistanceSensorUp();
            drive_ReverseAndStop(driveActions, SPEED, .05);
            moveBot(height, driveActions, wobbleGripperActions);


            driveActions.applySensorSpeed = false;// we have altered the speed for the forwards movement

        }
    public int detectHeight(){
        DriveWheelActions driveActions = new DriveWheelActions(telemetry, hardwareMap);
        sensorDistance = hardwareMap.get(DistanceSensor.class, "donut_counter");

        telemetry.addData("Distance (cm)",
                String.format(Locale.US, "%.02f", sensorDistance.getDistance(DistanceUnit.CM)));

        double distance = sensorDistance.getDistance(DistanceUnit.CM);
        if (distance < 3.2){
            telemetry.addData("4 donuts", 4);
            return 4;
        } else if (distance > 4.8){
            telemetry.addData("0 donuts", 0);
            return 0;
        } else {
            telemetry.addData("1 donut", 1);
            return 1;
        }


    }
    public void moveBot(int height, DriveWheelActions driveActions, WobbleGripperActions wobbleGripperActions){

        strafe_RightAndStop(driveActions, 0.4, 1.5);
        if (height == 0){
            drive_ForwardAndStop(driveActions, SPEED, 1.8);
            wobbleGripperActions.moveArmDown();
            sleep(500);
            wobbleGripperActions.openGripper();
            sleep(300);
        }
        if (height == 1){
            drive_ForwardAndStop(driveActions, SPEED, 3.0);
            strafe_LeftAndStop(driveActions, 0.4, 1.0);
            wobbleGripperActions.moveArmDown();
            sleep(500);
            wobbleGripperActions.openGripper();
            sleep(300);
        }
        if (height == 4){
            drive_ForwardAndStop(driveActions, SPEED, 4.3);
            strafe_RightAndStop(driveActions, 0.4, 1.2);
            wobbleGripperActions.moveArmDown();
            sleep(500);
            wobbleGripperActions.openGripper();
            sleep(300);
            strafe_LeftAndStop(driveActions, 0.4, 1.2);
            drive_ReverseAndStop(driveActions, SPEED, 1);
        }
    }


}

