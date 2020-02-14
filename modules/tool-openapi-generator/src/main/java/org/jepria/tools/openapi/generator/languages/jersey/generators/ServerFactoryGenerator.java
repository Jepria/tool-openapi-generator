package org.jepria.tools.openapi.generator.languages.jersey.generators;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.jepria.tools.openapi.generator.DefaultGenerator;

public class ServerFactoryGenerator extends DefaultGenerator {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/java/entity/ServerFactory.mustache";

  public ServerFactoryGenerator(OpenAPI spec) {
    this.setOpenAPI(spec);
    setTemplateFileName(TEMPLATE_FILE_NAME);
  }

  public ServerFactoryGenerator(String specLocation) {
    this(new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {

  }
}
