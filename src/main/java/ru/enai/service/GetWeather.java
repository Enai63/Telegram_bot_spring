package ru.enai.service;


import org.telegram.telegrambots.meta.api.objects.Location;
import ru.enai.model.Weather;


public interface GetWeather {
    Weather getWeather(Location location);
    Weather getWeather(String nameCity);
}
