package com.machinecoding.phonepe.service;

import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import java.util.List;

public interface ReleaseStrategyService {

  List<Device> getSelectedDevices();
  String getName();
  void doRelease(ReleaseStrategy releaseStrategy, AppVersion version);
}
