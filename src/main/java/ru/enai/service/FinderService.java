package ru.enai.Service;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Location;

import ru.enai.model.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
public class FinderService implements ServiceWeather {
    @Value("${weather.client.token}")
    private String WEATHER_TOKEN;
    private final String URL = "api.openweathermap.org/data/2.5/weather";
    private final StringBuilder stringBuilder = new StringBuilder(URL);


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    @Override
    public Weather getWeatherLocation(Location location) {
        //String url = String.format(URL, location.getLatitude(), location.getLongitude(),  WEATHER_TOKEN);
        stringBuilder
                .append("?lat=")
                .append(location.getLatitude())
                .append("&lon=")
                .append(location.getLongitude())
                .append("&units=metric")
                .append("&appid=")
                .append(WEATHER_TOKEN)
                .append("&lang=ru");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(stringBuilder.toString()))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Weather getWeatherCity(String nameCity) {
        return null;
    }


}
