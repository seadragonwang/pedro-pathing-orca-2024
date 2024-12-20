package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

import static org.firstinspires.ftc.teamcode.RobotConstants.*;

@Autonomous(name = "Specimen Auto")
public class SpecimenAuto extends OpMode {
    private Follower follower;
    private Outtake outtake;
    private Intake intake;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private final Pose startPose = new Pose(-9, -63.30, Math.toRadians(0));
    private final Pose scorePose = new Pose(-33.872, -72.511, Math.toRadians(0));
    private final Pose scorePose2 = new Pose(-35.872, -72.511, Math.toRadians(0));
    private final Pose startPushPose1 = new Pose(-57.872, -26.383, Math.toRadians(0));
    private final Pose endPushPose1 = new Pose(-22.447, -28.255, Math.toRadians(0));
    private final Pose startPushPose2 = new Pose(-57.872, -18.383, Math.toRadians(0));
    private final Pose endPushPose2 = new Pose(-22.617, -19.574, Math.toRadians(0));
    private final Pose startPushPose3 = new Pose(-57.532, -12.085, Math.toRadians(0));
    private final Pose endPushPose3 = new Pose(-22.128, -12.085, Math.toRadians(0));

    private final Pose specimenPickupPose = new Pose(-9.00, -34.213, Math.toRadians(0));
    private PathChain scoreSpecimen1, pushSample1, pushSample2, pushSample3, pickupSpecimen2, scoreSpecimen2, pickupSpecimen3, scoreSpecimen3, pickupSpecimen4, scoreSpecimen4;

    /**
     * This is the variable where we store the state of our auto.
     * It is used by the pathUpdate method.
     */
    private int pathState;

