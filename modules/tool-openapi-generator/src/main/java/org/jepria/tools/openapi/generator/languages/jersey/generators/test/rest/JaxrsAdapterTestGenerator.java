package org.jepria.tools.openapi.generator.languages.jersey.generators.test.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.JaxrsAdapterModel;

public class JaxrsAdapterTestGenerator extends DefaultGenerator {

  public JaxrsAdapterTestGenerator(OpenAPI spec) {
    this.setOpenAPI(spec);
    setTemplateFileName("/mustache-templates/service-rest/src/test/java/JaxrsAdapterTest.mustache");
  }

  public JaxrsAdapterTestGenerator(String specLocation) {
    this(new OpenAPIV3Parser().read(specLocation));
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    List<JaxrsAdapterModel> list = JaxrsAdapterModel.getFromSpec(this.getOpenAPI(), "com.technology.jep.jepriashowcase");

    for (JaxrsAdapterModel dto : list) {
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
