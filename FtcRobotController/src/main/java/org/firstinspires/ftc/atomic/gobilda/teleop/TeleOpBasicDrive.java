package org.firstinspires.ftc.atomic.gobilda.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.atomic.gobilda.actions.FlickerPositionerActions;
import org.firstinspires.ftc.atomic.gobilda.actions.DriveWheelActions;

@TeleOp(name = "TeleOp Shooting", group = "Linear Opmode")
public class TeleOpBasicDrive extends LinearOpMode {

    private DriveWheelActions driveActions = null;
    private FlickerPositionerActions flickerPositionerActions = null;
    //private IntakeShooterActions intakeShooterActions = null;/////
    private DcMotor intake = null;
    private DcMotor Shooter = null;


    @Override
    public void runOpMode() {

        driveActions = new DriveWheelActions(telemetry, hardwareMap);
        flickerPositionerActions = new FlickerPositionerActions(telemetry, hardwareMap);
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
                    gamepad1.left_bumper,       //open grabber
                    gamepad1.right_bumper);     //close grabber

            flickerPositionerActions.positioner_Forward_Backward(
                    gamepad1.x,                 //
                    gamepad1.y                  //
            );




            double IntakePower = 0;
            if(gamepad1.dpad_up){IntakePower = 0.65;}
            intake.setPower(IntakePower);

            double ShooterPower = 0;
            if(gamepad1.dpad_down){ShooterPower = 0.4;}
            Shooter.setPower(ShooterPower);

            telemetry.update();
        }

        telemetry.addData("Hubert ", "Stopping");
        telemetry.update();

        idle();
    }
}
