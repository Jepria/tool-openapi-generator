package org.jepria.tools.openapi.generator.languages.jersey.generators.entity.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.BaseJaxrsModel;

public class JaxrsAdapterGenerator extends DefaultGenerator {

  private final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/rest/JaxrsAdapter.mustache";

  public JaxrsAdapterGenerator(OpenAPI openAPI) {
    this.setOpenAPI(openAPI);
    setTemplateFileName(TEMPLATE_FILE_NAME);
    setBaseName("JaxrsAdapter");
  }

  public JaxrsAdapterGenerator(String specLocation) {
    this( new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<BaseJaxrsModel> list = BaseJaxrsModel.getFromSpec(this.getOpenAPI());

    if (null == this.getMainPackage()) {
      this.setMainPackage("com.example");
    }

    for (BaseJaxrsModel dto : list) {
      dto.setModelPackage(this.getMainPackage() + "." + dto.getClassName().toLowerCase() + ".dto");
      dto.setMainPackage(this.getMainPackage() + "." + dto.getClassName().toLowerCase());
      dto.setApiPackage(this.getMainPackage() + "." + dto.getClassName().toLowerCase() + ".rest");

      try {
        map.put(dto.getClassName(), this.fillTemplate(dto));
        dto.setTemplate(this.getTemplateFileName());
        dto.fillTemplate();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    this.setDtos(list);
    this.setValues(map);
  }

  @Override
  public void saveToFiles(String rootLocation) throws IOException {

    super.saveToFiles(rootLocation);
  }
}
