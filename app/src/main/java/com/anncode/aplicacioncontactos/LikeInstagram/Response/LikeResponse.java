package com.anncode.aplicacioncontactos.LikeInstagram.Response;

/**
 * Created by isaachernandezquinonez on 15/07/16.
 */
public class LikeResponse {
    private String code;

    public LikeResponse(String code) {
        this.code = code;
    }
    public LikeResponse() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
