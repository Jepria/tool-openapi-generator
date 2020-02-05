package com.technology.jep.jepriashowcase.feature;

import com.technology.jep.jepriashowcase.*;
import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
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

@Path("/feature")

public class FeatureJaxrsAdapter  {

protected final EntityEndpointAdapter entityEndpointAdapter = new EntityEndpointAdapter(() -> FeatureServerFactory.getInstance().getEntityService());

protected final SearchEndpointAdapter searchEndpointAdapter = new SearchEndpointAdapter(() -> FeatureServerFactory.getInstance().getSearchService(() -> request.getSession()));

  @POST
  @Path("/{featureId}/set-feature-responsible")
  public Response setFeatureResponsible(
      @PathParam("featureId") Integer featureId, 
      @QueryParam("responsibleId") Integer responsibleId) {
    return delegate.setFeatureResponsible(featureId, responsibleId);
  }

  @GET
  @Path("/option/feature-operator")
  public Response getFeatureOperator() {
    return delegate.getFeatureOperator();
  }

  @GET
  @Path("/option/feature-status")
  public Response getFeatureStatus() {
    return delegate.getFeatureStatus();
  }

//------------ entity methods ------------//
  @GET
  @Path("/{recordId}")
  public Response getRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    FeatureDto result = (FeatureDto) entityEndpointAdapter.getRecordById(recordId);
    return Response.ok(result).build();
  }

  @PUT
  @Path("/{recordId}")
  public Response update(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId, FeatureUpdateDto record) {
    entityEndpointAdapter.update(recordId, record);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{recordId}")
  public Response deleteRecordById(@Pattern(regexp = "\\d+", message = "ID must be an integer") @PathParam("recordId") String recordId) {
    entityEndpointAdapter.deleteRecordById(recordId);
    return Response.ok().build();
  }

  @POST
  public Response create(FeatureCreateDto record) {
    return entityEndpointAdapter.create(record);
  }

//------------ search methods ------------//
  @GET
  @Path("/search/{searchId}/resultset/paged-by-{pageSize:\\d+}/{page}")
  public Response getResultsetPaged(
      @PathParam("searchId") String searchId,
      @PathParam("pageSize") Integer pageSize,
      @PathParam("page") Integer page,
      @HeaderParam("Cache-Control") String cacheControl) {
      List<FeatureDto> result = (List <FeatureDto>)searchEndpointAdapter.getResultsetPaged(searchId, pageSize, page, cacheControl);
    return result == null ? Response.noContent().build() : Response.ok(result).build();
  }

  @GET
  @Path("/search/{searchId}")
  public Response getSearchRequest(
      @PathParam("searchId") String searchId) {
      SearchRequestDto<FeatureSearchDto> result = (SearchRequestDto <FeatureSearchDto>)searchEndpointAdapter.getSearchRequest(searchId);
    return Response.ok(result).build();
  }

  @GET
  @Path("/search/{searchId}/resultset-size")
  public Response getSearchResultsetSize(
      @PathParam("searchId") String searchId,
      @HeaderParam("Cache-Control") String cacheControl) {
    int result = searchEndpointAdapter.getSearchResultsetSize(searchId, cacheControl);
    return Response.ok(result).build();
  }

  @POST
  @Path("/search")
  public Response postSearch(
      SearchRequestDto<FeatureSearchDto> searchRequestDto,
      @Pattern(regexp =
        "(resultset/paged-by-\\d+/\\d+)|(resultset\\?pageSize\\d+&page=\\d+)|(resultset\\?page=\\d+&pageSize=\\d+)",
        message = "Bad Extended-Response header value")
      @HeaderParam(ExtendedResponse.REQUEST_HEADER_NAME) String extendedResponse,
      @HeaderParam("Cache-Control") String cacheControl) {
    return searchEndpointAdapter.postSearch(searchRequestDto, extendedResponse, cacheControl);
  }

  @GET
  @Path("/search/{searchId}/resultset")
  public Response getResultset(
      @PathParam("searchId") String searchId,
      @QueryParam("pageSize") Integer pageSize,
      @QueryParam("page") Integer page,
      @HeaderParam("Cache-Control") String cacheControl) {
      List<FeatureDto> result = (List <FeatureDto>)searchEndpointAdapter.getResultset(searchId, pageSize, page, cacheControl);
    return result == null ? Response.noContent().build() : Response.ok(result).build();
  }


}