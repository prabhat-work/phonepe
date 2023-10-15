package com.machinecoding.phonepe.service.Implemenation;

import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.models.VersionRange;
import com.machinecoding.phonepe.service.ReleaseStrategyService;
import java.util.ArrayList;
import java.util.List;

public class BetaRolloutStrategyServiceImpl implements ReleaseStrategyService {

  private List<Device> selectedDevices;
  private String name;

  public void BetaRolloutStrategyImpl(ReleaseStrategy releaseStrategy) {
    this.selectedDevices = releaseStrategy.getSelectedDevices();
    this.name = releaseStrategy.getName();
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

  @Override
  public void doRelease(ReleaseStrategy strategy,  AppVersion version) {
    if (strategy != null) {
      List<Device> selectedDevices = strategy.getSelectedDevices();

      for (Device device : selectedDevices) {
        boolean isSupported = isAppVersionSupported(version, device);
        if (isSupported) {
          System.out.println("Released version '" + version.getVersionName() + "' to devices' ");
        } else {
          System.out.println("Version '" + version.getVersionName() + "' is not supported on device '");
        }
      }

    } else {
      System.out.println("No release strategy specified.");
    }

    System.out.println("Version '" + version.getVersionName() + "' released according to strategy: " + strategy.getName());
  }

  public boolean isAppVersionSupported(AppVersion targetVersion, Device device) {
    return checkAppVersionCompatibility(targetVersion, device);
  }

  private boolean checkAppVersionCompatibility(AppVersion targetVersion, Device device) {
    VersionRange supportedVersionRange = targetVersion.getSupportedVersionRange();

    if (supportedVersionRange != null) {
      String targetMinVersion = supportedVersionRange.getMinVersion();
      String targetMaxVersion = supportedVersionRange.getMaxVersion();
      String deviceVersion = device.getVersion();

      return isVersionInRange(deviceVersion, targetMinVersion, targetMaxVersion);
    }
    return false;
  }

  private boolean isVersionInRange(String version, String minVersion, String maxVersion) {
    return version.compareTo(minVersion) >= 0 && version.compareTo(maxVersion) <= 0;
  }

}
