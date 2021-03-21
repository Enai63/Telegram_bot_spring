package ru.enai.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("weather")
    private List<Object> weather = new ArrayList<>();
    @JsonProperty("main")
    private Main main;
    @JsonProperty("visibility")
    private int visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("snow")
    private Snow snow;
    @JsonProperty("rain")
    private Rain rain;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private String date;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String nameCity;
    @JsonProperty("cod")
    private int cod;

    public Weather() {
    }

    public Weather(List<Object> weather, Main main, int visibility, Wind wind, Snow snow, Rain rain, Clouds clouds,
                   String date, Sys sys, String timezone, Long id, String nameCity, int cod) {
        this.weather = weather;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.snow = snow;
        this.rain = rain;
        this.clouds = clouds;
        this.date = date;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.nameCity = nameCity;
        this.cod = cod;
    }

    @Override
    public String toString() {
        String description = getDescriptionWeather(weather); //Move toString or not
        if (rain == null && snow == null) {
            return "Погодные условия: " + description + "\n" +
                    "Место: " + nameCity + "\n" +
                    "Дата: " + date + "\n" +
                    "Видимость: " + visibility + "m" + "\n" +
                    main + "\n" +
                    wind + "\n" +
                    clouds + "\n" +
                    sys + "\n";
        }
        else if(rain != null) {
            return "Погодные условия: " + description + "\n" +
                    "Место: " + nameCity + "\n" +
                    "Дата: " + date + "\n" +
                    "Видимость: " + visibility + "m" + "\n" +
                    main + "\n" +
                    wind + "\n" +
                    clouds + "\n" +
                    sys + "\n" +
                    rain;
        }
        else if (snow != null) {
            return "Погодные условия: " + description + "\n" +
                    "Место: " + nameCity + "\n" +
                    "Дата: " + date + "\n" +
                    "Видимость: " + visibility + "m" + "\n" +
                    main + "\n" +
                    wind + "\n" +
                    clouds + "\n" +
                    sys + "\n" +
                    snow;
        } else {
            return "Погодные условия: " + description + "\n" +
                    "Место: " + nameCity + "\n" +
                    "Дата: " + date + "\n" +
                    "Видимость: " + visibility + "m" + "\n" +
                    main + "\n" +
                    wind + "\n" +
                    clouds + "\n" +
                    sys + "\n" +
                    rain + "\n" +
                    snow;
        }
    }

    private String getDescriptionWeather(List<Object> weather) {
        String[] arrDescription = weather.get(0).toString().split(", ");
        return arrDescription[2].substring(12);
    }


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Main {
        private int temp;
        private float feels_like;
        private int temp_min;
        private int temp_max;
        private int pressure;
        private int humidity;

        public Main() {
        }

        public Main(int temp, float feels_like, int temp_min, int temp_max, int pressure, int humidity) {
            this.temp = temp;
            this.feels_like = feels_like;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "Температура: " + temp + "℃" + "\n" +
                    "Ощущается как: " + feels_like + "℃" + "\n" +
                    "Минимальная температура: " + temp_min + "℃" + "\n" +
                    "Максимальня температура: " + temp_max + "℃" + "\n" +
                    "Атмосферное давление: " + pressure + "hPa" + "\n" +
                    "Влажность воздуха: " + humidity + "%";
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Wind {
        private int speed;
        private int deg;

        public Wind() {
        }

        public Wind(int speed, int deg) {
            this.speed = speed;
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Ветер, направление: " + deg + "deg" + " скорость: " + speed + "m\\s";
        }
    }



    @Getter
    @Setter
    private static class Clouds {
        private String all;

        public Clouds() {
        }

        public Clouds(String all) {
            this.all = all;

        }

        @Override
        public String toString() {
            return  "Облачность: " + all + "%";
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Sys {
        private int type;
        private Long id;
        private String country;
        private int sunrise;
        private int sunset;

        public Sys() {
        }

        public Sys(int type, Long id, String country, int sunrise, int sunset) {
            this.type = type;
            this.id = id;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Страна: " + country + "\n" +
                    "Рассвет: " + sunrise + "\n" +
                    "Закат: " + sunset;
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Snow {
        @JsonProperty("1h")
        private String snow1h;
        @JsonProperty("3h")
        private String snow3h;

        public Snow() {
        }

        public Snow(String snow1h, String snow3h) {
            this.snow1h = snow1h;
            this.snow3h = snow3h;
        }

        @Override
        public String toString() {
            if (snow1h != null && snow3h == null) return "Снег 1 час: " + snow1h + "mm";
            if (snow1h == null && snow3h != null) return "Снег 3 часа: " + snow3h + "mm";
            return "Осадков нет";
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Rain {
        @JsonProperty("1h")
        private String rain1h;
        @JsonProperty("3h")
        private String rain3h;

        public Rain() {
        }

        public Rain(String rain1h, String rain3h) {
            this.rain1h = rain1h;
            this.rain3h = rain3h;
        }


        @Override
        public String toString() {
            if (rain1h != null && rain3h == null) return "Дождь 1 час: " + rain1h + "mm";
            if (rain1h == null && rain3h != null) return "Дождь 3 часа:  " + rain3h + "mm";
            return "Осадков нет";
        }
    }
}
