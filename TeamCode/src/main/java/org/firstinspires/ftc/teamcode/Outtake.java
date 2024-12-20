package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.RobotConstants.*;

public class Outtake {
    public DcMotorEx verticalLeft;
    public DcMotorEx verticalRight;
    public DcMotorEx verticalCenter;

    public Servo claw;
    public Servo arm;
    public Servo clawPivot;

    public Outtake(HardwareMap hardwareMap){
        verticalLeft = hardwareMap.get(DcMotorEx.class, "vertical1");
        verticalLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalLeft.setTargetPositionTolerance(20);
        verticalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        verticalLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLeft.setPositionPIDFCoefficients(5);
//        DcMotorEx verticalRight = hardwareMap.get(DcMotorEx.class, "vertical2");
//        verticalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        verticalRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        verticalCenter = hardwareMap.get(DcMotorEx.class, "vertical3");
        verticalCenter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        verticalCenter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        verticalCenter.setTargetPositionTolerance(20);
        verticalCenter.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalCenter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalCenter.setPositionPIDFCoefficients(5);

        claw = hardwareMap.get(Servo.class, "grab");
        clawPivot = hardwareMap.get(Servo.class, "clawTurnVert");
        arm = hardwareMap.get(Servo.class, "armPivot");
    }

    public void scoreSpecimen1(){
        verticalLeft.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_SCORE_POSITION);
        verticalCenter.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_SCORE_POSITION);
        verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalLeft.setPower(1.0);
        verticalCenter.setPower(1.0);
        arm.setPosition(ARM_SPECIMEN_SCORE_POSITION);
        clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_SCORE_POSITION);
    }

    public void scoreSpecimen2(){
        verticalLeft.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_SCORE_POSITION);
        verticalCenter.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_SCORE_POSITION);
        verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalLeft.setPower(1.0);
        verticalCenter.setPower(1.0);
        arm.setPosition(ARM_SPECIMEN_SCORE_POSITION);
        clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_SCORE_POSITION);
    }

    public void prepareSpecimenPickup1() {
        claw.setPosition(CLAW_SPECIMEN_OPEN_POSITION);
        clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_PICKUP_POSITION);
        arm.setPosition(ARM_SPECIMEN_PICKUP_POSITION);
    }

    public void pickupSpecimen(){
        arm.setPosition(ARM_SPECIMEN_PICKUP_POSITION);
        clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_PICKUP_POSITION);
        claw.setPosition(CLAW_SPECIMEN_OPEN_POSITION);
        verticalLeft.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_PICKUP_POSITION);
        verticalCenter.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_PICKUP_POSITION);
        verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalLeft.setPower(1.0);
        verticalCenter.setPower(1.0);
    }
    public void prepareSpecimenPickup2(){
        verticalLeft.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_PICKUP_POSITION);
        verticalCenter.setTargetPosition(VERTICAL_LINEAR_SLIDE_SPECIMEN_PICKUP_POSITION);
        verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalLeft.setPower(1.0);
        verticalCenter.setPower(1.0);
    }

    public void prepareTeleOp(){
        arm.setPosition(ARM_SAMPLE_PICKUP_POSITION);
        claw.setPosition(CLAW_OPEN_POSITION);

        clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_PICKUP_POSITION);
        verticalLeft.setTargetPosition(VERTICAL_LINEAR_SLIDE_SAMPLE_PICKUP_POSITION);
        verticalCenter.setTargetPosition(VERTICAL_LINEAR_SLIDE_SAMPLE_PICKUP_POSITION);
        verticalLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalCenter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalLeft.setPower(1.0);
        verticalCenter.setPower(1.0);
    }
}
