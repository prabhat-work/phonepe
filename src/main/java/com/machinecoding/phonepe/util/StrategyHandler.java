package com.machinecoding.phonepe.util;

import com.machinecoding.phonepe.models.AppVersion;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.service.ReleaseStrategyService;

public class StrategyHandler {
  public ReleaseStrategyService releaseStrategyService;
  public ReleaseStrategy releaseStrategy;

  public StrategyHandler(ReleaseStrategyService releaseStrategy, ReleaseStrategy releaseStrategymodel){
    this.releaseStrategyService = releaseStrategy;
    this.releaseStrategy = releaseStrategymodel;
  }

  public String releaseVersionWithStrategy(AppVersion version){
    releaseStrategyService.doRelease(releaseStrategy, version);
    System.out.println("Release version [better design] using Strategy " + releaseStrategy.toString());
    return null;
  }

}
