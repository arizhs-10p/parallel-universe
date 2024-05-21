package com.parallel.universe.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenResponse {

    public String jwtToken;
    public String identityToken;
    public String fullName;
    public String userEmail;
    public Integer userId;
    public Integer selectedCompanyId;
    public String creationDate;
    public Boolean hasEmployeeAccess;
    public String country;


}