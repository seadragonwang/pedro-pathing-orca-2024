package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Vertical Slide Position Test")
public class VerticalSlideTest extends LinearOpMode {
    private Outtake outtake;
    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap);
        PIDFCoefficients coeffVertivalLeft = outtake.verticalLeft.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION);
        PIDFCoefficients coeffVertivalCenter = outtake.verticalLeft.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addLine()
                .addData("Vertical Left PIDF Coefficients: ", "%s", coeffVertivalLeft.toString())
                .addData("Vertical Center PIDF Coefficients: ", "%s", coeffVertivalCenter.toString())
                .addData("Vertical Left Position: ", outtake.verticalLeft.getCurrentPosition())
                .addData("Vertical Center Position: ", outtake.verticalCenter.getCurrentPosition());
        telemetry.update();
        int slidePosition = outtake.verticalLeft.getCurrentPosition();
        float step = 50;
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                slidePosition += step;
                slidePosition = Range.clip(slidePosition, 0, 1150);
                outtake.verticalLeft.setTargetPosition(slidePosition);
                outtake.verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtake.verticalCenter.setTargetPosition(slidePosition);
                outtake.verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtake.verticalLeft.setPower(1.0);
                outtake.verticalCenter.setPower(1.0);
            }else if(gamepad1.a){
                slidePosition -= step;
                slidePosition = Range.clip(slidePosition, 0, 1150);
                outtake.verticalLeft.setTargetPosition(slidePosition);
                outtake.verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtake.verticalCenter.setTargetPosition(slidePosition);
                outtake.verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                outtake.verticalLeft.setPower(1.0);
                outtake.verticalCenter.setPower(1.0);
            }
            telemetry.addData("Slide Position: ", slidePosition);
            telemetry.addData("Left Position: ", outtake.verticalLeft.getCurrentPosition());
            telemetry.addData("Center Position: ", outtake.verticalCenter.getCurrentPosition());
            telemetry.update();
        }
    }
}
