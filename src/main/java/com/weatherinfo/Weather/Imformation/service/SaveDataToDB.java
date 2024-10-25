package com.weatherinfo.Weather.Imformation.service;

import com.weatherinfo.Weather.Imformation.apiResponce.WeatherResponse;
import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import com.weatherinfo.Weather.Imformation.repository.PinAndDateRepository;
import com.weatherinfo.Weather.Imformation.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveDataToDB {
    @Autowired
    private PinAndDateRepository pinAndDateRepository;
    @Autowired
    private WeatherInfoRepository weatherInfoRepository;
    @Autowired
    private WeatherInfo weatherInfo;

    //This method save all data to database
    public WeatherInfo SaveAllDataToDB(RequestInfo requestInfo, WeatherResponse weatherResponse){
        saveWeatherInfo(weatherResponse);
        requestInfo.setForeignKey(weatherInfo.getPrimaryKey());
        saveRequestInfo(requestInfo);
        return weatherInfo;
    }
    private void saveRequestInfo(RequestInfo requestInfo){
        pinAndDateRepository.save(requestInfo);
    }
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;
    private double rainHour;
    private String main;
    private String description;
    private double windSpeed;
    private int windDeg;
    private double WindGust;

    private void saveWeatherInfo(WeatherResponse weatherResponse){
      weatherInfo.setTemp(weatherResponse.getMain().getTemp());
      weatherInfo.setFeels_like(weatherResponse.getMain().getFeels_like());
      weatherInfo.setTemp_min(weatherResponse.getMain().getTemp_min());
      weatherInfo.setTemp_max(weatherResponse.getMain().getTemp_max());
      weatherInfo.setPressure(weatherResponse.getMain().getPressure());
      weatherInfo.setHumidity(weatherResponse.getMain().getHumidity());
      weatherInfo.setSea_level(weatherResponse.getMain().getSea_level());
      weatherInfo.setGrnd_level(weatherResponse.getMain().getGrnd_level());
      weatherInfo.setWindSpeed(weatherResponse.getWind().getSpeed());
      weatherInfo.setWindDeg(weatherResponse.getWind().getDeg());
      weatherInfo.setWindGust(weatherResponse.getWind().getGust());
      weatherInfoRepository.save(weatherInfo);
    }


}
