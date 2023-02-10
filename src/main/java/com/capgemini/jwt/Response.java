package com.capgemini.jwt;

import java.io.Serializable;

public class Response implements Serializable {

    private final String jwt;

    public Response(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
