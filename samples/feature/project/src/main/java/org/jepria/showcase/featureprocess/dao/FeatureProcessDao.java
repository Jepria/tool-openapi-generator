package org.jepria.showcase.featureprocess.dao;

import org.jepria.showcase.featureprocess.dto.*;

import org.jepria.server.data.Dao;
import org.jepria.server.data.OptionDto;

import java.util.List;

public interface FeatureProcessDao extends Dao {
  List<FeatureProcessDto> findFeatureProcess(Integer featureId);
}