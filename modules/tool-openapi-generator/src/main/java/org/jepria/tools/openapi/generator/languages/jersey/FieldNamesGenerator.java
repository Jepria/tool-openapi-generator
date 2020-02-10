package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.model.ModelDto;

public class FieldNamesGenerator extends DefaultGenerator {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/FieldNames.mustache";

  public FieldNamesGenerator(OpenAPI spec) {
    this.setOpenAPI(spec);
    setTemplateFileName(TEMPLATE_FILE_NAME);
  }

  public FieldNamesGenerator(String specLocation) {
    this(new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<ModelDto> list = ModelDto.getFromSpec(this.getOpenAPI());

    for (ModelDto dto : list) {
      try {
        map.put(dto.getClassName() + "FieldNames", this.fillTemplate(dto));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setValues(map);
  }
}
