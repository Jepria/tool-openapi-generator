package org.jepria.showcase.featureprocess;

import org.jepria.showcase.featureprocess.dto.FeatureProcessCreateDto;
import org.jepria.showcase.featureprocess.dto.FeatureProcessDto;
import org.jepria.showcase.featureprocess.dto.FeatureProcessSearchDto;
import org.jepria.showcase.featureprocess.dto.FeatureProcessUpdateDto;

import org.jepria.server.data.RecordDefinitionDtoImpl;

// TODO скорее всего, имеет смысл по-прежнему поддерживать синглтон этого класса, чтобы не выполнять тяжеловесную инициализацию почём зря
public class FeatureProcessRecordDefinition extends RecordDefinitionDtoImpl {

  public FeatureProcessRecordDefinition() {
    super(FeatureProcessCreateDto.class,
          FeatureProcessDto.class,
          FeatureProcessSearchDto.class,
          FeatureProcessUpdateDto.class
    );
  }

}
