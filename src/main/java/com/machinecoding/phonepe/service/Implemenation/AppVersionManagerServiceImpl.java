package com.machinecoding.phonepe.service.Implemenation;

import com.machinecoding.phonepe.exceptions.AppVersionAlreadyExistsException;
import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.models.TaskType;
import com.machinecoding.phonepe.models.VersionRange;
import com.machinecoding.phonepe.service.AppVersionManagerService;
import com.machinecoding.phonepe.service.PatchGeneratorService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AppVersionManagerServiceImpl implements AppVersionManagerService {

  private List<AppVersion> versions;

  public AppVersionManagerServiceImpl() {
    versions = new ArrayList<>();
    // Initialize other data structures if needed
  }

  @Override
  public void uploadNewVersion(AppVersion newVersion, byte[] versionFile)
      throws AppVersionAlreadyExistsException {

    if (isVersionExists(newVersion)) {
      throw new AppVersionAlreadyExistsException("Version '" + newVersion.getVersionName() + "' already exists.");
    }

    newVersion.setBinaryData(versionFile);
    versions.add(newVersion);

    System.out.println("New version '" + newVersion.getVersionName() + "' uploaded.");
  }


  private boolean isVersionExists(AppVersion newVersion) {
    for (AppVersion existingVersion : versions) {
      if (existingVersion.getVersionName().equals(newVersion.getVersionName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public byte[] createUpdatePatch(AppVersion sourceVersion, AppVersion targetVersion) {

    byte[] sourceBytes = sourceVersion.getBinaryData();
    byte[] targetBytes = targetVersion.getBinaryData();
    byte[] updatedData = new byte[0];

    PatchGeneratorService patchGenerator = new PatchGeneratorServiceImpl();

    try {
      // Create a binary patch
      byte[] patch = patchGenerator.createBinaryPatch(sourceBytes, targetBytes);
      updatedData = patchGenerator.applyBinaryPatch(sourceBytes, patch);
      System.out.println("Updated patch with Data" + new String(updatedData));
    }
    catch (Exception ex){
      System.out.println("Error: " + ex.getMessage());
    }
    
    return updatedData;
  }

  @Override
  public void releaseVersion(AppVersion version, ReleaseStrategy strategy) {

    if (strategy != null) {
      List<Device> selectedDevices = strategy.getSelectedDevices();

      for (Device device : selectedDevices) {
        boolean isSupported = isAppVersionSupported(version, device);
        if (isSupported) {
          System.out.println("Released version '" + version.getVersionName() + "' to device '");
        } else {
          System.out.println("Version '" + version.getVersionName() + "' is not supported on device '");
        }
      }

    } else {
      System.out.println("No release strategy specified.");
    }

    System.out.println("Version '" + version.getVersionName() + "' released according to strategy: " + strategy.getName());
  }

  @Override
  public List<AppVersion> getAllVersions() {
     return versions;
  }

  @Override
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
    return true;
  }

  private boolean isVersionInRange(String version, String minVersion, String maxVersion) {
    return version.compareTo(minVersion) >= 0 && version.compareTo(maxVersion) <= 0;
  }

  @Override
  public boolean checkForInstall(Device device) {
    return true;
  }


  @Override
  public void executeTask(TaskType taskType, AppVersion targetVersion, Device device) {
    if (taskType == TaskType.INSTALL) {
      System.out.println("Installed succesfully");
      //installApp(targetVersion, device);
    } else if (taskType == TaskType.UPDATE) {
    //  updateApp(targetVersion, device);
    } else {
      System.out.println("Invalid task type.");
    }
  }

}