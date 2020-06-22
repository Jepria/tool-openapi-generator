package org.jepria.tools.openapi.generator;

import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.dto.DtoGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.rest.JaxrsAdapterGenerator;
import org.jepria.tools.openapi.generator.languages.jersey.ApplicationStructureCreator;
import org.jepria.tools.openapi.generator.languages.jersey.generators.test.rest.JaxrsAdapterTestGenerator;

public class GeneratorCli {

  public static final  String SPEC_OPT      = "i";
  private static final String SPEC_OPT_NAME = "specification";

  public static final  String OUTPUT_OPT      = "o";
  private static final String OUTPUT_OPT_NAME = "output";

  public static final String PACKAGE_OPT      = "pkg";
  public static final String PACKAGE_OPT_NAME = "package";

  public static final  String GEN_OPT      = "g";
  private static final String GEN_OPT_NAME = "generate";
  public static final  String GEN_TESTS    = "tests";
  public static final  String GEN_REST     = "rest";
  public static final  String GEN_PROJECT  = "proj";
  private static final String GEN_DTO      = "dto";


  public static void main(String[] args) throws ParseException, IOException {

    generate(args);

  }

  public static void generate(String[] args) throws ParseException, IOException {

    CommandLine commandLine = new DefaultParser().parse(getOptions(), args);

    String specPath    = null;
    String outputPath  = null;
    String mainPackage = null;

    if (commandLine.hasOption(SPEC_OPT)) {
      System.out.println(commandLine.getOptionValue(SPEC_OPT));
      specPath = commandLine.getOptionValue(SPEC_OPT);
    } else {
      System.out.println("Set OpenApi specification file");
      return;
    }
    if (commandLine.hasOption(OUTPUT_OPT)) {
      System.out.println(commandLine.getOptionValue(OUTPUT_OPT));
      outputPath = commandLine.getOptionValue(OUTPUT_OPT);
    } else {
      outputPath = "out/";
      System.out.println("There is no output directory option. The default output directory will be used: " + outputPath);
    }

    if (commandLine.hasOption(PACKAGE_OPT)) {
      mainPackage = commandLine.getOptionValue(PACKAGE_OPT);
    } else {
      mainPackage = "org.jepria";
    }

    if (commandLine.hasOption(GEN_OPT)) {
      if (commandLine.getOptionValue(GEN_OPT).equals(GEN_REST)) {
        System.out.println("Generate rest adapters...");
        JaxrsAdapterGenerator adapterGen = new JaxrsAdapterGenerator(specPath);
        if (null != mainPackage) {
          adapterGen.setMainPackage(mainPackage);
        }
        adapterGen.create();
        adapterGen.saveToFiles(outputPath);
      } else if (commandLine.getOptionValue(GEN_OPT).equals(GEN_TESTS)) {
        System.out.println("Generate tests for rest adapters...");
        JaxrsAdapterTestGenerator adapterTestGen = new JaxrsAdapterTestGenerator(specPath);
        if (null != mainPackage) {
          adapterTestGen.setMainPackage(mainPackage);
        }
        adapterTestGen.create();
        adapterTestGen.saveToFiles(outputPath);
      } else if (commandLine.getOptionValue(GEN_OPT).equals(GEN_PROJECT)) {
        System.out.println("Generate project...");
        ApplicationStructureCreator creator = new ApplicationStructureCreator(outputPath);
        creator.setBasePackage(mainPackage);
        creator.create(specPath);
      } else if (commandLine.getOptionValue(GEN_OPT).equals(GEN_DTO)){
        System.out.println("Generate dtos...");
        DtoGenerator generator = new DtoGenerator(specPath);
        generator.create();
        generator.saveToFiles(outputPath);
      }
    } else {

    }

  }

  private static Options getOptions() {

    Option specOpt = new Option(SPEC_OPT, SPEC_OPT_NAME, true, "OpenAPI specification");
    specOpt.setArgs(1);
    specOpt.setArgName(SPEC_OPT_NAME);

    Option outputDirOpt = new Option(OUTPUT_OPT, OUTPUT_OPT_NAME, true, "Output directory");
    outputDirOpt.setArgs(1);
    outputDirOpt.setArgName(OUTPUT_OPT_NAME);

    Option generateOpt = new Option(GEN_OPT, GEN_OPT_NAME, true, "Output directory");
    outputDirOpt.setArgs(1);
    outputDirOpt.setArgName(GEN_OPT_NAME);

    Option packageOpt = new Option(PACKAGE_OPT, PACKAGE_OPT_NAME, true, "Package");
    outputDirOpt.setArgs(1);
    outputDirOpt.setArgName(PACKAGE_OPT_NAME);

    Options options = new Options();
    options.addOption(specOpt);
    options.addOption(outputDirOpt);
    options.addOption(generateOpt);
    options.addOption(packageOpt);

    return options;
  }

}
