package org.jepria.tools.openapi.generator.languages.jersey.models.entity.dto;

import static org.jepria.tools.openapi.generator.utils.StringUtils.camelize;

class DtoField {
  private String field;
  private String type;
  private String name;
  private String Name;

  public DtoField(String name, String type) {
    this.field = name;
    this.type = type;
    this.name = name;
    this.Name = camelize(name, false);
  }
}
