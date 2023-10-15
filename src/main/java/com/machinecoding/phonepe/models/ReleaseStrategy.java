package com.machinecoding.phonepe.models;

import java.util.ArrayList;
import java.util.List;

public class ReleaseStrategy {
  private String name;
  private String description;
  private List<Device> selectedDevices;

  public ReleaseStrategy(String name, String description) {
    this.name = name;
    this.description = description;
    this.selectedDevices = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "ReleaseStrategy{" +
        "name='" + name + '\'' +
        ", description='" + description +
        '}';
  }

  public List<Device> getSelectedDevices() {
    return selectedDevices;
  }

  public void addDevice(Device device) {
    selectedDevices.add(device);
  }

  public void setName(ReleaseStrategy releaseStrategy) {
    this.name = name;
  }
}

