package ru.enai.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;


import java.net.http.HttpClient;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@Component
public class WeatherService {
    private String URL = "https://gridforecast.com/api/v1/forecast/%f;%f/%s?api_token=%s";

    private Date date = new Date();
    @Value("${weather.client.token}")
    private String WEATHER_TOKEN;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private String getCurrentData() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        return String.format("%d%d%d%d%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public void getURL(double lat, double lon){
        //System.out.println("Hello " + lat + " " + lon);
        String newURL = String.format(URL, lat, lon, getCurrentData(), WEATHER_TOKEN);
        //System.out.println(newURL);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(formatter.format(date));

    }
//    public void getWeather(double lat, double lon) throws IOException, InterruptedException {
//        date = (Date) new GregorianCalendar().getTime();
//        String url = String.format(URL, lat, lon, "202011231100", WEATHER_TOKEN);
//        System.out.println(url);
//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create(url))
//                .build();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(date);
//        System.out.println(response.body());
//    }
}
