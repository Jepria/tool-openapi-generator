package org.jepria.tools.openapi.generator;

import static org.jepria.tools.openapi.generator.GeneratorCli.GEN_OPT;
import static org.jepria.tools.openapi.generator.GeneratorCli.GEN_PROJECT;
import static org.jepria.tools.openapi.generator.GeneratorCli.GEN_TESTS;
import static org.jepria.tools.openapi.generator.GeneratorCli.OUTPUT_OPT;
import static org.jepria.tools.openapi.generator.GeneratorCli.PACKAGE_OPT;
import static org.jepria.tools.openapi.generator.GeneratorCli.SPEC_OPT;
import static org.jepria.tools.openapi.generator.GeneratorCli.generate;

import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class GeneratorCliTest {


  @Test
  public void generateTestsTest() throws ParseException, IOException {

    String specLocation = new File(getClass().getClassLoader().getResource("feature/swagger.json").getPath()).getCanonicalPath();

    String[] argv = {"-" + SPEC_OPT, specLocation, "-" + OUTPUT_OPT, "temp/Tests", "-" + GEN_OPT, GEN_TESTS};

    generate(argv);

  }


  @Test
  public void test() throws IOException, ParseException {
    String specLocation = new File(getClass().getClassLoader().getResource("feature/swagger.json").getPath()).getCanonicalPath();

    String[] argv = {"-" + SPEC_OPT, specLocation, "-" + OUTPUT_OPT, "temp/Project", "-" + GEN_OPT, GEN_PROJECT, "-" + PACKAGE_OPT, "org.jepria.showcase"};

    generate(argv);
  }

}