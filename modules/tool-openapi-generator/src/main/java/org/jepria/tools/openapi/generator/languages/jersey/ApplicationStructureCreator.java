package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.jepria.tools.openapi.generator.GeneratorImpl;
import org.jepria.tools.openapi.generator.languages.jersey.generators.test.rest.JaxrsAdapterTestGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.ApplicationConfigModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.PomModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.WebModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.RecordDefinitionModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.ServerFactoryModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.ServiceImplModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.ServiceModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.dao.DaoImplModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.dao.DaoModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.dto.DtoModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.JaxrsAdapterModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.OtherJaxrsOperation;
import org.jepria.tools.openapi.generator.languages.jersey.models.test.JaxrsCrudTestModel;

import static org.jepria.tools.openapi.generator.languages.jersey.Templates.*;

public class ApplicationStructureCreator {

  private String apiSpecFolder = "api-spec";
  private String outputFolderName;
  private String basePackage   = "";

  List<String> folders = new ArrayList<>();


  public ApplicationStructureCreator(String outputFolderName) {
    this.outputFolderName = outputFolderName;
    folders.add(outputFolderName);
    folders.add(outputFolderName + "/" + apiSpecFolder);
  }

  public void create(OpenAPI openAPI) throws IOException {
    if (null == openAPI) {
      throw new IllegalArgumentException("openAPI argument cannot be null");
    }

    File file = new File(outputFolderName);

    Boolean check = file.mkdir();
    createEntities(openAPI, this.outputFolderName);
//    createAdapterTests(openAPI, this.outputFolderName + "\\src\\test\\java\\");
    createWeb(this.outputFolderName + "\\src\\main\\webapp\\WEB-INF\\");
    createApplicationConfig(openAPI, this.outputFolderName + "\\src\\main\\java\\");
    createPom(this.getBasePackage(), this.outputFolderName + "\\");
  }

  public void create(String spec) throws IOException {
    OpenAPI openAPI = null;
    try {
      SwaggerParseResult parseResult = new OpenAPIV3Parser().readContents(spec);
      openAPI = parseResult.getOpenAPI();
    } catch (Exception e) {
      System.err.println("Cannot parse spec!");
      return;
    }
    create(openAPI);
  }

  private void createEntities(OpenAPI spec, String outputFolder) throws IOException {
    String srcFolder  = outputFolder + "\\src\\main\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";
    String testFolder = outputFolder + "\\src\\test\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";

    List<JaxrsAdapterModel> models = JaxrsAdapterModel.getFromSpec(spec, this.getBasePackage());

    String fileName = "JaxrsAdapter.java";

    GeneratorImpl generator = new GeneratorImpl();

    for (JaxrsAdapterModel model : models) {
      String className     = model.getEntityName();
      String entityFolder  = srcFolder + className.toLowerCase() + "\\";
      String entityPackage = this.getBasePackage() + "." + className.toLowerCase();

      generator.generate(model, entityFolder + "rest\\", model.getEntityName() + fileName, JAXRS_ADAPTER_TEMPLATE);

      createServerFactory(entityPackage, className, entityFolder);
      createService(entityPackage, className, model.getOperations(), entityFolder);
      createServiceImpl(entityPackage, className, model.getOperations(), entityFolder);
      createDao(entityPackage, className, model.getOperations(), entityFolder + "dao\\");
      createDaoImpl(entityPackage, className, model.getOperations(), entityFolder + "dao\\");
      createDtos(spec, entityPackage, className, entityFolder + "dto\\");
      createRecordDefinition(entityPackage, className, entityFolder);
      createCrudTests(entityPackage, className, testFolder + className.toLowerCase() + "\\");
      //TODO: FieldNames ???
    }

  }

  private void createAdapterTests(OpenAPI spec, String outputFolder) throws IOException {
    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createDtos(OpenAPI spec, String entityPackage, String entityName, String outputFolder) throws IOException {

    List<DtoModel> models = DtoModel.getFromSpec(spec);

    String fileExt = ".java";

    GeneratorImpl generator = new GeneratorImpl();
    for (DtoModel model : models) {
      if (model.getEntityName().contains(entityName)) { // filtered model only for current entity // TODO: needed bugfix
        model.setModelPackage(entityPackage);
        generator.generate(model, outputFolder, model.getEntityName() + fileExt, DTO_TEMPLATE);
      }
    }

  }

  private void createWeb(String outputFolder) throws IOException {
    WebModel model = new WebModel();

    model.setMainPackage(this.getBasePackage());

    String fileName = "web.xml";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(model, outputFolder, fileName, WEB_TEMPLATE);
  }

  private void createApplicationConfig(OpenAPI spec, String outputFolder) throws IOException {
    outputFolder = outputFolder + this.getBasePackage().replace(".", "\\") + "\\" + "main\\rest\\";

    ApplicationConfigModel model = ApplicationConfigModel.getFromSpec(spec, this.getBasePackage());

    String fileName = "ApplicationConfig.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(model, outputFolder, fileName, APPLICATION_CONFIG_TEMPLATE);
  }

  private void createServerFactory(String apiPackage, String entityName, String outputFolder) throws IOException {
    ServerFactoryModel dto = new ServerFactoryModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);

    String fileName = entityName + "ServerFactory.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, SERVER_FACTORY_TEMPLATE);
  }

  private void createService(String apiPackage, String entityName, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    ServiceModel dto = new ServiceModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = entityName + "Service.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, SERVICE_TEMPLATE);
  }

  private void createServiceImpl(String apiPackage, String entityName, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    ServiceImplModel dto = new ServiceImplModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = entityName + "ServiceImpl.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, SERVICEIMPL_TEMPLATE);
  }

  private void createDao(String apiPackage, String entityName, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoModel dto = new DaoModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = entityName + "Dao.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, DAO_TEMPLATE);
  }

  private void createDaoImpl(String apiPackage, String entityName, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoImplModel dto = new DaoImplModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = entityName + "DaoImpl.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, DAO_IMPL_TEMPLATE);
  }

  private void createRecordDefinition(String apiPackage, String entityName, String outputFolder) throws IOException {
    RecordDefinitionModel dto = new RecordDefinitionModel();

    dto.setApiPackage(apiPackage);
    dto.setEntityName(entityName);

    String fileName = entityName + "RecordDefinition.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, RECORD_DEFINITION_TEMPLATE);
  }

  private void createPom(String basePackage, String outputFolder) throws IOException {
    PomModel model = new PomModel();

    model.setBasePackage(basePackage);
    model.setApplicationName("service-rest-name"); //TODO set parameter

    String fileName = "pom.xml";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(model, outputFolder, fileName, POM_TEMPLATE);
  }

  private void createCrudTests(String entityPackage, String entityName, String outputFolder) throws IOException {
    JaxrsCrudTestModel dto = new JaxrsCrudTestModel();
    dto.setApiPackage(entityPackage);
    dto.setEntityName(entityName);
    dto.setModelPackage(entityPackage + ".dto");

    String fileName = entityName + "JaxrsAdapterCrudTestIT.java";

    GeneratorImpl generator = new GeneratorImpl();

    generator.generate(dto, outputFolder, fileName, CRUD_TEST_TEMPLATE);
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  public String getBasePackage() {
    return basePackage;
  }

}
