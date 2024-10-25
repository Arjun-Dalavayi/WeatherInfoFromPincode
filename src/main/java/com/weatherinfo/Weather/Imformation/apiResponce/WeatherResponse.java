package com.weatherinfo.Weather.Imformation.apiResponce;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;


import java.util.ArrayList;

@Getter
public class WeatherResponse {
    private Main main;
    private int visibility;
    private Wind wind;
    @Getter
    public class Main{
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    @Getter
    public class Wind{
        private double speed;
        private int deg;
        private double gust;
    }



}






