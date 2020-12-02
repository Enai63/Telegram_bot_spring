package ru.enai.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import ru.enai.model.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FinderService implements ServiceWeather {
    @Value("${weather.client.token}")
    private String WEATHER_TOKEN;

    private String URL = "https://gridforecast.com/api/v1/forecast/%f;%f/%s?api_token=%s";
    private Weather weather;


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public FinderService(Weather weather) {
        this.weather = weather;
    }

    public void getParams(double lat, double lon) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");;
        String url = String.format(URL, lat, lon, formatter.format(new Date()), WEATHER_TOKEN);
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        System.out.println(url);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            //JacksonJsonParser mapper = new JacksonJsonParser();
            //Map<String, Object> map = mapper.parseMap(response.body());
            weather = new ObjectMapper()
                    .readerFor(Weather.class)
                    .readValue(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
