package org.acme.quickstart.responses;

public class BaseResponse {
    public BaseResult result;
    public String message;
    public BaseResponse() {
        result = BaseResult.OK;
        message = "";
    }
    public BaseResponse(BaseResult r, String m) {
        result = r;
        message = m;
    }
}