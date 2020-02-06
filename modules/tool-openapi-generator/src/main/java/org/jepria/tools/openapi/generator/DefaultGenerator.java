package org.jepria.tools.openapi.generator;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Map;

public abstract class DefaultGenerator implements Generator {

  public void setValues(Map<String, String> values) {
    this.values = values;
  }

  private Map<String, String> values;

  private String specLocation;

  private String templateFileName;

  public OpenAPI getOpenAPI() {
    return openAPI;
  }

  private OpenAPI openAPI;

  public DefaultGenerator(String specLocation) {
    this.specLocation = specLocation;
    this.openAPI      = new OpenAPIV3Parser().read(specLocation);
  }

  public DefaultGenerator(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  public void saveToFiles(String rootLocation) throws IOException {

    if (null != getValues()) {
      for (String adapterName : getValues().keySet()) {
        System.out.println(adapterName);

        new java.io.File(rootLocation).mkdirs();

        File file = new File(rootLocation + adapterName + ".java");
        if (file.exists()) {
          file.delete();
        }

        if (file.createNewFile()) {
          Files.write(file.toPath(), this.values.get(adapterName).getBytes());
        }
      }
    }
  }

  public Map<String, String> getValues() {
    return this.values;
  }

  public abstract void create();

  public String getTemplateFileName() {
    return templateFileName;
  }

  public String getSpecLocation() {
    return specLocation;
  }

  public void setTemplateFileName(String templateFileName) {
    this.templateFileName = templateFileName;
  }

  protected String fillTemplate(Object dto) throws IOException {
    Reader templateSource = new InputStreamReader(getClass().getResourceAsStream(this.getTemplateFileName()));

    Template template = Mustache.compiler().escapeHTML(false).compile(templateSource);
    templateSource.close();

    return template.execute(dto).replace(", )", ")");
  }
}
