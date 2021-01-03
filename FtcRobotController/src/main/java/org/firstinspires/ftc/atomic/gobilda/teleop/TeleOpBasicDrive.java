package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.actions.FlickerPositionerActions;
import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;
import org.firstinspires.ftc.atomic.gobilda.actions.WobbleGripperActions;

@TeleOp(name = "TeleOp Shooting", group = "Linear Opmode")
public class TeleOpBasicDrive extends LinearOpMode {

    private DriveWheelActions driveActions = null;
    private FlickerPositionerActions flickerPositionerActions = null;
    private WobbleGripperActions wobbleGripperActions = null;
    //private IntakeShooterActions intakeShooterActions = null;/////
    private DcMotor intake = null;
    private DcMotor Shooter = null;



    @Override
    public void runOpMode() {

        driveActions = new DriveWheelActions(telemetry, hardwareMap);
        flickerPositionerActions = new FlickerPositionerActions(telemetry, hardwareMap);
        wobbleGripperActions = new WobbleGripperActions(telemetry, hardwareMap);
        //intakeShooterActions = new IntakeShooterActions(telemetry,hardwareMap);/////

        intake = hardwareMap.get(DcMotor.class,"intake");
        Shooter = hardwareMap.get(DcMotor.class, "shooter");
        flickerPositionerActions.flicker = hardwareMap.get(Servo.class, "flicker");

        //Set Speed for teleOp. Mecannum wheel speed.

        driveActions.setSpeed(1.0);

        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        Shooter.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            /** Gamepad 1 **/
            driveActions.drive(
                    -gamepad1.left_stick_x,      //joystick controlling strafe
                    -gamepad1.left_stick_y,     //joystick controlling forward/backward
                    -gamepad1.right_stick_x);    //joystick controlling rotation


            flickerPositionerActions.flipper_Forward_Backward(
                    gamepad2.left_bumper,       //open grabber
                    gamepad2.right_bumper);     //close grabber

            flickerPositionerActions.positioner_Forward_Backward(
                    gamepad2.x,                 //
                    gamepad2.y);                  //
            wobbleGripperActions.armUpDown(
                    gamepad1.b,
                    gamepad1.a);
            boolean right_trigger = false;
            boolean left_trigger = false;
            if (gamepad1.left_trigger > 0.2)
                left_trigger = true;
            if (gamepad1.right_trigger > 0.2)
                right_trigger = true;
            wobbleGripperActions.gripperOpenClose(
                    left_trigger,
                    right_trigger);
            flickerPositionerActions.reset_Flicker_Positioner(
                    gamepad2.a);





            double IntakePower = 0;
            if(gamepad2.a){IntakePower = 1.0;}
            intake.setPower(IntakePower);

            double ShooterPower = 0;
            if(gamepad2.b){ShooterPower = 0.4;}
            Shooter.setPower(ShooterPower);

            telemetry.update();
        }

        telemetry.addData("Hubert ", "Stopping");
        telemetry.update();

        idle();
    }
}
