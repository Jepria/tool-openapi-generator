package org.jepria.tools.openapi.generator.languages.jersey;

import static org.jepria.tools.openapi.generator.languages.jersey.Templates.CRUD_TEST_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.DAO_IMPL_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.DAO_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.POM_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.RECORD_DEFINITION_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.SERVER_FACTORY_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.SERVICE_TEMPLATE;
import static org.jepria.tools.openapi.generator.languages.jersey.Templates.WEB_TEMPLATE;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jepria.tools.openapi.generator.GeneratorImpl;
import org.jepria.tools.openapi.generator.languages.jersey.generators.ApplicationConfigGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.dto.DtoGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.rest.JaxrsAdapterGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.test.rest.JaxrsAdapterTestGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.models.BaseDtoImpl;
import org.jepria.tools.openapi.generator.languages.jersey.models.PomModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.WebModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.RecordDefinitionModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.ServerFactoryModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.ServiceModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.dao.DaoImplModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.dao.DaoModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.BaseJaxrsModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.operations.OtherJaxrsOperation;
import org.jepria.tools.openapi.generator.languages.jersey.models.test.JaxrsCrudTestModel;

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
    createAdapterTests(openAPI, this.outputFolderName + "\\src\\test\\java\\");
//    createDtos(openAPI, this.outputFolderName + "src\\main\\java\\" + this.getBasePackage().replace(".", "\\") + "\\");
    createWeb(this.outputFolderName + "\\src\\main\\webapp\\WEB-INF\\");
    createApplicationConfig(openAPI, this.outputFolderName + "\\src\\main\\java\\");
    createPom(this.getBasePackage(), this.outputFolderName + "\\");
  }

  public void create(String specFileLocation) throws IOException {
    try {
      OpenAPI openAPI = new OpenAPIV3Parser().read(specFileLocation);
      create(openAPI);
    } catch (Exception e) {
      System.err.println("Cannot read spec from file!");
    }
  }

  private void createEntities(OpenAPI spec, String outputFolder) throws IOException {
    String srcFolder  = outputFolder + "\\src\\main\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";
    String testFolder = outputFolder + "\\src\\test\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";
    ;
//    outputFolder = outputFolder + this.getBasePackage().replace(".", "\\") + "\\";

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(spec);
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    List<? extends BaseDtoImpl> dtos = generator.getDtos();
    for (BaseDtoImpl dto : dtos) {
      String className     = ((BaseJaxrsModel) dto).getClassName();
      String entityFolder  = srcFolder + className.toLowerCase() + "\\";
      String entityPackage = this.getBasePackage() + "." + className.toLowerCase();
      dto.saveToFile(entityFolder + "rest\\" + className + "JaxrsAdapter.java");
      createServerFactory(entityPackage, className, entityFolder);
      createService(entityPackage, className, ((BaseJaxrsModel) dto).getOperations(), entityFolder);
      createDao(entityPackage, className, ((BaseJaxrsModel) dto).getOperations(), entityFolder + "dao\\");
      createDaoImpl(entityPackage, className, ((BaseJaxrsModel) dto).getOperations(), entityFolder + "dao\\");
      createDtos(spec, entityFolder + "dto\\");
      createRecordDefinition(entityPackage, className, entityFolder);
      createCrudTests(entityPackage, className, testFolder);
      //TODO: FieldNames ???
    }

  }

  private void createAdapterTests(OpenAPI spec, String outputFolder) throws IOException {
    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createDtos(OpenAPI spec, String outputFolder) throws IOException { //TODO: DTO packages missed!!!
    DtoGenerator generator = new DtoGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
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
    ApplicationConfigGenerator generator = new ApplicationConfigGenerator(spec);
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createServerFactory(String apiPackage, String className, String outputFolder) throws IOException {
    ServerFactoryModel dto = new ServerFactoryModel();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);

    String fileName = className + "ServerFactory.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, SERVER_FACTORY_TEMPLATE);
  }

  private void createService(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    ServiceModel dto = new ServiceModel();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = className + "Service.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, SERVICE_TEMPLATE);
  }

  private void createDao(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoModel dto = new DaoModel();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = className + "Dao.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, DAO_TEMPLATE);
  }

  private void createDaoImpl(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoImplModel dto = new DaoImplModel();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    String fileName = className + "DaoImpl.java";

    GeneratorImpl generator = new GeneratorImpl();
    generator.generate(dto, outputFolder, fileName, DAO_IMPL_TEMPLATE);
  }

  private void createRecordDefinition(String apiPackage, String className, String outputFolder) throws IOException {
    RecordDefinitionModel dto = new RecordDefinitionModel();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);

    String fileName = className + "RecordDefinition.java";

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

  private void createCrudTests(String entityPackage, String className, String outputFolder) throws IOException {
    JaxrsCrudTestModel dto = new JaxrsCrudTestModel();
    dto.setApiPackage(entityPackage);
    dto.setClassName(className);
    dto.setModelPackage(entityPackage + ".dto");

    String fileName = className + "JaxrsCrudTestIT.java";

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
