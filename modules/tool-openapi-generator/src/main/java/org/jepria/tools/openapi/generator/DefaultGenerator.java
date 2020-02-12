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
import java.util.List;
import java.util.Map;
import org.jepria.tools.openapi.generator.languages.jersey.dtos.BaseDtoImpl;

public abstract class DefaultGenerator implements Generator {

  public void setValues(Map<String, String> values) {
    this.values = values;
  }

  private Map<String, String> values;

  private List<? extends BaseDtoImpl> dtos;

  private String templateFileName;

  private String mainPackage;

  private OpenAPI openAPI;

  private String fileExtension = ".java";

  private String baseName = "";

  public void saveToFiles(String rootLocation) throws IOException {

    if (null != getValues()) {
      for (String adapterName : getValues().keySet()) {
        System.out.println(adapterName);

//        String path = rootLocation + "\\" + adapterName.replace(".", "\\") + this.getBaseName() + fileExtension ;

        new java.io.File(rootLocation).mkdirs();

        File file = new File(rootLocation + adapterName + this.getBaseName() + this.fileExtension);
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

  public void setTemplateFileName(String templateFileName) {
    this.templateFileName = templateFileName;
  }

  protected String fillTemplate(Object dto) throws IOException {

    if (null == getClass().getResourceAsStream(this.getTemplateFileName())) {
      throw new IOException("Cannot get template file");
    }

    Reader templateSource = new InputStreamReader(getClass().getResourceAsStream(this.getTemplateFileName()));

    Template template = Mustache.compiler().escapeHTML(false).compile(templateSource);
    templateSource.close();

    return template.execute(dto).replace(", )", ")");
  }

  public void setMainPackage(String mainPackage) {
    this.mainPackage = mainPackage;
  }

  public String getMainPackage() {
    return mainPackage;
  }

  public OpenAPI getOpenAPI() {
    return this.openAPI;
  }

  public void setOpenAPI(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  public void setOpenAPI(String openAPILocation) {
    this.openAPI = new OpenAPIV3Parser().read(openAPILocation);
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public String getBaseName() {
    return baseName;
  }

  public void setBaseName(String baseName) {
    this.baseName = baseName;
  }

  public void setDtos(List<? extends BaseDtoImpl> dtos) {
    this.dtos = dtos;
  }

  public List<? extends BaseDtoImpl> getDtos() {
    return dtos;
  }
}
