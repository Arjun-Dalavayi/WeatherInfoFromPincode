package com.weatherinfo.Weather.Imformation.entity;

import com.weatherinfo.Weather.Imformation.apiResponce.WeatherResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@ToString
@Component
public class WeatherInfo {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer primaryKey;

    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;
    private double windSpeed;
    private int windDeg;
    private double WindGust;

}
