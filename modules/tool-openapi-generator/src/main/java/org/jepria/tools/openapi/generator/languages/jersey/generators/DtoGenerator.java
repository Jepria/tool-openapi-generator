package org.jepria.tools.openapi.generator.languages.jersey.generators;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.model.ModelDto;

public class DtoGenerator extends DefaultGenerator {

  public DtoGenerator(OpenAPI spec) {
    this.setOpenAPI(spec);
    setTemplateFileName("/mustache-templates/dto.mustache");
  }

  public DtoGenerator(String specLocation) {
    this( new OpenAPIV3Parser().read(specLocation));
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
