package com.machinecoding.phonepe.models;

public class Device {
  private String deviceId;
  private String deviceName;
  private String osVersion;
  private String hardwareInfo;
  private String version; // Add version property

  // Constructors, getters, and setters for the above properties

  public Device(String deviceId, String deviceName, String osVersion, String hardwareInfo, String version) {
    this.deviceId = deviceId;
    this.deviceName = deviceName;
    this.osVersion = osVersion;
    this.hardwareInfo = hardwareInfo;
    this.version = version; // Initialize the version property
  }

  // Getter and setter methods for the properties

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public String getHardwareInfo() {
    return hardwareInfo;
  }

  public void setHardwareInfo(String hardwareInfo) {
    this.hardwareInfo = hardwareInfo;
  }

  public String getVersion() {
    return version; // Getter for the version property
  }

  public void setVersion(String version) {
    this.version = version; // Setter for the version property
  }
}
