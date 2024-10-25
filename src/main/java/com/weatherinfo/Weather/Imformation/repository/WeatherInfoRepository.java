package com.weatherinfo.Weather.Imformation.repository;

import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo,Integer>{
    WeatherInfo findByPrimaryKey(Integer primaryKey);
}
