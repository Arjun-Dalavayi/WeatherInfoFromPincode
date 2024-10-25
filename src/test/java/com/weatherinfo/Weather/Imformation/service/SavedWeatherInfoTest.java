package com.weatherinfo.Weather.Imformation.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import com.weatherinfo.Weather.Imformation.repository.PinAndDateRepository;
import com.weatherinfo.Weather.Imformation.repository.WeatherInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SavedWeatherInfo.class, RequestInfo.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SavedWeatherInfoTest {
    @MockBean
    private PinAndDateRepository pinAndDateRepository;

    @Autowired
    private RequestInfo requestInfo;

    @Autowired
    private SavedWeatherInfo savedWeatherInfo;

    @MockBean
    private WeatherInfoRepository weatherInfoRepository;


    @Test
    void testCheckDB() {
        // Arrange
        RequestInfo requestInfo2 = new RequestInfo();
        requestInfo2.setDate("2020-03-01");
        requestInfo2.setForeignKey(1);
        requestInfo2.setLat(10.0d);
        requestInfo2.setLon(10.0d);
        requestInfo2.setPinCode("Pin Code");
        requestInfo2.setPrimaryKey(1);
        when(pinAndDateRepository.findByPinCodeAndDate(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(requestInfo2);

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setFeels_like(10.0d);
        weatherInfo.setGrnd_level(1);
        weatherInfo.setHumidity(1);
        weatherInfo.setPressure(1);
        weatherInfo.setPrimaryKey(1);
        weatherInfo.setSea_level(1);
        weatherInfo.setTemp(10.0d);
        weatherInfo.setTemp_max(10.0d);
        weatherInfo.setTemp_min(10.0d);
        weatherInfo.setWindDeg(1);
        weatherInfo.setWindGust(10.0d);
        weatherInfo.setWindSpeed(10.0d);
        when(weatherInfoRepository.findByPrimaryKey(Mockito.<Integer>any())).thenReturn(weatherInfo);

        RequestInfo input = new RequestInfo();
        input.setDate("2020-03-01");
        input.setForeignKey(1);
        input.setLat(10.0d);
        input.setLon(10.0d);
        input.setPinCode("Pin Code");
        input.setPrimaryKey(1);

        // Act
        WeatherInfo actualCheckDBResult = savedWeatherInfo.checkDB(input);

        // Assert
        verify(pinAndDateRepository).findByPinCodeAndDate(eq("Pin Code"), eq("2020-03-01"));
        verify(weatherInfoRepository).findByPrimaryKey(eq(1));
        assertSame(weatherInfo, actualCheckDBResult);
    }
}
