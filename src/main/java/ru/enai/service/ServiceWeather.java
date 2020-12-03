package ru.enai.service;


import ru.enai.model.Weather;

public interface ServiceWeather {
    Weather getWeather(double lat, double lon);
}
