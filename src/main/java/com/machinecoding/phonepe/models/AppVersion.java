package com.machinecoding.phonepe.models;

import java.util.Date;

public class AppVersion {

  private String versionName;
  private int versionCode;
  private String changelog;
  private Date releaseDate;
  private byte[] binaryData;
  private VersionRange supportedVersionRange; // supported versions

  public AppVersion(String versionName, int versionCode, String changelog, Date releaseDate, byte[] binaryData, VersionRange supportedVersionRange) {
    this.versionName = versionName;
    this.versionCode = versionCode;
    this.changelog = changelog;
    this.releaseDate = releaseDate;
    this.binaryData = binaryData;
    this.supportedVersionRange = supportedVersionRange;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }

  public String getChangelog() {
    return changelog;
  }

  public void setChangelog(String changelog) {
    this.changelog = changelog;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public byte[] getBinaryData() {
    return binaryData;
  }

  public void setBinaryData(byte[] binaryData) {
    this.binaryData = binaryData;
  }

  public VersionRange getSupportedVersionRange() {
    return supportedVersionRange;
  }

  public void setSupportedVersionRange(VersionRange supportedVersionRange) {
    this.supportedVersionRange = supportedVersionRange;
  }
}
