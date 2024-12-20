package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Claw Pivot Position Test")
public class ClawPivotPositionTest extends LinearOpMode {
    private Outtake outtake;
    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake(hardwareMap);
        float clawPivotPosition = 0.5f;
        outtake.clawPivot.setPosition(clawPivotPosition);
        float step = 0.001f;
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                clawPivotPosition += step;
                clawPivotPosition = Range.clip(clawPivotPosition, 0.0f, 1.0f);
                outtake.clawPivot.setPosition(clawPivotPosition);
            }else if(gamepad1.a){
                clawPivotPosition -= step;
                clawPivotPosition = Range.clip(clawPivotPosition, 0.0f, 1.0f);
                outtake.clawPivot.setPosition(clawPivotPosition);
            }
            telemetry.addData("Claw Pivot Position: ", clawPivotPosition);
            telemetry.update();
        }
    }
}
