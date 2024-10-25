package com.weatherinfo.Weather.Imformation.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import com.weatherinfo.Weather.Imformation.entity.WeatherInfo;
import com.weatherinfo.Weather.Imformation.service.SavedWeatherInfo;
import com.weatherinfo.Weather.Imformation.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Controller.class, RequestInfo.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ControllerTest {
    @Autowired
    private Controller controller;

    @Autowired
    private RequestInfo requestInfo;

    @MockBean
    private SavedWeatherInfo savedWeatherInfo;

    @MockBean
    private WeatherService weatherService;


    @Test
    void testGetDate() throws Exception {
        // Arrange
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
        when(savedWeatherInfo.checkDB(Mockito.<RequestInfo>any())).thenReturn(weatherInfo);

        RequestInfo requestInfo2 = new RequestInfo();
        requestInfo2.setDate("2020-03-01");
        requestInfo2.setForeignKey(1);
        requestInfo2.setLat(10.0d);
        requestInfo2.setLon(10.0d);
        requestInfo2.setPinCode("Pin Code");
        requestInfo2.setPrimaryKey(1);
        String content = (new ObjectMapper()).writeValueAsString(requestInfo2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/get-weather")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("from DB \n"
                                + "WeatherInfo(temp=10.0, feels_like=10.0, temp_min=10.0, temp_max=10.0, pressure=1, humidity=1, sea_level=1,"
                                + " grnd_level=1, windSpeed=10.0, windDeg=1, WindGust=10.0)"));
    }
}
