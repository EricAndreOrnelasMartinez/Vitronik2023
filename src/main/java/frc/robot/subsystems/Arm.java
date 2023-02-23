package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private static VictorSPX arLifterForwardMotor; 
  private static VictorSPX arLifterBackwardMotor;
  private CANSparkMax armUpDown;
  private RelativeEncoder encoderArm;
  private VictorSPX wiper;
  private DigitalInput limitForward;
  private DigitalInput limitReverse;

  public Arm() {
    arLifterForwardMotor = Constants.arLifterForwardMotor;
    arLifterBackwardMotor = Constants.arLifterBackwardMotor;
    armUpDown =  Constants.armUpDown;
    armUpDown.setIdleMode(IdleMode.kBrake);
    encoderArm = armUpDown.getEncoder();
    wiper = Constants.wipermotor;
    limitForward = new DigitalInput(7);
    limitReverse = new DigitalInput(9);
  }
  public void openwiper(){
    wiper.set(ControlMode.PercentOutput, 1);
  }
  public void closewiper(){
    wiper.set(ControlMode.PercentOutput, -1);
  }

  public void backward(double speed){
    if(!limitReverse.get()){
      arLifterBackwardMotor.set(ControlMode.PercentOutput, speed * -1);
    }else{
      arLifterBackwardMotor.set(ControlMode.PercentOutput, 0);
    }
    //System.out.println(limitReverse.get());
  }

  public void forward(double speed){
    if(limitForward.get()){
      arLifterForwardMotor.set(ControlMode.PercentOutput, speed * -1);
    }else{
      arLifterForwardMotor.set(ControlMode.PercentOutput, 0);
    }
    //System.out.println(limitForward.get());
  }

  public void moveUp(double speed, double position){
    //if(encoderArm.getPosition() < position){
      //armUpDown.set(speed);
      //System.out.println("True!!!!!");
    //}else{
      //armUpDown.set(0);
    //}
    armUpDown.set(speed);
    System.out.println(encoderArm.getPosition());
  }

  public void moveDown(double speed, double position){
    //if(encoderArm.getPosition() > position){
      //armUpDown.set(speed * -1);
    //}else{
    //  armUpDown.set(0);
    //}
    armUpDown.set(speed * -1);
    System.out.println(encoderArm.getPosition());
  }

  public void stop(){
    arLifterBackwardMotor.set(ControlMode.PercentOutput, 0);
    arLifterForwardMotor.set(ControlMode.PercentOutput, 0);
    armUpDown.set(0);
    wiper.set(ControlMode.PercentOutput, 0);
  }

  public void restEncoder(){
    encoderArm.setPosition(0);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
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
