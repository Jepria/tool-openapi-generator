package org.jepria.tools.openapi.generator.languages.jersey;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

public class ApplicationStructureCreatorTest {

  @Test
  public void create() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\ApplicationStructureCreatorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    ApplicationStructureCreator structureCreator = new ApplicationStructureCreator(outputFolder);

    structureCreator.setBasePackage("com.technology.rfi");
    structureCreator.create(specLocation);

  }

  @Test
  public void featureProject()  throws IOException{
    String specLocation = new File(getClass().getClassLoader().getResource("spec/feature/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\ApplicationStructureCreatorTestFeature\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    ApplicationStructureCreator structureCreator = new ApplicationStructureCreator(outputFolder);

    structureCreator.setBasePackage("com.technology.rfi");
    structureCreator.create(specLocation);
  }

}