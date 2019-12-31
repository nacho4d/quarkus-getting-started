package org.acme.quickstart.responses.base;

public class Response {
    Result result;
    String message;
    public Response(Result r, String m) {
        result = r;
        message = m;
    }
}