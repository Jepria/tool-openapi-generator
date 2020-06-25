package org.jepria.showcase.main.rest.jersey;

import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import org.jepria.showcase.feature.rest.FeatureJaxrsAdapter;
import org.jepria.showcase.featureprocess.rest.FeatureProcessJaxrsAdapter;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationConfig extends ApplicationConfigBase {
  
  public ApplicationConfig() {
    register(FeatureJaxrsAdapter.class);
    register(FeatureProcessJaxrsAdapter.class);

    register(new ExceptionMapper<ApplicationException>() {
      @Override
      public Response toResponse(ApplicationException e) {
        e.printStackTrace();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String stackTraceString = baos.toString();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(stackTraceString)
                .type("text/plain;charset=UTF-8").build();
      }
    });
  }
}