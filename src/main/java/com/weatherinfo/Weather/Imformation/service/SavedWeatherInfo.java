package com.weatherinfo.Weather.Imformation.service;

import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import com.weatherinfo.Weather.Imformation.repository.PinAndDateRepository;
import com.weatherinfo.Weather.Imformation.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class SavedWeatherInfo{
    @Autowired
    PinAndDateRepository pinAndDateRepository;

    @Autowired
    WeatherInfoRepository weatherInfoRepository;

    //Checking requested data is in database or not
    //If data present in the database then it returns the data from database
    //So there is no need to call External API
    public WeatherInfo checkDB(RequestInfo input) {
        RequestInfo foreignKey=pinAndDateRepository.findByPinCodeAndDate(input.getPinCode(),input.getDate());
        if(foreignKey==null){
            return null;
        }

       return weatherInfoRepository.findByPrimaryKey(foreignKey.getForeignKey());
    }




}
