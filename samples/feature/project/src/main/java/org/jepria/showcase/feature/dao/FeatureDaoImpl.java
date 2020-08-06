package org.jepria.showcase.feature.dao;

import org.jepria.showcase.feature.dto.*;

import java.util.List;
import java.util.Map;
import org.jepria.server.data.OptionDto;

public class FeatureDaoImpl implements FeatureDao {

  @Override
  public void setFeatureResponsible(Integer featureId, Integer responsibleId) {

  }

  @Override
  public List<OptionDto<Integer>> getFeatureOperator() {
    return null;
  }

  @Override
  public List<OptionDto<String>> getFeatureStatus() {
    return null;
  }

  @Override
  public List<?> find(Object o, Integer integer) {
    return null;
  }

  @Override
  public List<?> findByPrimaryKey(Map<String, ?> map, Integer integer) {
    return null;
  }

  @Override
  public Object create(Object o, Integer integer) {
    return null;
  }

  @Override
  public void update(Map<String, ?> map, Object o, Integer integer) {

  }

  @Override
  public void delete(Map<String, ?> map, Integer integer) {

  }
}
