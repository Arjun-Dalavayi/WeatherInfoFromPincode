package com.weatherinfo.Weather.Imformation.repository;

import com.weatherinfo.Weather.Imformation.entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PinAndDateRepository extends JpaRepository<RequestInfo,Integer> {
     RequestInfo findByPinCodeAndDate(String pinCode,String date);
}
