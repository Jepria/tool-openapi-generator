package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.rest.BaseJaxrsDto;

public class JaxrsAdapterGenerator extends DefaultGenerator {

  private final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/rest/JaxrsAdapter.mustache";

  public JaxrsAdapterGenerator(OpenAPI openAPI) {
    this.setOpenAPI(openAPI);
    setTemplateFileName(TEMPLATE_FILE_NAME);
  }

  public JaxrsAdapterGenerator(String specLocation) {
    this( new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<BaseJaxrsDto> list = BaseJaxrsDto.getFromSpec(this.getOpenAPI());

    if (null == this.getMainPackage()) {
      this.setMainPackage("com.technology.jep.jepriashowcase");
    }

    for (BaseJaxrsDto dto : list) {
      dto.setModelPackage(this.getMainPackage() + ".dto");
      dto.setMainPackage(this.getMainPackage());
      dto.setApiPackage(this.getMainPackage() + "." + dto.getClassName().toLowerCase() + ".rest");
      try {
        map.put(dto.getClassName() + "JaxrsAdapter", this.fillTemplate(dto));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setValues(map);
  }
}
