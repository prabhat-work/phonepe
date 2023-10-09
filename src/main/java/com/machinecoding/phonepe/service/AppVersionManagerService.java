package com.machinecoding.phonepe.service;

import com.machinecoding.phonepe.exceptions.AppVersionAlreadyExistsException;
import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.models.TaskType;
import java.util.Collection;
import java.util.List;

public interface AppVersionManagerService {

  void uploadNewVersion(AppVersion newVersion, byte[] versionFile)
      throws AppVersionAlreadyExistsException;

  byte[] createUpdatePatch(AppVersion sourceVersion, AppVersion targetVersion);

  void releaseVersion(AppVersion version, ReleaseStrategy strategy);

  boolean isAppVersionSupported(AppVersion targetVersion, Device device);

  List<AppVersion> getAllVersions();

  boolean checkForInstall(Device device);

  void executeTask(TaskType taskType, AppVersion targetVersion, Device device);
}
