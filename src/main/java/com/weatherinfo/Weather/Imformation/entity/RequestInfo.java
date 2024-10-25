package com.weatherinfo.Weather.Imformation.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@ToString
@Component
public class RequestInfo {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer primaryKey;
    private String pinCode;
    private String date;
    private double lat;
    private double lon;

    @ToString.Exclude
    private Integer foreignKey;

}
