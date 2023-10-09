package com.machinecoding.phonepe;

import com.machinecoding.phonepe.exceptions.AppVersionAlreadyExistsException;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.VersionRange;
import com.machinecoding.phonepe.service.AppVersionManagerService;
import com.machinecoding.phonepe.service.Implemenation.AppVersionManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhonepeApplicationTests {

	private AppVersionManagerService appVersionManager;

	@BeforeEach
	public void setUp() {
		appVersionManager = new AppVersionManagerServiceImpl();
	}

	@Test
	public void testUploadNewVersion() throws AppVersionAlreadyExistsException {
		VersionRange supportedVersionRange = new VersionRange("1.0", "2.0");
		AppVersion newVersion = new AppVersion("1.2", 2, "New features", null, null, supportedVersionRange);
		appVersionManager.uploadNewVersion(newVersion, new byte[]{0x01, 0x02, 0x03});
		List<AppVersion> allVersions = appVersionManager.getAllVersions();
		assertTrue(allVersions.contains(newVersion));
	}


	@Test
	public void testCreateUpdatePatch() {
		try {
			VersionRange supportedVersionRange = new VersionRange("1.0", "2.0");
			AppVersionManagerService appVersionManager = new AppVersionManagerServiceImpl();
			AppVersion sourceVersion = new AppVersion("1.1", 1, "Bug fixes", new Date(), new byte[]{0x01, 0x02, 0x03}, supportedVersionRange);
			AppVersion targetVersion = new AppVersion("1.2", 2, "New features", new Date(), new byte[]{0x04, 0x02, 0x01},supportedVersionRange);
			byte[] updatePatch = appVersionManager.createUpdatePatch(sourceVersion, targetVersion);

			int expectedPatchLength = 3;
			assertEquals(expectedPatchLength, updatePatch.length);
		} catch (Exception e) {
			fail("createUpdatePatch threw an exception: " + e.getMessage());
		}
	}

//	@Test
//	public void testReleaseVersion() {
//		try {
//
//			AppVersionManagerService appVersionManager = new AppVersionManagerServiceImpl();
//			VersionRange supportedVersionRange = new VersionRange("1.0", "2.0");
//			AppVersion newVersion = new AppVersion("1.2", 3, "Improvements", new Date(), new byte[]{0x05, 0x06, 0x07}, supportedVersionRange);
//			ReleaseStrategy strategy = new ReleaseStrategy("BetaRolloutStrategy", "Roll out to selected devices");
//
//			testUploadNewVersion();
//			appVersionManager.releaseVersion(newVersion, strategy);
//			System.out.println("Release version " + appVersionManager.getAllVersions());
//			assertTrue(appVersionManager.getAllVersions().contains(newVersion));
//		} catch (Exception e) {
//			fail("releaseVersion threw an exception: " + e.getMessage());
//		}
//	}
}

