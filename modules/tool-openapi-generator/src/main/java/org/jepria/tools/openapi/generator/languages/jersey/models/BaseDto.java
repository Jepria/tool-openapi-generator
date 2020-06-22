package org.jepria.tools.openapi.generator.languages.jersey.models;

import java.io.IOException;

public interface BaseDto {

  String fillTemplate() throws IOException;

  void setTemplate(String template);

  public String getGeneratedCode();
}
