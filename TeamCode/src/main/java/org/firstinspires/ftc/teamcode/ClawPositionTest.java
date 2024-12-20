package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Claw Position Test")
public class ClawPositionTest extends LinearOpMode {
    private Outtake outtake;
    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap);
        float clawPosition = 0.0f;
        outtake.claw.setPosition(clawPosition);
        float step = 0.001f;
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                clawPosition += step;
                clawPosition = Range.clip(clawPosition, 0.0f, 1.0f);
                outtake.claw.setPosition(clawPosition);
            }else if(gamepad1.a){
                clawPosition -= step;
                clawPosition = Range.clip(clawPosition, 0.0f, 1.0f);
                outtake.claw.setPosition(clawPosition);
            }
            telemetry.addData("Claw Position: ", clawPosition);
            telemetry.update();
        }
    }
}
