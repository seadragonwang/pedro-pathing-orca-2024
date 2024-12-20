package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Arm Position Test")
public class ArmPositionTest extends LinearOpMode {
    private Outtake outtake;
    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap);
        float armPosition = 0.5f;
        outtake.arm.setPosition(armPosition);
        float step = 0.001f;
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                armPosition += step;
                armPosition = Range.clip(armPosition, 0.0f, 1.0f);
                outtake.arm.setPosition(armPosition);
            }else if(gamepad1.a){
                armPosition -= step;
                armPosition = Range.clip(armPosition, 0.0f, 1.0f);
                outtake.arm.setPosition(armPosition);
            }
            telemetry.addData("Arm Position: ", armPosition);
            telemetry.update();
        }
    }
}
