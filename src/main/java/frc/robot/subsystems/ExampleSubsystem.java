// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase implements Reportable {
  private final TalonFX motor;

  private double desiredSpeed = 0.0;
  private boolean enabled = true;
  private TalonFXConfigurator motorConfigurator;
  private VelocityVoltage velocityRequest;
  private final NeutralOut neutralRequest = new NeutralOut();

  private NeutralModeValue neutralMode = NeutralModeValue.Brake;

  public FlyWheel(){
    flywheelMotor = new TalonFX(deviceId:0);
    velocityRequest = new VelocityVoltage(speed:0);
    
    flywheelMotor.setSpeed(newvalue:0.0);

    motorConfigurator = flywheelMotor.getConfigurator();

    setMotorConfigs();

    velocityRequest(newSlot:0);
    zeroEncoder();
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  public void setMotorConfigs(){
    TalonFXConfiguration motorConfigs = new TalonFXConfiguration();

  }

  @Override
  public void periodic() {
    if (!enabled) {
      return;
    }

    VelocityVoltage.Velocity = desiredSpeed;

    flywheelMotor.setControl(velocityRequest);
  }


  public void setEnabled(boolean enabled){
    this.enabled = enabled;
    if(enabled){
      flywheelMotor.setControl(followRequest);
    } else{
      stopMotion();
    }
  }

  public void setTargetSpeed(double speed){
    desiredSpeed = speed;
  }

  public double getSpeed(){
    return flywheelMotor.getSpeed().getValueAsDouble();
  }

  public double getTargetSpeed(){
    return desiredSpeed;
  }

  public boolean atSpeed(){
    return flywheelMotor.getSpeed().getValueAsDouble() > desiredSpeed;
  }

  public void reportToSmartDashboard(LOG_LEVEL priority){
  
  }

  @Override
  public void initShuffleboard(LOG_LEVEL priority){
    switch (priority){
      case OFF;
        break;
      case ALL;

      case MEDIUM;
      
      case MINIMAL;
    }

  }
}


