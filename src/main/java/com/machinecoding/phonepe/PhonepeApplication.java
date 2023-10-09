package com.machinecoding.phonepe;

import com.machinecoding.phonepe.exceptions.AppVersionAlreadyExistsException;
import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.Device;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.models.TaskType;
import com.machinecoding.phonepe.models.VersionRange;
import com.machinecoding.phonepe.service.AppVersionManagerService;
import com.machinecoding.phonepe.service.Implemenation.AppVersionManagerServiceImpl;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PhonepeApplication {

	public static void main(String[] args) {

		AppVersionManagerService appVersionManager = new AppVersionManagerServiceImpl();

		VersionRange supportedVersionRange = new VersionRange("1.0", "2.0");
		AppVersion newVersion = new AppVersion("1.2", 2, "New features", new Date(), null, supportedVersionRange);
		AppVersion sourceVersion = new AppVersion("1.1", 1, "Bug fixes", new Date(), new byte[]{0x01, 0x02, 0x03}, supportedVersionRange);
		AppVersion targetVersion = new AppVersion("1.2", 2, "New features", new Date(), new byte[]{0x04, 0x02, 0x01},supportedVersionRange);
		ReleaseStrategy strategy = new ReleaseStrategy("BetaRolloutStrategy", "Roll out to selected devices");
		Device device = new Device("Device123", "MyDevice", "Android 11", "Some hardware info", "1.0");
		strategy.addDevice(device);
		TaskType taskType = TaskType.INSTALL;

		try {
			appVersionManager.uploadNewVersion(newVersion, new byte[]{0x01, 0x02, 0x03});

			appVersionManager.createUpdatePatch(sourceVersion, targetVersion);

			appVersionManager.releaseVersion(newVersion, strategy);

			boolean isSupported = appVersionManager.isAppVersionSupported(targetVersion, device);
			log.info("Is App version supported for target version:  {} on deviceId: {} :=> {} " , targetVersion.getVersionName(), device.getDeviceId(), isSupported);

			boolean canInstall = appVersionManager.checkForInstall(device);
			log.info("Is Update available for deviceId: {} => {}" , device.getDeviceId(), canInstall);

			appVersionManager.executeTask(taskType, targetVersion, device);

			SpringApplication.run(PhonepeApplication.class, args);
		}
		catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}

	}

}
