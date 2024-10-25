package com.weatherinfo.Weather.Imformation.apiResponce;

import lombok.Getter;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
LatAndLon root = om.readValue(myJsonString, LatAndLon.class); */
@Getter
public class LatAndLon {
    private String zip;
    private String name;
    private double lat;
    private double lon;
    private String country;

}

