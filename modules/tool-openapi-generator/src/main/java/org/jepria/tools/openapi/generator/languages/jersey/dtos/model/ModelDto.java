package org.jepria.tools.openapi.generator.languages.jersey.dtos.model;

import static org.jepria.tools.openapi.generator.utils.SchemaUtils.refToName;
import static org.jepria.tools.openapi.generator.utils.StringUtils.sanitizeName;
import static org.jepria.tools.openapi.generator.utils.StringUtils.underscore;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelDto {

  private String modelPackage;
  private String className;

  public void setFields(List<ModelField> fields) {
    this.fields = fields;
  }

  List<ModelField> fields = new ArrayList<>();

  public static List<ModelDto> getFromSpec(OpenAPI spec) {
    List<ModelDto> dtos = new ArrayList<>();

    Map<String, Schema> schemas = spec.getComponents().getSchemas();
    for (String className : schemas.keySet()) {
      if (isSystem(className)){
        continue;
      }
      ModelDto dto = new ModelDto();

      Schema schema = schemas.get(className);

      List<ModelField> fields = new ArrayList<>();
      for (Object fieldName : schema.getProperties().keySet()) {
        ModelField dtoField = null;
        if (schema.getProperties().get(fieldName) instanceof StringSchema) {
          sanitizeName(fieldName.toString());
          underscore(fieldName.toString());
          dtoField = new ModelField(fieldName.toString(), "String");
        } else if (schema.getProperties().get(fieldName) instanceof IntegerSchema) {
          dtoField = new ModelField(fieldName.toString(), "Integer");
        } else if (schema.getProperties().get(fieldName) instanceof DateSchema) {
          dtoField = new ModelField(fieldName.toString(), "Date");
        } else if (schema.getProperties().get(fieldName) instanceof DateTimeSchema) {
          dtoField = new ModelField(fieldName.toString(), "DateTime");
        } else if (schema.getProperties().get(fieldName) instanceof ObjectSchema) {
          ((ObjectSchema) schema.getProperties().get(fieldName)).getType();
          dtoField = new ModelField(fieldName.toString(), "Object");
          ((ObjectSchema) schema.getProperties().get(fieldName)).get$ref();
        } else if (schema.getProperties().get(fieldName) instanceof ArraySchema) {
          ((ArraySchema) schema.getProperties().get(fieldName)).getItems();
          dtoField = new ModelField(fieldName.toString(), "Array");
        } else {
          dtoField = new ModelField(fieldName.toString(), refToName(((Schema) schema.getProperties().get(fieldName)).get$ref()));
        }

        if (null != dtoField) {
          fields.add(dtoField);
        }
      }
      dto.setFields(fields);
      dto.setClassName(className);
      dtos.add(dto);
    }

    return dtos;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

  private static boolean isSystem(String className){
    boolean result = false;

    List<String> systemList = new ArrayList<>();
    systemList.add("OptionDto");
    systemList.add("OptionDtoString");
    systemList.add("OptionDtoInteger");
    systemList.add("SearchRequestDto");
    systemList.add("ColumnSortConfigurationDto");

    for (String name : systemList) {
      if (className.startsWith(name)) {
        result = true;
        break;
      }
    }

    return result;
  }

}
