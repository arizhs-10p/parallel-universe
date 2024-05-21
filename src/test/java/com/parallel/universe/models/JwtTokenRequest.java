package com.parallel.universe.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenRequest {

    String emailAddress;
    String deviceSessionId;
    String password;

    public JwtTokenRequest(String emailAddress, String deviceSessionId, String password) {
        this.emailAddress = emailAddress;
        this.deviceSessionId = deviceSessionId;
        this.password = password;
    }

}
