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

  public boolean create(OpenAPI openAPI) throws IOException {
    if (null == openAPI) {
      return false;
    }

    File file = new File(outputFolderName);

    Boolean check = file.mkdir();
    createAdapters(openAPI, this.outputFolderName + "src\\main\\java\\");
    createAdapterTests(openAPI, this.outputFolderName + "src\\test\\java");
//    createDtos(openAPI, this.outputFolderName + "src\\main\\java\\" + this.getBasePackage().replace(".", "\\") + "\\");
    createWeb(this.outputFolderName + "src\\main\\webapp\\WEB-INF\\");
    createApplicationConfig(openAPI, this.outputFolderName + "src\\main\\java\\");
    createPom(this.getBasePackage(), this.outputFolderName);
    return false;
  }

  public void create(String specFileLocation) throws IOException {
    OpenAPI openAPI = null;
    try {
      openAPI = new OpenAPIV3Parser().read(specFileLocation);
    } catch (Exception e) {
      System.err.println("Cannot read spec from file!");
    }
    if (null != openAPI) {
      create(openAPI);
    }
  }

  private void createAdapters(OpenAPI spec, String outputFolder) throws IOException {
    outputFolder = outputFolder + this.getBasePackage().replace(".", "\\") + "\\";

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(spec);
    generator.setMainPackage(this.getBasePackage());
    generator.create();
    List<? extends BaseDtoImpl> dtos = generator.getDtos();
    for (BaseDtoImpl dto : dtos) {
      String entityFolder = outputFolder + ((BaseJaxrsDto) dto).getClassName().toLowerCase() + "\\";
      String entityPackage = this.getBasePackage() + "." + ((BaseJaxrsDto) dto).getClassName().toLowerCase();
      dto.saveToFile(entityFolder + "rest\\" + ((BaseJaxrsDto) dto).getClassName() + "JaxrsAdapter.java");
      createServerFactory(entityPackage, ((BaseJaxrsDto) dto).getClassName(), entityFolder);
      createService(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder);
      createDao(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder + "dao\\");
      createDaoImpl(entityPackage, ((BaseJaxrsDto) dto).getClassName(), ((BaseJaxrsDto) dto).getOperations(), entityFolder + "dao\\");
      createDtos(spec, entityFolder + "dto\\");
      createRecordDefinition(entityPackage, ((BaseJaxrsDto) dto).getClassName(), entityFolder);
    }

  }

  private void createAdapterTests(OpenAPI spec, String outputFolder) throws IOException {
    JaxrsAdapterTestGenerator generator = new JaxrsAdapterTestGenerator(spec);
    generator.create();
    generator.saveToFiles(outputFolder);
  }

  private void createDtos(OpenAPI spec, String outputFolder) throws IOException {
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
    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "Service.java");
  }

  private void createDao(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoDto dto = new DaoDto();
    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
    dto.fillTemplate();
    dto.saveToFile(outputFolder + className + "Dao.java");
  }

  private void createDaoImpl(String apiPackage, String className, List<OtherJaxrsOperation> operations, String outputFolder) throws IOException {
    DaoImplDto dto = new DaoImplDto();
    dto.setApiPackage(apiPackage);
    dto.setClassName(className);
    dto.setOperations(operations);
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

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  public String getBasePackage() {
    return basePackage;
  }

}
