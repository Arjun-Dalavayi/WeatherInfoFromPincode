package com.weatherinfo.Weather.Imformation.controller;

import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import com.weatherinfo.Weather.Imformation.service.SavedWeatherInfo;
import com.weatherinfo.Weather.Imformation.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SavedWeatherInfo savedWeatherInfo;
   @PostMapping("/get-weather")
    public ResponseEntity<?> getDate(@RequestBody RequestInfo input){
       WeatherInfo weatherInfo=savedWeatherInfo.checkDB(input);
       if (weatherInfo!=null){
           return new ResponseEntity<>("from DB \n"+weatherInfo, HttpStatus.FOUND);
       }
       try {
           WeatherInfo weatherInfo1=weatherService.getLanAndLog(input);
           if(weatherInfo1!=null) {
               return new ResponseEntity<>("From Server \n"+weatherInfo1, HttpStatus.FOUND);
           }
           return new ResponseEntity<>("Weather Not found", HttpStatus.NOT_FOUND);
       }catch (Exception e){
           return new ResponseEntity<>("Invalid input !!!!", HttpStatus.BAD_REQUEST);
       }

   }

}
