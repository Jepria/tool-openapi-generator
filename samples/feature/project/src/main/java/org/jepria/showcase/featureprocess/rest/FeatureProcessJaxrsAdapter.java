package org.jepria.showcase.featureprocess.rest;

import org.jepria.showcase.featureprocess.dto.*;
import org.jepria.showcase.featureprocess.FeatureProcessServerFactory;
import org.jepria.server.data.OptionDto;
import org.jepria.server.data.SearchRequestDto;
import org.jepria.server.service.rest.ExtendedResponse;
import org.jepria.server.service.rest.JaxrsAdapterBase;
import org.jepria.server.service.security.HttpBasic;



import java.util.Map;
import java.util.List;

import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.Pattern;

@Path("/feature/{featureId}/feature-process")

public class FeatureProcessJaxrsAdapter extends JaxrsAdapterBase {

  protected final EntityEndpointAdapter entityEndpointAdapter = new EntityEndpointAdapter(() -> FeatureProcessServerFactory.getInstance().getEntityService());

  protected final SearchEndpointAdapter searchEndpointAdapter = new SearchEndpointAdapter(() -> FeatureProcessServerFactory.getInstance().getSearchService(() -> request.getSession()));

  @GET
  @Path("")
  public Response findFeatureProcess(
      @PathParam("featureId") Integer featureId) {
    List<FeatureProcessDto> result = FeatureProcessServerFactory.getInstance().getService().findFeatureProcess( featureId);
    return Response.ok(result).build();
  }

  @POST
  public Response create(FeatureProcessCreateDto record) {
    return entityEndpointAdapter.create(record);
  }

  @GET
  @Path("/{recordId}")
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
  FeatureProcessDto result = (FeatureProcessDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @DELETE
  @Path("/{recordId}")
  public Response deleteRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    entityEndpointAdapter.deleteRecordById(recordId);
    return Response.ok().build();
  }


}