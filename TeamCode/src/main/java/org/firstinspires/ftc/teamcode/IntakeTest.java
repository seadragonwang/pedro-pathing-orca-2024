package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Intake Test")
public class IntakeTest extends LinearOpMode {
    private Intake intake;


    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap);
        float rampPosition = 0.0f;
        intake.ramp.setPosition(rampPosition);
        float step = 0.001f;
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.y){
                rampPosition += step;
                rampPosition = Range.clip(rampPosition, 0.0f, 1.0f);
                intake.ramp.setPosition(rampPosition);
            }else if(gamepad1.a){
                rampPosition -= step;
                rampPosition = Range.clip(rampPosition, 0.0f, 1.0f);
                intake.ramp.setPosition(rampPosition);
            }
            if(gamepad1.x){
                intake.take();
            }else if(gamepad1.b){
                intake.drop();
            }else if(gamepad1.left_bumper){
                intake.stop();
            }
            telemetry.addData("Ramp Position: ", rampPosition);
            telemetry.update();
        }
    }
}
