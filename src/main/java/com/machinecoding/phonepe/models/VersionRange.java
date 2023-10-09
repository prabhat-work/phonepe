package com.machinecoding.phonepe.models;

import lombok.Getter;

@Getter
public class VersionRange {
    private String minVersion;
    private String maxVersion;

    public VersionRange(String minVersion, String maxVersion) {
      this.minVersion = minVersion;
      this.maxVersion = maxVersion;
    }

  public void setMinVersion(String minVersion) {
      this.minVersion = minVersion;
    }

  public void setMaxVersion(String maxVersion) {
      this.maxVersion = maxVersion;
    }
  }

