package com.machinecoding.phonepe.util;

import com.machinecoding.phonepe.exceptions.NoValidStategyException;
import com.machinecoding.phonepe.models.ReleaseStrategy;
import com.machinecoding.phonepe.service.Implemenation.BetaRolloutStrategyServiceImpl;
import com.machinecoding.phonepe.service.Implemenation.PercentageRolloutStrategyServiceImpl;
import com.machinecoding.phonepe.service.ReleaseStrategyService;

public class ReleaseStrategyFactory {

  public ReleaseStrategyService getReleaseStrategyObject(String strategyName)
      throws NoValidStategyException {

    if(strategyName.equalsIgnoreCase("BetaRolloutStrategy")){
      return new BetaRolloutStrategyServiceImpl();
    }
    else if(strategyName.equalsIgnoreCase("PercentageRolloutStrategy")){
      return new PercentageRolloutStrategyServiceImpl();
    }
    throw new NoValidStategyException("No valid strategy found");
  }

}
