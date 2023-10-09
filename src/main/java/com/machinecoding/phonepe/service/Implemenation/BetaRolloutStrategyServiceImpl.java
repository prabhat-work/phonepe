package com.machinecoding.phonepe.service.Implemenation;

import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.service.ReleaseStrategyService;
import java.util.ArrayList;
import java.util.List;

public class BetaRolloutStrategyServiceImpl implements ReleaseStrategyService {

  private List<Device> selectedDevices;
  private String name;

  public void BetaRolloutStrategyImpl(String name) {
    this.selectedDevices = new ArrayList<>();
    this.name = name;
  }

  public void addSelectedDevice(Device device) {
    selectedDevices.add(device);
  }

  @Override
  public List<Device> getSelectedDevices() {
    return selectedDevices;
  }

  @Override
  public String getName() {
    return name;
  }

}
