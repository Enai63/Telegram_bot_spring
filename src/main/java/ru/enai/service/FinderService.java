package ru.enai.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;

import ru.enai.model.Weather;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


@Service
public class FinderService implements ServiceWeather {

    private Weather weather;
    @Value("${weather.client.token}")
    private String WEATHER_TOKEN;
    private String baseURL = "http://api.openweathermap.org/data/2.5/weather";
    private StringBuilder stringBuilder = new StringBuilder(baseURL);

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(60))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public FinderService(Weather weather) {
        this.weather = weather;
    }


    @Override
    public Weather getWeatherLocation(Location location) {
        stringBuilder
                .append("?lat=")
                .append(location.getLatitude())
                .append("&lon=")
                .append(location.getLongitude())
                .append("&units=metric")
                .append("&appid=")
                .append(WEATHER_TOKEN)
                .append("&lang=ru");

        String URL = stringBuilder.toString();
        System.out.println(URL);
        System.out.println("Location");
        stringBuilder.delete(baseURL.length(), stringBuilder.length());



        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            weather = new ObjectMapper()
                    .readerFor(Weather.class)
                    .readValue(response.body());

        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return weather;
    }

    @Override
    public Weather getWeatherCity(String nameCity) {
        stringBuilder
                .append("?q=")
                .append(nameCity)
                .append("&appid=")
                .append(WEATHER_TOKEN)
                .append("&lang=ru")
        ;

        String URL = stringBuilder.toString();
        System.out.println("Name city");
        System.out.println(URL);
        stringBuilder.delete(baseURL.length(), stringBuilder.length() - 1);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            weather = new ObjectMapper()
                    .readerFor(Weather.class)
                    .readValue(response.body());

        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return weather;
    }


}
