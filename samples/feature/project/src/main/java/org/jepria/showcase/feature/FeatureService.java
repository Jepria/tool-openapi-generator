package org.jepria.showcase.feature;

import org.jepria.showcase.feature.dto.*;

import org.jepria.server.data.OptionDto;
import org.jepria.server.service.security.Credential;

import java.util.List;

public class FeatureService {

  public void setFeatureResponsible(Integer featureId, Integer responsibleId) {
    FeatureServerFactory.getInstance().getDao().setFeatureResponsible(featureId, responsibleId);
  }
  public List<OptionDto<Integer>> getFeatureOperator(){
    return FeatureServerFactory.getInstance().getDao().getFeatureOperator();
  }
  public List<OptionDto<String>> getFeatureStatus(){
    return FeatureServerFactory.getInstance().getDao().getFeatureStatus();
  }

}
