package ru.enai.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Weather {
    private int Humidity;         //r %
    private int Temperature;     //t C
    private double TotalNumberOfPrecipitation;   //tp kg/m2
    private float Pressure;        //sp Pa
    private int Rainfall;        //crain 0-5
    private int Storm;           //tstm 0 < ....
    private int HighCloudy;     //hcy %
    private int LowCloudy;      //lcy %
    private int MidlCloudy;     //mcy %
    private float Visibility;     //vis m
    private int ApparentTemperature; //aptmp t
    private double WindSpeed10;        //speed10
    private int WindDegree10;       //degree10

    private Weather() {
    }

    @Override
    public String toString() {
        return "Weather{" +
                "Humidity=" + Humidity +
                ", Temperature=" + Temperature +
                ", TotalNumberOfPrecipitation=" + TotalNumberOfPrecipitation +
                ", Pressure=" + Pressure +
                ", Rainfall=" + Rainfall +
                ", Storm=" + Storm +
                ", HighCloudy=" + HighCloudy +
                ", LowCloudy=" + LowCloudy +
                ", MidlCloudy=" + MidlCloudy +
                ", Visibility=" + Visibility +
                ", ApparentTemperature=" + ApparentTemperature +
                ", WindSpeed10=" + WindSpeed10 +
                ", WindDegree10=" + WindDegree10 +
                '}';
    }

    @JsonCreator
    public Weather (
            @JsonProperty("speed10") double WindSpeed10,
            @JsonProperty("degree10") int WindDegree10,
            @JsonProperty("speed20") double WindSpeed20,
            @JsonProperty("degree20") int WindDegree20,
            @JsonProperty("speed30") double WindSpeed30,
            @JsonProperty("degree30") int WindDegree30,
            @JsonProperty("speed40") double WindSpeed40,
            @JsonProperty("degree40") int WindDegree40,
            @JsonProperty("speed50") double WindSpeed50,
            @JsonProperty("degree50") int WindDegree50,
            @JsonProperty("speed80") double WindSpeed80,
            @JsonProperty("degree80") int WindDegree80,
            @JsonProperty("speed100") double WindSpeed100,
            @JsonProperty("degree100") int WindDegree100,
            @JsonProperty("t") int Temperature,
            @JsonProperty("tdmax") int TemperatureMax,
            @JsonProperty("tdmin") int TemperatureMix,
            @JsonProperty("aptmp") int ApparentTemperature,
            @JsonProperty("r") int Humidity,
            @JsonProperty("sp") float Pressure,
            @JsonProperty("dswrf") int WaveRadiation,
            @JsonProperty("lcy") int LowCloudy,
            @JsonProperty("mcy") int MidlCloudy,
            @JsonProperty("hcy") int HighCloudy,
            @JsonProperty("tp") double TotalNumberOfPrecipitation,
            @JsonProperty("crain") int Rainfall,
            @JsonProperty("tstm") int Storm,
            @JsonProperty("cprat") int ConvectivePrecipitationRate,
            @JsonProperty("acpcp") int ConvectivePrecipitationWater,
            @JsonProperty("cape") int ConvectiveAvailablePotentialEnergy,
            @JsonProperty("prmsl") long PressureReducedToMSL,
            @JsonProperty("vis") float Visibility,
            @JsonProperty("cpofp") int 	PercentFrozenPrecipitation,
            @JsonProperty("latitude") double Latitude,
            @JsonProperty("longitude") double Longitude
            ) {
        this.Humidity = Humidity;
        this.Temperature = Temperature;
        this.TotalNumberOfPrecipitation = TotalNumberOfPrecipitation;
        this.Pressure = Pressure;
        this.Rainfall = Rainfall;
        this.Storm = Storm;
        this.HighCloudy = HighCloudy;
        this.LowCloudy = LowCloudy;
        this.MidlCloudy = MidlCloudy;
        this.Visibility = Visibility;
        this.ApparentTemperature = ApparentTemperature;
        this.WindSpeed10 = WindSpeed10;
        this.WindDegree10 = WindDegree10;

    }
}
