package org.jepria.showcase.featureprocess;

import org.jepria.showcase.featureprocess.dto.*;

import org.jepria.server.data.OptionDto;
import org.jepria.server.service.security.Credential;

import java.util.List;

public class FeatureProcessService {

  public List<FeatureProcessDto> findFeatureProcess(Integer featureId){
    return FeatureProcessServerFactory.getInstance().getDao().findFeatureProcess(featureId);
  }

}
