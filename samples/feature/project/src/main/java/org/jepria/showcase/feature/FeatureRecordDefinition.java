package org.jepria.showcase.feature;

import org.jepria.showcase.feature.dto.FeatureCreateDto;
import org.jepria.showcase.feature.dto.FeatureDto;
import org.jepria.showcase.feature.dto.FeatureSearchDto;
import org.jepria.showcase.feature.dto.FeatureUpdateDto;

import org.jepria.server.data.RecordDefinitionDtoImpl;

// TODO скорее всего, имеет смысл по-прежнему поддерживать синглтон этого класса, чтобы не выполнять тяжеловесную инициализацию почём зря
public class FeatureRecordDefinition extends RecordDefinitionDtoImpl {

  public FeatureRecordDefinition() {
    super(FeatureCreateDto.class,
          FeatureDto.class,
          FeatureSearchDto.class,
          FeatureUpdateDto.class
    );
  }

}
