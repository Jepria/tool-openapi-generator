package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.rest.BaseJaxrsDto;

public class JaxrsAdapterTestGenerator extends DefaultGenerator {

  public JaxrsAdapterTestGenerator(OpenAPI openAPI) {
    super(openAPI);
    setTemplateFileName("/mustache-templates/api-test.mustache");
  }

  public JaxrsAdapterTestGenerator(String specLocation) {
    super(specLocation);
    setTemplateFileName("/mustache-templates/api-test.mustache");
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<BaseJaxrsDto> list = BaseJaxrsDto.getFromSpec(this.getOpenAPI());

    for (BaseJaxrsDto dto : list) {
      dto.setModelPackage("com.technology.jep.jepriashowcase"); //TODO create parameters
      dto.setApiPackage("com.technology.jep.jepriashowcase" + dto.getApiPackage());
      try {
        map.put(dto.getClassName() + "JaxrsAdapterTest", this.fillTemplate(dto));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setValues(map);
  }
}
