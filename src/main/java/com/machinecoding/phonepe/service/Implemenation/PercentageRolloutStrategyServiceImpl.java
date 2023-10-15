package com.machinecoding.phonepe.service.Implemenation;

import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.service.ReleaseStrategyService;
import java.util.List;

public class PercentageRolloutStrategyServiceImpl implements ReleaseStrategyService {

  @Override
  public List<Device> getSelectedDevices() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void doRelease(ReleaseStrategy releaseStrategy, AppVersion version) {

  }
}
