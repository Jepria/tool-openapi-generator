package org.jepria.tools.openapi.generator.languages.jersey;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ApplicationStructureCreatorTest {
  
  @Test
  public void create() throws IOException {
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\out\\";
    String specShowcase = "d:\\work\\jepria-showcase\\module\\JepRiaShowcase\\App\\service-rest\\src\\api-spec\\feature\\swagger.json";
    String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
    String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";
    String specLocation = specTest;

    ApplicationStructureCreator structureCreator = new ApplicationStructureCreator(outputFolder);

    structureCreator.create(specLocation);


  }
}