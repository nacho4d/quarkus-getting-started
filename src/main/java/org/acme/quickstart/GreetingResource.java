package org.acme.quickstart;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quickstart.responses.BaseResponse;
import org.acme.quickstart.responses.BaseResult;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") final String name) {
        return service.greeting(name);
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json")
    public BaseResponse jsonSync() {
        BaseResponse r = new BaseResponse(BaseResult.OK, "Everything is fine");
        return r;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/async")
    public CompletionStage<BaseResponse> demand() {
        return CompletableFuture.supplyAsync(() -> {
            BaseResponse r = new BaseResponse(BaseResult.OK, "Everything is fine");
            return r;
        });
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text/async")
    public String text() {
        return "example";
    }

}