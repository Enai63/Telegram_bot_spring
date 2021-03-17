package ru.enai.service;


import org.telegram.telegrambots.meta.api.objects.Location;
import ru.enai.model.Weather;

public interface ServiceWeather {
    Weather getWeatherLocation(Location location);
    Weather getWeatherCity(String nameCity);
}
