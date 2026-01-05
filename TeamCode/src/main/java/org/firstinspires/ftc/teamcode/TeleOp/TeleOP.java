package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOP extends LinearOpMode {
    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;
    public void drive(){
        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
         frontLeftPower  = axial + lateral + yaw;
         frontRightPower = axial - lateral - yaw;
         backLeftPower   = axial - lateral + yaw;
         backRightPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;
        }

    }
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeft = this.hardwareMap.dcMotor.get("left_front_mtr");
        DcMotor frontRight = this.hardwareMap.dcMotor.get("right_front_mtr");
        DcMotor backLeft = this.hardwareMap.dcMotor.get("left_back_mtr");
        DcMotor backRight = this.hardwareMap.dcMotor.get("right_back_mtr");
        CRServo loader = hardwareMap.get(CRServo.class, "loader");
        DcMotorEx outake = hardwareMap.get(DcMotorEx.class,"outake");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()){
            drive();
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);
        if (gamepad2.y){
            outake.setPower(1);
        }else if (gamepad2.a){
            outake.setPower(0);
        }
        loader.setPower(gamepad2.left_trigger);

    }}}

