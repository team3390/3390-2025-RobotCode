package com.team3390.lib.drivers;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class TalonSRXCreator {

  private final static int kTimeoutMs = 100;
  
  public static class Configuration {
    public NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
    public double NEUTRAL_DEADBAND = 0.04;
    public boolean INVERTED = false;
  }

  private static final Configuration kDefaultConfiguration = new Configuration();
  private static final Configuration kSlaveConfiguration = new Configuration();

  public static LazyTalonSRX createDefaultMasterTalon(int id) {
    return createTalon(id, kDefaultConfiguration);
  }

  public static LazyTalonSRX createDefaultPermanentSlaveTalon(int id, int master_id) {
    final LazyTalonSRX talon = createTalon(id, kSlaveConfiguration);
    talon.set(ControlMode.Follower, master_id);
    return talon;
  }

  public static LazyTalonSRX createCustomPermanentSlaveTalon(int id, int master_id, Configuration config) {
    final LazyTalonSRX talon = createTalon(id, config);
    talon.set(ControlMode.Follower, master_id);
    return talon;
  }

  public static LazyTalonSRX createTalon(int id, Configuration config) {
    LazyTalonSRX talon = new LazyTalonSRX(id);
    talon.set(ControlMode.PercentOutput, 0.0);
    
    talon.clearMotionProfileHasUnderrun(kTimeoutMs);
    talon.clearMotionProfileTrajectories();

    talon.clearStickyFaults();

    talon.configSetParameter(ParamEnum.eClearPositionOnLimitF, 0, 0, 0, kTimeoutMs);
    talon.configSetParameter(ParamEnum.eClearPositionOnLimitR, 0, 0, 0, kTimeoutMs);

    talon.configNominalOutputForward(0, kTimeoutMs);
    talon.configNominalOutputReverse(0, kTimeoutMs);
    talon.configNeutralDeadband(config.NEUTRAL_DEADBAND, kTimeoutMs);

    talon.configPeakOutputForward(1.0, kTimeoutMs);
    talon.configPeakOutputReverse(-1.0, kTimeoutMs);

    talon.setNeutralMode(config.NEUTRAL_MODE);

    talon.setInverted(config.INVERTED);

    return talon;
  }

}
