package ru.enai.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherLocation {
    List<String> weather = new ArrayList<>();
    @JsonSetter("weather")
    public void setWeather(List<String> weather) {
        this.weather = weather;
    }
}
