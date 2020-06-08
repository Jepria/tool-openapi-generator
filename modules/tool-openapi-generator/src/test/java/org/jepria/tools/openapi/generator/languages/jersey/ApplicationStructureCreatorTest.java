package org.jepria.tools.openapi.generator.languages.jersey;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ApplicationStructureCreatorTest {

  @Test
  public void create() throws IOException {
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\out\\";
    String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
    String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
    String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";

//    String specLocation = specTest;
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();

    ApplicationStructureCreator structureCreator = new ApplicationStructureCreator(outputFolder);

    structureCreator.setBasePackage("com.technology.rfi");
    structureCreator.create(specLocation);

  }

  @Test
  public void brokerProject()  throws IOException{

  }

  @Test
  public void featureProject()  throws IOException{
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\out\\feature\\";
    String specLocation = new File(getClass().getClassLoader().getResource("spec/feature/swagger.json").getPath()).getCanonicalPath();

    ApplicationStructureCreator structureCreator = new ApplicationStructureCreator(outputFolder);

    structureCreator.setBasePackage("com.technology.rfi");
    structureCreator.create(specLocation);
  }

}