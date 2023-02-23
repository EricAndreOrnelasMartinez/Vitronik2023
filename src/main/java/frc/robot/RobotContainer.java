package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ClaseWiper;
import frc.robot.commands.MoveArmBackward;
import frc.robot.commands.MoveArmDown;
import frc.robot.commands.MoveArmForward;
import frc.robot.commands.MoveArmUp;
import frc.robot.commands.OpenWiper;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  public static CommandXboxController m_driverController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().whileTrue(new MoveArmBackward(Robot.m_Arm));
    m_driverController.b().whileTrue(new MoveArmForward(Robot.m_Arm));
    m_driverController.y().whileTrue(new OpenWiper(Robot.m_Arm));
    m_driverController.x().whileTrue(new ClaseWiper(Robot.m_Arm));
    m_driverController.leftBumper().whileTrue(new MoveArmDown(Robot.m_Arm));
    m_driverController.rightBumper().whileTrue(new MoveArmUp(Robot.m_Arm));
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(new ExampleSubsystem());
  }
}
