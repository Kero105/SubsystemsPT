// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase implements Reportable {
  private final TalonFX flywheelMotor;

  private double desiredSpeed = 0.0;
  private boolean enabled = true;
  private TalonFXConfigurator motorConfigurator;
  private VelocityVoltage velocityRequest;
  private final NeutralOut neutralRequest = new NeutralOut();

  private NeutralModeValue neutralMode = NeutralModeValue.Brake;

  public ExampleSubsystem(){
    flywheelMotor = new TalonFX(0);
    velocityRequest = new VelocityVoltage(0);

    motorConfigurator = flywheelMotor.getConfigurator();

    setMotorConfigs();

    velocityRequest = new VelocityVoltage(0);

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

    velocityRequest.Velocity = desiredSpeed;

    flywheelMotor.setControl(velocityRequest);
  }


  public void setEnabled(boolean enabled){
    this.enabled = enabled;
    if(!enabled){
      flywheelMotor.setControl(neutralRequest);
    } 
  }

  public void setTargetSpeed(double speed){
    desiredSpeed = speed;
  }

  public double getSpeed(){
    return flywheelMotor.getVelocity().getValueAsDouble();
  }

  public double getTargetSpeed(){
    return desiredSpeed;
  }

  public boolean atSpeed(){
    return flywheelMotor.getVelocity().getValueAsDouble() > desiredSpeed;
  }

  public void reportToSmartDashboard(LOG_LEVEL priority){
  
  }

  @Override
  public void initShuffleboard(LOG_LEVEL priority){
    switch (priority){
      case OFF:
        break;
      case ALL:

      case MEDIUM:
      
      case MINIMAL:
    }

  }
}


