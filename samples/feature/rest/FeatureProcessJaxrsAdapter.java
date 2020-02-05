package com.technology.jep.jepriashowcase.featureFeatureIdFeatureProcess;

import com.technology.jep.jepriashowcase.*;
import com.technology.jep.jepriashowcase.featureFeatureIdFeatureProcess.FeatureProcessServerFactory;
import org.jepria.server.data.OptionDto;
import org.jepria.server.data.SearchRequestDto;
import org.jepria.server.service.rest.ExtendedResponse;
import org.jepria.server.service.rest.JaxrsAdapterBase;
import org.jepria.server.service.security.HttpBasic;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;


import java.util.Map;
import java.util.List;

import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/feature/{featureId}/feature-process")

public class FeatureProcessJaxrsAdapter  {

protected final EntityEndpointAdapter entityEndpointAdapter = new EntityEndpointAdapter(() -> FeatureProcessServerFactory.getInstance().getEntityService());

protected final SearchEndpointAdapter searchEndpointAdapter = new SearchEndpointAdapter(() -> FeatureProcessServerFactory.getInstance().getSearchService(() -> request.getSession()));

  @GET
  @Path("")
  public Response findFeatureProcess(
      @PathParam("featureId") Integer featureId) {
    return delegate.findFeatureProcess(featureId);
  }

  @POST
  public Response create(FeatureProcessCreateDto record) {
    return entityEndpointAdapter.create(record);
  }

  @GET
  @Path("/{recordId}")
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    FeatureDto result = (FeatureProcessDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @DELETE
  @Path("/{recordId}")
  public Response deleteRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    entityEndpointAdapter.deleteRecordById(recordId);
    return Response.ok().build();
  }


}