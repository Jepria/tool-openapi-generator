package org.jepria.tools.openapi.generator.languages.jersey;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class CrudTestsCreatorTest {

  @Test
  void create() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/feature/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\CrudTestsCreatorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    CrudTestsCreator testsCreator = new CrudTestsCreator(outputFolder);

    testsCreator.setBasePackage("com.technology.rfi");
    testsCreator.create(specLocation);

  }
}