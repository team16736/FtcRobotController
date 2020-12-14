package org.firstinspires.ftc.atomic.gobilda.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.atomic.gobilda.actions.ArmElbowGripperActions;
import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.WobbleGripperActions;


/**
 * There are 2 color sensors:
 * 1. sensor_color_distance1
 * 2. sensor_color_distance2
 *
 */
@Autonomous(name = "Ultimate", group = "Ultimate")
public class DetectAllDonuts extends HelperAction {

    @Override
    public void runOpMode() {

//        DriveWheelActions driveActions = new DriveWheelActions(telemetry, hardwareMap);
//        WobbleGripperActions wobbleGripperActions = new WobbleGripperActions(telemetry, hardwareMap);
//         ArmElbowGripperActions armActions = null;

        int totalRings = detectNumberOfRings();
        telemetry.addData("totalRings: ", totalRings);

        if(totalRings == 0) {

            telemetry.addData("0 : ", "Path 1");

        } else if (totalRings == 1) {

            telemetry.addData("1 : ", "Path 2");

        } else if (totalRings == 4) {

            telemetry.addData("4 : ", "Path 3");
        }

         telemetry.update();

    }


    public int detectNumberOfRings(){

        int totalRings = 0;

        boolean detected_top_ring = identify_Rings(bottom_sensor, top_sensor, hsvValues);

        boolean detected_bottom_ring;

        return totalRings;
    }

}

