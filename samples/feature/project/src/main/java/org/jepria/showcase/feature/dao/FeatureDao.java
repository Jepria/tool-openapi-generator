package org.jepria.showcase.feature.dao;

import org.jepria.showcase.feature.dto.*;

import org.jepria.server.data.Dao;
import org.jepria.server.data.OptionDto;

import java.util.List;

public interface FeatureDao extends Dao {
  void setFeatureResponsible(Integer featureId, Integer responsibleId);
  List<OptionDto<Integer>> getFeatureOperator();
  List<OptionDto<String>> getFeatureStatus();
}