    public void buildPaths() {
        PathBuilder builder = new PathBuilder();

        scoreSpecimen1 = builder.addPath(
                        // Score Specimen 1
                        new BezierLine(
                                new Point(startPose),
                                new Point(scorePose)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        pushSample1 = new PathBuilder()
                .addPath(
                        // Start to push sample 1
                        new BezierCurve(
                                new Point(-33.872, -72.511, Point.CARTESIAN),
                                new Point(-12.936, -13.277, Point.CARTESIAN),
                                new Point(-71.660, -49.872, Point.CARTESIAN),
                                new Point(startPushPose1)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        // Line 3
                        new BezierLine(
                                new Point(startPushPose1),
                                new Point(endPushPose1)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        pushSample2 = new PathBuilder()
                .addPath(
                        // Push sample 2
                        new BezierCurve(
                                new Point(endPushPose1),
                                new Point(-65.191, -32.000, Point.CARTESIAN),
                                new Point(startPushPose2)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        // Line 5
                        new BezierLine(
                                new Point(startPushPose2),
                                new Point(endPushPose2)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        pushSample3 = new PathBuilder()
                .addPath(
                        // Push sample 3
                        new BezierCurve(
                                new Point(endPushPose2),
                                new Point(-60.936, -23.489, Point.CARTESIAN),
                                new Point(startPushPose3)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        // Line 7
                        new BezierLine(
                                new Point(startPushPose3),
                                new Point(endPushPose3)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        pickupSpecimen2 = new PathBuilder()
                .addPath(
                        // Line 8
                        new BezierCurve(
                                new Point(endPushPose3),
                                new Point(-28.255, -34.043, Point.CARTESIAN),
                                new Point(specimenPickupPose)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        scoreSpecimen2 = new PathBuilder().addPath(
                        // Line 9
                        new BezierCurve(
                                new Point(specimenPickupPose),
                                new Point(-8.340, -75.915, Point.CARTESIAN),
                                new Point(scorePose2)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        pickupSpecimen3 = new PathBuilder()
                .addPath(
                        // Line 10
                        new BezierCurve(
                                new Point(scorePose2),
                                new Point(-35.745, -29.617, Point.CARTESIAN),
                                new Point(specimenPickupPose)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();

        opmodeTimer.resetTimer();
        intake = new Intake(hardwareMap);
        intake.ramp.setPosition(RAMP_DROP_POSITION);
        outtake = new Outtake(hardwareMap);
        outtake.arm.setPosition(ARM_SPECIMEN_INITIAL_POSITION);
        outtake.clawPivot.setPosition(CLAW_PIVOT_SPECIMEN_INITIAL_POSITION);
        outtake.claw.setPosition(CLAW_CLOSE_POSITION);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        buildPaths();
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();

        // Feedback to Driver Hub
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    public boolean isCloseEnough2(Pose targetPose, int gap) {
        return Math.abs(follower.getPose().getX()) > Math.abs((targetPose.getX() - gap)) && Math.abs(follower.getPose().getY()) > Math.abs(targetPose.getY() - gap);
    }

    public boolean isCloseEnough3(Pose targetPose) {
        return (follower.getPose().getX() < targetPose.getX() - 2) && (follower.getPose().getY() < targetPose.getY() - 2);
    }

    public boolean isCloseEnough(Pose targetPose, int threshold) {
        return Math.abs(follower.getPose().getX() - targetPose.getX()) < threshold && Math.abs(follower.getPose().getY() - targetPose.getY()) < threshold;
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                intake.ramp.setPosition(RAMP_SPECIMEN_SCORE_POSITION);
                outtake.scoreSpecimen1();
                follower.followPath(scoreSpecimen1, true);
                setPathState(1);
                break;
            case 1:
                if (isCloseEnough2(scorePose, 2)) {
                    outtake.claw.setPosition(CLAW_SPECIMEN_OPEN_POSITION);
                    follower.followPath(pushSample1);
                    setPathState(2);
                }
                break;
            case 2:
                if (isCloseEnough(endPushPose1, 3)) {
                    outtake.pickupSpecimen();
                    follower.followPath(pushSample2);
                    setPathState(3);
                }
                break;
            case 3:
                if (isCloseEnough(endPushPose2, 3)) {
                    follower.followPath(pushSample3);
                    setPathState(4);
                }
                break;
            case 4:
                if (isCloseEnough(endPushPose3, 3)) {
                    follower.followPath(pickupSpecimen2, true);
                    setPathState(5);
                }
                break;
            case 5:
                if (isCloseEnough(specimenPickupPose, 1)) {
                    outtake.claw.setPosition(CLAW_CLOSE_POSITION);
                    setPathState(6);
                }
                break;
            case 6:
                    outtake.scoreSpecimen2();
                    follower.followPath(scoreSpecimen2, true);
                    setPathState(7);

                break;
            case 7:
                if (isCloseEnough2(scorePose2, 3)) {
                    outtake.claw.setPosition(CLAW_CLOSE_POSITION);
                    follower.followPath(pickupSpecimen3, true);
                    setPathState(8);
                }
                break;
            case 8:
                if (follower.getPose().getX()>-30) {
                    outtake.pickupSpecimen();
                    setPathState(9);
                }
                break;
            case 9:
                if (isCloseEnough(specimenPickupPose, 1) && pathTimer.getElapsedTimeSeconds() > 0.5) {
                    outtake.claw.setPosition(CLAW_CLOSE_POSITION);
                    setPathState(10);
                }
                break;
            case 10:
                if (pathTimer.getElapsedTimeSeconds() > 0.5) {
                    outtake.scoreSpecimen2();
                    follower.followPath(scoreSpecimen2, true);
                    setPathState(11);
                }
                break;
            case 11:
                if (isCloseEnough2(scorePose, 2)) {
                    outtake.prepareSpecimenPickup1();
                    follower.followPath(pickupSpecimen3, true);
                    setPathState(12);
                }
                break;
            case 12:
                if (pathTimer.getElapsedTimeSeconds() > 0.5) {
                    outtake.prepareSpecimenPickup2();
                    setPathState(-1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void stop() {

    }
}
