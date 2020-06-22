package org.jepria.tools.openapi.generator.languages.jersey;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jepria.tools.openapi.generator.languages.jersey.dtos.BaseDtoImpl;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.DaoDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.DaoImplDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.PomDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.RecordDefinitionDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.ServerFactoryDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.ServiceDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.BaseJaxrsDto;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.rest.operations.OtherJaxrsOperation;
import org.jepria.tools.openapi.generator.languages.jersey.generators.ApplicationConfigGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.DtoGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.JaxrsAdapterGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.JaxrsAdapterTestGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.WebGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.test.JaxrsCrudTestDto;

public class ApplicationStructureCreator {

  private String apiSpecFolder = "api-spec";
  private String outputFolderName;
  private String basePackage = "";

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
    String srcFolder = outputFolder + "\\src\\main\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";
    String testFolder = outputFolder + "\\src\\test\\java\\" + this.getBasePackage().replace(".", "\\") + "\\";;
//    outputFolder = outputFolder + this.getBasePackage().replace(".", "\\") + "\\";

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(spec);
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    List<? extends BaseDtoImpl> dtos = generator.getDtos();
    for (BaseDtoImpl dto : dtos) {
      String entityFolder = srcFolder + ((BaseJaxrsDto) dto).getClassName().toLowerCase() + "\\";
      String entityPackage = this.getBasePackage() + "." + ((BaseJaxrsDto) dto).getClassName().toLowerCase();
      dto.saveToFile(entityFolder + "rest\\" + ((BaseJaxrsDto) dto).getClassName() + "JaxrsAdapter.java");
      createServerFactory(entityPackage, ((BaseJaxrsDto) dto).getClassName(), entityFolder);
      createService(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder);
      createDao(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder + "dao\\");
      createDaoImpl(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder + "dao\\");
      createDtos(spec, entityFolder + "dto\\");
      createRecordDefinition(entityPackage, ((BaseJaxrsDto) dto).getClassName(), entityFolder);
      createCrudTests(entityPackage, ((BaseJaxrsDto) dto).getClassName(), testFolder);
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
    WebGenerator generator = new WebGenerator();
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createApplicationConfig(OpenAPI spec, String outputFolder) throws IOException {
    outputFolder = outputFolder + this.getBasePackage().replace(".", "\\") + "\\" + "main\\rest\\";
    ApplicationConfigGenerator generator = new ApplicationConfigGenerator(spec);
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createServerFactory(String apiPackage, String className, String outputFolder) throws IOException {
    ServerFactoryDto dto = new ServerFactoryDto();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);

    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "ServerFactory.java");
  }

  private void createService(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    ServiceDto dto = new ServiceDto();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "Service.java");
  }

  private void createDao(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoDto dto = new DaoDto();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "Dao.java");
  }

  private void createDaoImpl(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoImplDto dto = new DaoImplDto();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.setModelPackage(apiPackage + "." + "dto");

    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "DaoImpl.java");
  }

  private void createRecordDefinition(String apiPackage, String className, String outputFolder) throws IOException {
    RecordDefinitionDto dto = new RecordDefinitionDto();

    dto.setApiPackage(apiPackage);
    dto.setClassName(className);

    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "RecordDefinition.java");
  }

  private void createPom(String basePackage, String outputFolder) throws IOException {
    PomDto dto = new PomDto();

    dto.setBasePackage(basePackage);
    dto.setApplicationName("service-rest-name");

    dto.fillTemplate();
    dto.saveToFile(outputFolder + "pom.xml");
  }

  private void createCrudTests(String entityPackage, String className, String outputFolder) {
    JaxrsCrudTestDto dto = new JaxrsCrudTestDto();
    dto.setApiPackage(entityPackage);
    dto.setClassName(className);
    dto.setModelPackage(entityPackage + ".dto");
    try {
      dto.fillTemplate();
    } catch (IOException e) {
      System.err.println("JaxrsCrudTest fillTemplate");
      e.printStackTrace();
    }
    try {
      dto.saveToFile(outputFolder + className + "JaxrsCrudTestIT.java");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  public String getBasePackage() {
    return basePackage;
  }

}
