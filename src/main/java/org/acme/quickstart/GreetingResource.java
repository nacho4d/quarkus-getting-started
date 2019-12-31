package org.acme.quickstart;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quickstart.responses.base.Response;
import org.acme.quickstart.responses.base.Result;

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
    public CompletionStage<Response> demand() {
        return CompletableFuture.supplyAsync(() -> {
            Response r = new Response(Result.OK, "Everything is fine");
            return r;
        });
    }

}