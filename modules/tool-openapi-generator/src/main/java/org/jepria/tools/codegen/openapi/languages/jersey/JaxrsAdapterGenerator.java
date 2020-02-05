package org.jepria.tools.codegen.openapi.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.codegen.openapi.DefaultGenerator;
import org.jepria.tools.codegen.openapi.languages.jersey.rest.BaseJaxrsDto;

public class JaxrsAdapterGenerator extends DefaultGenerator {

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
