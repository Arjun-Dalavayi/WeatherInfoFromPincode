package com.weatherinfo.Weather.Imformation.service;

import com.weatherinfo.Weather.Imformation.apiResponce.LatAndLon;
import com.weatherinfo.Weather.Imformation.apiResponce.WeatherResponse;

import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class  WeatherService {

    @Autowired
    SaveDataToDB saveDataToDB;


    @Value("${weather.api.LatAndLonUrl}")
    private String LatAndLon_API_URL;

    @Value("${weather.api.key}")
    private String API_KEY;

    @Value("${weather.api.weatherInfoUrl}")
    private String weatherInfo_API_URL;




    private final RestTemplate restTemplate = new RestTemplate();

   //When requested data is not present in the database then this method will run
   //this method call external API and send returns weather information
    //And it also store the weather information and requested data in database
    public WeatherInfo getLanAndLog(RequestInfo requestInfo) throws Exception{


        String finalAPI = LatAndLon_API_URL.replace("{API key}", API_KEY).replace("{zip code}", requestInfo.getPinCode()).replace("{country code}", "IN");
        System.out.println(finalAPI);


        ResponseEntity<LatAndLon> response ;
        try {
            //Calling API to get lat and log
            response= restTemplate.exchange(finalAPI, HttpMethod.GET, null, LatAndLon.class);
        }catch (Exception e){
            throw new Exception();
        }

        LatAndLon latAndLon = response.getBody();
        if(latAndLon==null){
            return null;
        }
         requestInfo.setLon(latAndLon.getLon());
         requestInfo.setLat(latAndLon.getLat());
         return getWeatherInfo(latAndLon, requestInfo);
    }

    public WeatherInfo getWeatherInfo(LatAndLon latAndLon, RequestInfo requestInfo)throws Exception {
        String finalAPI =weatherInfo_API_URL.replace("{API key}", API_KEY).replace("{lat}", ""+requestInfo.getLat()).replace("{lon}", requestInfo.getLon()+"");

        ResponseEntity<WeatherResponse> response;
        try {
            //Calling API to get weather information
             response= restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        }catch (Exception e){
            throw new Exception();
        }
        WeatherResponse weatherResponse = response.getBody();

        WeatherInfo weatherInfo=null;
        if(weatherResponse!=null){
            requestInfo.setLon(latAndLon.getLon());
            requestInfo.setLat(latAndLon.getLat());
            weatherInfo=saveDataToDB.SaveAllDataToDB(requestInfo,weatherResponse);
        }
        return weatherInfo;
    }


}
