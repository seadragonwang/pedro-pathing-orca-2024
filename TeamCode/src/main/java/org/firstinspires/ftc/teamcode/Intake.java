package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public CRServo intakeleft;
    public CRServo intakeRight;
    public Servo ramp;
    public Intake(HardwareMap hardwareMap){
        intakeleft = hardwareMap.get(CRServo.class, "intake");
        intakeRight = hardwareMap.get(CRServo.class, "intake2");
        ramp = hardwareMap.get(Servo.class, "ramp");
    }

    public void take(){
        intakeleft.setPower(-1.0);
        intakeRight.setPower(1.0);
    }

    public void drop(){
        intakeleft.setPower(1.0);
        intakeRight.setPower(-1.0);
    }

    public void stop(){
        intakeleft.setPower(0.0);
        intakeRight.setPower(0.0);
    }
}
