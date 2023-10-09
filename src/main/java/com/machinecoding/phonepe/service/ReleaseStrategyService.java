package com.machinecoding.phonepe.service;

import com.machinecoding.phonepe.models.Device;
import java.util.List;

public interface ReleaseStrategyService {

  List<Device> getSelectedDevices();
  String getName();

}
