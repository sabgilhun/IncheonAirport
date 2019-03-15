package ado.sabgil.incheonariport.model;

import android.text.TextUtils;

import java.util.Objects;

import ado.sabgil.incheonariport.remote.openapi.response.PassengerDeparturesWItem;
import androidx.annotation.NonNull;

public class DepartureWeather {

    @NonNull
    private final String dayOfWeek;

    @NonNull
    private final String weatherImageLink;

    @NonNull
    private final String humidity;

    @NonNull
    private final String wind;

    @NonNull
    private final String maxTemperature;

    @NonNull
    private final String minTemperature;

    @NonNull
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    @NonNull
    public String getWeatherImageLink() {
        return weatherImageLink;
    }

    @NonNull
    public String getHumidity() {
        return humidity;
    }

    @NonNull
    public String getWind() {
        return wind;
    }

    @NonNull
    public String getMaxTemperature() {
        return maxTemperature;
    }

    @NonNull
    public String getMinTemperature() {
        return minTemperature;
    }

    private DepartureWeather(@NonNull String dayOfWeek,
                             @NonNull String weatherImageLink,
                             @NonNull String humidity,
                             @NonNull String wind,
                             @NonNull String maxTemperature,
                             @NonNull String minTemperature) {
        this.dayOfWeek = dayOfWeek;
        this.weatherImageLink = weatherImageLink;
        this.humidity = humidity;
        this.wind = wind;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public static DepartureWeather from(PassengerDeparturesWItem item) {
        return new DepartureWeather(
                checkNull(item.getDayOfWeek(), ""),
                checkNull(item.getWeatherImage(), ""),
                checkNull(item.getHumidity(), "%"),
                checkNull(item.getWind(), "m/s"),
                checkNull(item.getMaxTemperature(), "℃"),
                checkNull(item.getMinTemperature(), "℃")
        );
    }

    private static String checkNull(String target, String suffix) {
        return TextUtils.isEmpty(target) ? "정보 없음" : target + suffix;
    }

    @Override
    public String toString() {
        return "DepartureWeather{" +
                "dayOfWeek='" + dayOfWeek + '\'' +
                ", weatherImageLink='" + weatherImageLink + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", maxTemperature='" + maxTemperature + '\'' +
                ", minTemperature='" + minTemperature + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartureWeather weather = (DepartureWeather) o;
        return Objects.equals(dayOfWeek, weather.dayOfWeek) &&
                Objects.equals(weatherImageLink, weather.weatherImageLink) &&
                Objects.equals(humidity, weather.humidity) &&
                Objects.equals(wind, weather.wind) &&
                Objects.equals(maxTemperature, weather.maxTemperature) &&
                Objects.equals(minTemperature, weather.minTemperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, weatherImageLink, humidity,
                wind, maxTemperature, minTemperature);
    }
}
