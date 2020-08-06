package org.jepria.tools.openapi.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;

public class GeneratorImpl {

  public void generate(Object model, String path, String fileName, String templateFileName) throws IOException {

    String filledTemplate = fillTemplate(model, templateFileName);

    saveToFile(filledTemplate, path, fileName);
  }

  private String fillTemplate(Object model, String templateFileName) throws IOException {

    if (null == getClass().getResourceAsStream(templateFileName)) {
      throw new IOException("Cannot get template file");
    }

    Reader templateSource = new InputStreamReader(getClass().getResourceAsStream(templateFileName));

    Template template = Mustache.compiler().escapeHTML(false).compile(templateSource);
    templateSource.close();

    return template.execute(model).replace(", )", ")");
  }

  private void saveToFile(String object, String location, String fileName) throws IOException {

    new File(location).mkdirs();

    File file = new File(location + fileName);
    if (file.exists()) {
      file.delete();
    }

    if (file.createNewFile()) {
      Files.write(file.toPath(), object.getBytes());
    }

  }

}
