package org.jepria.tools.codegen.openapi.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.codegen.openapi.DefaultGenerator;
import org.jepria.tools.codegen.openapi.languages.jersey.model.ModelDto;

public class DtoGenerator extends DefaultGenerator {

  public DtoGenerator(OpenAPI spec) {
    super(spec);
    setTemplateFileName("/mustache-templates/dto.mustache");
  }

  public DtoGenerator(String specLocation) {
    super(specLocation);
    setTemplateFileName("/mustache-templates/dto.mustache");
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<ModelDto> list = ModelDto.getFromSpec(this.getOpenAPI());

    for (ModelDto dto : list) {
      try {
        map.put(dto.getClassName(), this.fillTemplate(dto));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setValues(map);

  }

}
