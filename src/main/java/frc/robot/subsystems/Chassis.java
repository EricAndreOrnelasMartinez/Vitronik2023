package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  // Atributos de Chassis
  private CANSparkMax chassisMotorL1;
  private CANSparkMax chassisMotorL2;
  private CANSparkMax chassisMotorR1;
  private CANSparkMax chassisMotorR2;
  private RelativeEncoder encoderMotorL;
  private RelativeEncoder encoderMotorR;

  public Chassis() {
    chassisMotorL1 = Constants.chassisMotorL1;
    chassisMotorL2 = Constants.chassisMotorL2;
    chassisMotorR1 = Constants.chassisMotorR1;
    chassisMotorR2 = Constants.chassisMotorR2;
    encoderMotorL = chassisMotorL1.getEncoder();
    encoderMotorR = chassisMotorR1.getEncoder();
    chassisMotorL1.setInverted(true);
    chassisMotorL2.setInverted(true);
    encoderMotorL.setPosition(0);
    encoderMotorR.setPosition(0);
  }

  public void move(double y, double x){
    double speedL = (y - (x * 0.8));
    double speedR = (y + (x * 0.8));
    if((x >= -0.1 && x <= 0.1) && (y >= -0.1 && y <= 0.1)){
      speedL = 0;
      speedR = 0;
    }else if(x >= -0.2 && x <= 0.2){
      speedL = y;
      speedR = y;
    }
    chassisMotorL1.set(speedL * 0.25);
    chassisMotorL2.set(speedL * 0.25);
    chassisMotorR1.set(speedR * 0.25);
    chassisMotorR2.set(speedR * 0.25);
  }
  
  public void moveForward(double distance, double speed){
    if(speed < 0){
      throw new IllegalArgumentException("Velocidad negativa");
    }
    double r = (distance * 545/52) + (10 * distance);
    if(encoderMotorR.getPosition() < r && encoderMotorL.getPosition() < r){
      chassisMotorL1.set(speed);
      chassisMotorL2.set(speed);
      chassisMotorR1.set(speed);
      chassisMotorR2.set(speed);
    }else{
      chassisMotorL1.set(0);
      chassisMotorL2.set(0);
      chassisMotorR1.set(0);
      chassisMotorR2.set(0);
    }
  }

  public void moveBackward(double distance, double speed){
    if(speed > 0){
      throw new IllegalArgumentException("Velocidad negativa");
    }
    double r = (distance * 545/52) + (10 * distance);
    if(encoderMotorR.getPosition() > r*-1 && encoderMotorL.getPosition() > r*-1){
      chassisMotorL1.set(speed);
      chassisMotorL2.set(speed);
      chassisMotorR1.set(speed);
      chassisMotorR2.set(speed);
    }else{
      chassisMotorL1.set(0);
      chassisMotorL2.set(0);
      chassisMotorR1.set(0);
      chassisMotorR2.set(0);
    }
  }


  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
