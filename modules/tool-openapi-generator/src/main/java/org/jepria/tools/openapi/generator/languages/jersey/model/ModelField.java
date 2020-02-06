package org.jepria.tools.openapi.generator.languages.jersey.model;

import static org.jepria.tools.openapi.generator.utils.StringUtils.camelize;

class ModelField {
  private String field;
  private String type;
  private String name;
  private String Name;

  public ModelField(String name, String type) {
    this.field = name;
    this.type = type;
    this.name = name;
    this.Name = camelize(name, false);
  }
}
