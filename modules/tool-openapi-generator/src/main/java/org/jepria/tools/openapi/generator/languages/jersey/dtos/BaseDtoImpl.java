package org.jepria.tools.openapi.generator.languages.jersey.dtos;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;

public class BaseDtoImpl implements BaseDto {

  private String templateFileName;
  private String generatedCode;

  @Override
  public String fillTemplate() throws IOException {
    Reader templateSource = new InputStreamReader(getClass().getResourceAsStream(this.templateFileName));

    Template template = Mustache.compiler().escapeHTML(false).compile(templateSource);
    templateSource.close();

    return this.generatedCode = template.execute(this).replace(", )", ")");
  }

  @Override
  public void setTemplate(String template) {
    this.templateFileName = template;
  }

  @Override
  public String getGeneratedCode() {
    return generatedCode;
  }

  public void saveToFile(String location) throws IOException {

    if (null != this.generatedCode) {

      new java.io.File(location).mkdirs();

      File file = new File(location);
      if (file.exists()) {
        file.delete();
      }

      if (file.createNewFile()) {
        Files.write(file.toPath(), this.generatedCode.getBytes());
      }
    }
  }

}
