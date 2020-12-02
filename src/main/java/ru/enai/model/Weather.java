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

    public Weather() {

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
            @JsonProperty("r") int r,
            @JsonProperty("t") int t,
            @JsonProperty("tp") double tp,
            @JsonProperty("sp") float sp,
            @JsonProperty("crain") int crain,
            @JsonProperty("tstm") int tstm,
            @JsonProperty("hcy") int hcy,
            @JsonProperty("lcy") int lcy,
            @JsonProperty("mcy") int mcy,
            @JsonProperty("vis") float vis,
            @JsonProperty("aptmp") int aptmp,
            @JsonProperty("speed10") double speed10,
            @JsonProperty("degree10") int degree10
            ) {
        this.Humidity = r;
        this.Temperature = t;
        this.TotalNumberOfPrecipitation = tp;
        this.Pressure = sp;
        this.Rainfall = crain;
        this.Storm = tstm;
        this.HighCloudy = hcy;
        this.LowCloudy = lcy;
        this.MidlCloudy = mcy;
        this.Visibility = vis;
        this.ApparentTemperature = aptmp;
        this.WindSpeed10 = speed10;
        this.WindDegree10 = degree10;

    }
}
