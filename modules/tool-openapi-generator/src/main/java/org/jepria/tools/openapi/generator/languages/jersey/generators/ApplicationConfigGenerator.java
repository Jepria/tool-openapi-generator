package org.jepria.tools.openapi.generator.languages.jersey.generators;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.ApplicationConfigModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.BaseJaxrsDto;

public class ApplicationConfigGenerator extends DefaultGenerator {
  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/main/rest/jersey/ApplicationConfig.mustache";

  public ApplicationConfigGenerator(OpenAPI openAPI) {
    setOpenAPI(openAPI);
    setTemplateFileName(TEMPLATE_FILE_NAME);
    this.setFileExtension(".java");
  }

  public ApplicationConfigGenerator(String specLocation) {
    this( new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    ApplicationConfigModel dto = new ApplicationConfigModel();
    dto.setMainPackage(this.getMainPackage());

    List<BaseJaxrsDto> adapters = BaseJaxrsDto.getFromSpec(this.getOpenAPI());
    for (BaseJaxrsDto adapter : adapters) {
      adapter.setApiPackage(this.getMainPackage() + "." + adapter.getClassName().toLowerCase() + ".rest");
    }
    dto.setAdapters(adapters);

    try {
      map.put("ApplicationConfig", this.fillTemplate(dto));
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.setValues(map);

  }
}
