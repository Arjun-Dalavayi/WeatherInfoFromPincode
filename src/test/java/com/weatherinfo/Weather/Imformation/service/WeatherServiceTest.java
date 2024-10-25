package com.weatherinfo.Weather.Imformation.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.weatherinfo.Weather.Imformation.apiResponce.LatAndLon;
import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WeatherService.class, RequestInfo.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class WeatherServiceTest {
    @Autowired
    private RequestInfo requestInfo;

    @MockBean
    private SaveDataToDB saveDataToDB;

    @Autowired
    private WeatherService weatherService;

    @Test
    void testGetLanAndLog() throws Exception {
        // Arrange
        RequestInfo requestInfo2 = new RequestInfo();
        requestInfo2.setDate("2020-03-01");
        requestInfo2.setForeignKey(1);
        requestInfo2.setLat(10.0d);
        requestInfo2.setLon(10.0d);
        requestInfo2.setPinCode("Pin Code");
        requestInfo2.setPrimaryKey(1);

        // Act and Assert
        assertThrows(Exception.class, () -> weatherService.getLanAndLog(requestInfo2));
    }


    @Test
    void testGetWeatherInfo() throws Exception {
        // Arrange
        LatAndLon latAndLon = new LatAndLon();

        RequestInfo requestInfo2 = new RequestInfo();
        requestInfo2.setDate("2020-03-01");
        requestInfo2.setForeignKey(1);
        requestInfo2.setLat(10.0d);
        requestInfo2.setLon(10.0d);
        requestInfo2.setPinCode("Pin Code");
        requestInfo2.setPrimaryKey(1);

        // Act and Assert
        assertThrows(Exception.class, () -> weatherService.getWeatherInfo(latAndLon, requestInfo2));
    }
}
