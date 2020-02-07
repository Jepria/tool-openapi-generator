package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.rest.BaseJaxrsDto;

public class JaxrsAdapterGenerator extends DefaultGenerator {

  private static final String TEMPLATE_FILE_NAME = "service-rest/src/main/java/entity/";

  public JaxrsAdapterGenerator(OpenAPI openAPI) {
    super(openAPI);
    setTemplateFileName("/mustache-templates/api.mustache");
  }

  public JaxrsAdapterGenerator(String specLocation) {
    super(specLocation);
    setTemplateFileName("/mustache-templates/api.mustache");
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<BaseJaxrsDto> list = BaseJaxrsDto.getFromSpec(this.getOpenAPI());

    for (BaseJaxrsDto dto : list) {
      dto.setModelPackage("com.technology.jep.jepriashowcase");
      dto.setApiPackage("com.technology.jep.jepriashowcase" + dto.getApiPackage());
      try {
        map.put(dto.getClassName() + "JaxrsAdapter", this.fillTemplate(dto));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setValues(map);
  }
}
