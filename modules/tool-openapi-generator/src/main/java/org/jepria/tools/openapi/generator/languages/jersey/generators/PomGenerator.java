package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jepria.tools.openapi.generator.DefaultGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.PomDto;

public class PomGenerator extends DefaultGenerator {

  private static final String TEMPLATE_FILE_NAME = "/mustache-templates/service-rest/pom.mustache";

  private String applicationName;

  public PomGenerator() {
    setTemplateFileName(TEMPLATE_FILE_NAME);
    this.setFileExtension(".xml");
  }

  @Override
  public void create() {
    Map<String, String> map = new HashMap<>();

    PomDto dto = new PomDto();
    dto.setBasePackage(this.getMainPackage());
    dto.setApplicationName(this.applicationName);

    try {
      map.put("pom", this.fillTemplate(dto));
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.setValues(map);
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }
}
