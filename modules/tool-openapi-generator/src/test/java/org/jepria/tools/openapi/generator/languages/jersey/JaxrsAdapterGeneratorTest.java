package org.jepria.tools.openapi.generator.languages.jersey;

import org.junit.jupiter.api.Test;

class JaxrsAdapterGeneratorTest {

  String specShowcase = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\feature\\swagger.json";
  String specBroker   = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\broker\\swagger.json";
  String specTest     = "d:\\work\\tool-openapi-generator\\modules\\tool-openapi-generator\\src\\main\\resources\\spec\\swagger.json";
  String specEmployee     = "d:\\work\\RusFinanceInfo\\Module\\OperatorScoringAdapter\\Branch\\REST\\service-rest\\src\\api-spec\\EmployeeCity\\swagger.json";

  @Test
  void createTest() throws Exception {
    String specLocation = specShowcase;
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\JaxrsAdapterGenerator\\";

    JaxrsAdapterGenerator generator = new JaxrsAdapterGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);
  }
}