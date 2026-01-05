package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeft = this.hardwareMap.dcMotor.get("left_front_mtr");
        DcMotor frontRight = this.hardwareMap.dcMotor.get("right_front_mtr");
        DcMotor backLeft = this.hardwareMap.dcMotor.get("left_back_mtr");
        DcMotor backRight = this.hardwareMap.dcMotor.get("right_back_mtr");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);
        backLeft.setPower(0.5);
        sleep(1000);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
