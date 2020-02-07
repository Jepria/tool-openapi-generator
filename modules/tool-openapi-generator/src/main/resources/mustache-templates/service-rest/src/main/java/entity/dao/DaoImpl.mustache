package com.technology.jep.jepriashowcase.feature.dao;

import com.technology.jep.jepria.server.dao.ResultSetMapper;
import com.technology.jep.jepriashowcase.feature.FeatureFieldNames;
import com.technology.jep.jepriashowcase.feature.dto.FeatureCreateDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureUpdateDto;
import org.jepria.server.data.DaoSupport;
import org.jepria.server.data.DtoUtil;
import org.jepria.server.data.OptionDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FeatureDaoImpl implements FeatureDao {


  @Override
  public List<FeatureDto> find(Object template, Integer operatorId) {

    FeatureSearchDto searchTemplate = (FeatureSearchDto) template;

    final List<FeatureDto> records;

    records = DaoSupport.getInstance().find(findSqlQuery,
            new FeatureResultSetMapper()
            , FeatureDto.class
            , searchTemplate.getFeatureId()
            , DtoUtil.like(searchTemplate.getFeatureNameTemplate())
            , DtoUtil.like(searchTemplate.getFeatureNameEnTemplate())
            , null
            , null
            , searchTemplate.getDateInsFrom()
            , searchTemplate.getDateInsTo()
            , searchTemplate.getAuthorId()
            , searchTemplate.getResponsibleId()
            , DtoUtil.convertList(searchTemplate.getStatusCodeList())
            , searchTemplate.getMaxRowCount()
            , operatorId);

    // download descriptions for each record
    if (records != null) {
      records.forEach(record -> {
        String description = downloadDescription(record.getFeatureId());
        record.setDescription(description);
      });
    }

    return records;
  }

  @Override
  public List<FeatureDto> findByPrimaryKey(Map<String, ?> primaryKeyMap, Integer operatorId) {

    final List<FeatureDto> records;

    records = DaoSupport.getInstance().find(findSqlQuery,
            new FeatureResultSetMapper()
            , FeatureDto.class
            , primaryKeyMap.get(FeatureFieldNames.FEATURE_ID)
            , null
            , null
            , null
            , null
            , null
            , null
            , null
            , null
            , null
            , null
            , operatorId);

    // download descriptions for each record
    if (records != null) {
      records.forEach(record -> {
        String description = downloadDescription(record.getFeatureId());
        record.setDescription(description);
      });
    }

    return records;
  }

  private static final String findSqlQuery = "begin  "
          + "? := pkg_jepriashowcase.findFeature("
          + "featureid => ? "
          + ", featureName => ? "
          + ", featureNameEn => ? "
          + ", workSequenceFrom => ? "
          + ", workSequenceTo => ? "
          + ", dateInsFrom => ? "
          + ", dateInsTo => ? "
          + ", authorid => ? "
          + ", responsibleId => ? "
          + ", featureStatusCodeList => ? "
          + ", maxRowCount => ? "
          + ", operatorId => ? "
          + ");"
          + " end;";

  protected static class FeatureResultSetMapper extends ResultSetMapper<FeatureDto> {
    @Override
    public void map(ResultSet rs, FeatureDto record) throws SQLException {
      record.setFeatureId(getInteger(rs, FeatureFieldNames.FEATURE_ID));
      record.setFeatureName(rs.getString(FeatureFieldNames.FEATURE_NAME));
      record.setFeatureNameEn(rs.getString(FeatureFieldNames.FEATURE_NAME_EN));
      record.setDateIns(getDate(rs, FeatureFieldNames.DATE_INS));

      OptionDto<String> featureStatus = new OptionDto<>();
      featureStatus.setName(rs.getString(FeatureFieldNames.FEATURE_STATUS_NAME));
      featureStatus.setValue(rs.getString(FeatureFieldNames.FEATURE_STATUS_CODE));
      record.setFeatureStatus(featureStatus);

      OptionDto<Integer> author = new OptionDto<>();
      author.setName(rs.getString(FeatureFieldNames.AUTHOR_NAME));
      author.setValue(getInteger(rs, FeatureFieldNames.AUTHOR_ID));
      record.setAuthor(author);

      OptionDto<Integer> responsible = new OptionDto<>();
      responsible.setName(rs.getString(FeatureFieldNames.RESPONSIBLE_NAME));
      responsible.setValue(getInteger(rs, FeatureFieldNames.RESPONSIBLE_ID));
      record.setResponsible(responsible);
    }
  }

  @Override
  public Object create(Object record, Integer operatorId) {
    FeatureCreateDto dto = (FeatureCreateDto) record;

    String sqlQuery =
            "begin  "
                    + "? := pkg_jepriashowcase.createFeature("
                    + "featureName => ? "
                    + ", featureNameEn => ? "
                    + ", operatorId => ? "
                    + ");"
                    + " end;";

    final Integer featureId;

    featureId = DaoSupport.getInstance().create(
            sqlQuery
            , Integer.class
            , dto.getFeatureName()
            , dto.getFeatureNameEn()
            , operatorId);

    uploadDescription(featureId, dto.getDescription());

    return featureId;
  }

  protected void uploadDescription(Integer featureId, String description) {
    DaoSupport.getInstance().uploadClob("v_jrs_feature_lob", "description", FeatureFieldNames.FEATURE_ID + "=" + featureId, description);
  }

  protected String downloadDescription(Integer featureId) {
    String description = DaoSupport.getInstance().downloadClob("v_jrs_feature_lob", "description", FeatureFieldNames.FEATURE_ID + "=" + featureId);
    description = "".equals(description) ? null : description;
    return description;
  }

  @Override
  public void update(Map<String, ?> primaryKey, Object record, Integer operatorId) {

    FeatureUpdateDto dto = (FeatureUpdateDto) record;

    String sqlQuery =
            "begin "
                    + "pkg_jepriashowcase.updateFeature("
                    + "featureId => ? "
                    + ", featureName => ? "
                    + ", featureNameEn => ? "
                    + ", operatorId => ? "
                    + ");"
                    + "end;";

    final Integer featureId = (Integer)primaryKey.get(FeatureFieldNames.FEATURE_ID);

    DaoSupport.getInstance().update(sqlQuery
            , featureId
            , dto.getFeatureName()
            , dto.getFeatureNameEn()
            , operatorId);

    uploadDescription(featureId, dto.getDescription());

    setFeatureResponsible(featureId, dto.getResponsibleId(), operatorId);

  }

  @Override
  public void delete(Map<String, ?> primaryKey, Integer operatorId) {
    String sqlQuery =
            "begin "
                    + "pkg_jepriashowcase.deleteFeature("
                    + "featureId => ? "
                    + ", operatorId => ? "
                    + ");"
                    + "end;";

    DaoSupport.getInstance().delete(sqlQuery
            , primaryKey.get(FeatureFieldNames.FEATURE_ID)
            , operatorId);
  }

  @Override
  public void setFeatureResponsible(Integer featureId, Integer responsibleId, Integer operatorId) {
    String sqlQuery =
            "begin "
                    + "pkg_jepriashowcase.setFeatureResponsible("
                    + "featureId => ? "
                    + ", responsibleId => ? "
                    + ", operatorId => ? "
                    + ");"
                    + "end;";

    DaoSupport.getInstance().execute(sqlQuery
            , featureId
            , responsibleId
            , operatorId);
  }

  @Override
  public List<OptionDto<Integer>> getFeatureOperator() {
    String sqlQuery =
            " begin "
                    + " ? := pkg_jepriashowcase.findFeatureOperator("
                    + "featureOperatorId => null "
                    + ", featureOperatorName => null "
                    + ", maxRowCount => null "
                    + ", operatorId => null "
                    + ");"
                    + " end;";
    return DaoSupport.getInstance().find(sqlQuery,
            new ResultSetMapper<OptionDto<Integer>>() {
              @Override
              public void map(ResultSet rs, OptionDto<Integer> dto) throws SQLException {
                dto.setName(rs.getString(FeatureFieldNames.FEATURE_OPERATOR_NAME));
                dto.setValue(getInteger(rs, FeatureFieldNames.FEATURE_OPERATOR_ID));
              }
            },
            OptionDto.class);
  }

  @Override
  public List<OptionDto<String>> getFeatureStatus() {
    String sqlQuery =
            " begin "
                    + " ? := pkg_jepriashowcase.getFeatureStatus;"
                    + " end;";

    return DaoSupport.getInstance().find(sqlQuery,
            new ResultSetMapper<OptionDto<String>>() {
              @Override
              public void map(ResultSet rs, OptionDto<String> dto) throws SQLException {
                dto.setName(rs.getString(FeatureFieldNames.FEATURE_STATUS_NAME));
                dto.setValue(rs.getString(FeatureFieldNames.FEATURE_STATUS_CODE));
              }
            },
            OptionDto.class);
  }
}
