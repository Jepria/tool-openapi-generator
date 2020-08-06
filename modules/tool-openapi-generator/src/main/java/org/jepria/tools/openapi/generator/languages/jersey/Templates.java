package org.jepria.tools.openapi.generator.languages.jersey;

public class Templates {

  public static final String DAO_TEMPLATE                = "/mustache-templates/service-rest/src/main/java/entity/dao/Dao.mustache";
  public static final String DAO_IMPL_TEMPLATE           = "/mustache-templates/service-rest/src/main/java/entity/dao/DaoImpl.mustache";
  public static final String DTO_TEMPLATE                = "/mustache-templates/service-rest/src/main/java/entity/dto/Dto.mustache";
  public static final String JAXRS_ADAPTER_TEMPLATE      = "/mustache-templates/service-rest/src/main/java/entity/rest/JaxrsAdapter.mustache";
  public static final String SERVICE_TEMPLATE            = "/mustache-templates/service-rest/src/main/java/entity/Service.mustache";
  public static final String SERVER_FACTORY_TEMPLATE     = "/mustache-templates/service-rest/src/main/java/entity/ServerFactory.mustache";
  public static final String RECORD_DEFINITION_TEMPLATE  = "/mustache-templates/service-rest/src/main/java/entity/RecordDefinition.mustache";
  public static final String FIELD_NAMES_TEMPLATE        = "/mustache-templates/service-rest/src/main/java/entity/FieldNames.mustache";
  public static final String APPLICATION_CONFIG_TEMPLATE = "/mustache-templates/service-rest/src/main/java/main/rest/jersey/ApplicationConfig.mustache";
  public static final String POM_TEMPLATE                = "/mustache-templates/service-rest/pom.mustache";
  public static final String WEB_TEMPLATE                = "/mustache-templates/service-rest/src/main/webapp/WEB-INF/web.mustache";
  public static final String CRUD_TEST_TEMPLATE          = "/mustache-templates/service-rest/src/test/java/JaxrsAdapterCrudTest.mustache";
}
