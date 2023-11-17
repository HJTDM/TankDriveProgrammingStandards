// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax leftFront;
  private CANSparkMax rightFront;
  private CANSparkMax leftBack;
  private CANSparkMax rightBack;

  private static Drivetrain drivetrain;
  public static Drivetrain getInstance(){
    if(drivetrain == null){
      drivetrain = new Drivetrain();
    }
    return drivetrain;
  }

  public Drivetrain() {
    leftFront = new CANSparkMax(DrivetrainConstants.LEFT_FRONT_ID, MotorType.kBrushless);
    rightFront = new CANSparkMax(DrivetrainConstants.RIGHT_FRONT_ID, MotorType.kBrushless);
    leftBack = new CANSparkMax(DrivetrainConstants.LEFT_BACK_ID, MotorType.kBrushless);
    rightBack = new CANSparkMax(DrivetrainConstants.RIGHT_BACK_ID, MotorType.kBrushless);

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);
  
    leftFront.restoreFactoryDefaults();
    rightFront.restoreFactoryDefaults();
    leftBack.restoreFactoryDefaults();
    rightBack.restoreFactoryDefaults();
    //Configuring...
    leftFront.burnFlash();
    rightFront.burnFlash();
    leftBack.burnFlash();
    rightBack.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double throttle, double twist){
    throttle = Math.abs(throttle) > 0.1 ? throttle : 0;
    twist = Math.abs(twist) > 0.1 ? twist : 0;

    leftFront.set(throttle + twist);
    rightFront.set(throttle - twist);
  }
}
