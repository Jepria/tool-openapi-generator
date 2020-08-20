package org.jepria.tools.openapi.generator.languages.jersey.models.entity.dto;

import static org.jepria.tools.openapi.generator.utils.SchemaUtils.refToName;

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

public class DtoModel {

  private String modelPackage;
  private String className;
  List<DtoField> fields = new ArrayList<>();

  public void setFields(List<DtoField> fields) {
    this.fields = fields;
  }

  public static List<DtoModel> getFromSpec(OpenAPI spec) {
    List<DtoModel> dtos = new ArrayList<>();

    Map<String, Schema> schemas = spec.getComponents().getSchemas();
    for (String className : schemas.keySet()) {
      if (isSystem(className)) {
        continue;
      }
      DtoModel dto = new DtoModel();

      Schema schema = schemas.get(className);

      List<DtoField> fields = new ArrayList<>();

      if (schema instanceof ArraySchema) {
        DtoField dtoField = new DtoField("list", "List<" +  refToName(((ArraySchema) schema).getItems().get$ref()) + ">");
        fields.add(dtoField);
      } else if (null != schema.getProperties()) {
        for (Object fieldName : schema.getProperties().keySet()) {
          DtoField dtoField = null;
          dtoField = new DtoField(fieldName.toString(), getTypeNameFromSchema((Schema) schema.getProperties().get(fieldName)));

          if (null != dtoField) {
            fields.add(dtoField);
          }
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

  public void setModelPackage(String modelPackage) {
    this.modelPackage = modelPackage;
  }


  private static boolean isSystem(String className) {
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

  static String getTypeNameFromSchema(Schema schema){
    String result = null;
    if (schema instanceof StringSchema) {
      result = "String";
    } else if (schema instanceof IntegerSchema) {
      result = "Integer";
    } else if (schema instanceof DateSchema) {
      result = "Date";
    } else if (schema instanceof DateTimeSchema) {
      result = "Date";
    } else if (schema instanceof ObjectSchema) {
      result = "Object";
    } else if (schema instanceof ArraySchema) {
      if (null != ((ArraySchema) schema).getItems().get$ref()) {
        result = "List<" + refToName(schema.get$ref()) + ">";
      } else if (null != ((ArraySchema) schema).getItems().getType()){
        result = "List<" + refToName(getTypeNameFromSchema(((ArraySchema) schema).getItems())) + ">";
      }
    } else {
      result = refToName(schema.get$ref());
    }
    return result;
  }

}
