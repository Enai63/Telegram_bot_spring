package ru.enai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private JsonNode weather;
    private Main main;
    private int visibility;
    private Wind wind;
    private JsonNode snow;
    private JsonNode rain;
    private JsonNode clouds;
    private String date;
    private Sys sys;
    private String timezone;
    private Long id;
    private String nameCity;
    private int code;

    public Weather() {
    }

    @JsonCreator
    public Weather(@JsonProperty("weather") JsonNode weather,
                   @JsonProperty("main") Main main,
                   @JsonProperty("visibility") int visibility,
                   @JsonProperty("wind") Wind wind,
                   @JsonProperty("snow") JsonNode snow,
                   @JsonProperty("rain") JsonNode  rain,
                   @JsonProperty("clouds") JsonNode clouds,
                   @JsonProperty("dt") String date,
                   @JsonProperty("sys") Sys sys,
                   @JsonProperty("timezone") String timezone,
                   @JsonProperty("id") Long id,
                   @JsonProperty("name") String nameCity,
                   @JsonProperty("cod") int cod) {
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
        this.code = cod;
    }

    @Getter
    @Setter
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
            return "Main{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    @Getter
    @Setter
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
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

//    @Getter
//    @Setter
//    private static class Snow {
//
//        private String value;
//
//        public Snow() {
//        }
//
//        public Snow(String  value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return value.toString();
//        }
//    }
//
//    @Getter
//    @Setter
//    private static class Rain {
//
//        private String value;
//
//        public Rain() {
//        }
//
//        public Rain(String value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return value.toString();
//        }
//    }

    @Getter
    @Setter
    private static class Clouds {
        private int clouds;

        public Clouds() {
        }

        public Clouds(int clouds) {
            this.clouds = clouds;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "clouds=" + clouds + "%" +
                    '}';
        }
    }

    @Getter
    @Setter
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
            return "Sys{" +
                    "type=" + type +
                    ", id=" + id +
                    ", countryCode='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weather=" + weather +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", snow=" + snow +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", date='" + date + '\'' +
                ", sys=" + sys +
                ", timezone='" + timezone + '\'' +
                ", id=" + id +
                ", nameCity='" + nameCity + '\'' +
                '}';
    }
}
