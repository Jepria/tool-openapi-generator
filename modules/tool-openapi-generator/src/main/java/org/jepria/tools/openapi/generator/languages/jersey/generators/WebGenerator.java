package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.web.WebDto;

public class WebGenerator extends DefaultGenerator {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/src/main/webapp/WEB-INF/web.mustache";

  public WebGenerator() {
    setTemplateFileName(TEMPLATE_FILE_NAME);
    this.setFileExtension(".xml");
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    WebDto webDto = new WebDto();
    webDto.setMainPackage(this.getMainPackage());

    try {
      map.put("web", this.fillTemplate(webDto));
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.setValues(map);

  }
}
