package ado.sabgil.incheonariport.remote.openapi.response;

import android.text.TextUtils;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import androidx.annotation.NonNull;

@Root(name = "item")
public class DeparturesWeatherItem {

    @Element
    private String airline;

    @Element
    private String flightId;

    @Element
    private String scheduleDateTime;

    @Element
    private String estimatedDateTime;

    @Element
    private String airport;

    @Element(name = "gatenumber")
    private String gateNumber;

    @Element(required = false)
    private String remark;

    @Element
    private String airportCode;

    @Element(name = "yoil", required = false)
    private String dayOfWeek;

    @Element(name = "chkinrange", required = false)
    private String checkInRange;

    @Element(name = "himidity", required = false)
    private String humidity;

    @Element(name = "wimage", required = false)
    private String weatherImage;

    @Element(required = false)
    private String wind;

    @Element(name = "maxtem", required = false)
    private String maxTemperature;

    @Element(name = "mintem", required = false)
    private String minTemperature;

    @Element(name = "terminalid")
    private String terminalId;

    public String getAirline() {
        return airline;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public String getEstimatedDateTime() {
        return estimatedDateTime;
    }

    public String getAirport() {
        return airport;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public String getRemark() {
        return remark;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getCheckInRange() {
        return checkInRange;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeatherImage() {
        return weatherImage;
    }

    public String getWind() {
        return wind;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getTerminalId() {
        return terminalId;
    }

    @NonNull
    @Override
    public String toString() {
        return "DeparturesWeatherItem{" +
                "airline='" + airline  + '\'' +
                ", flightId='" + flightId + '\'' +
                ", scheduleDateTime='" + scheduleDateTime + '\'' +
                ", estimatedDateTime='" + estimatedDateTime + '\'' +
                ", airport='" + airport + '\'' +
                ", gateNumber='" + gateNumber + '\'' +
                ", remark='" + (TextUtils.isEmpty(remark) ? remark : "null") + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", dayOfWeek='" + (TextUtils.isEmpty(dayOfWeek) ? dayOfWeek : "null") + '\'' +
                ", checkInRange='" + (TextUtils.isEmpty(checkInRange) ? checkInRange : "null") + '\'' +
                ", humidity='" + (TextUtils.isEmpty(humidity) ? humidity : "null") + '\'' +
                ", weatherImage='" + (TextUtils.isEmpty(weatherImage) ? weatherImage : "null") + '\'' +
                ", wind='" + (TextUtils.isEmpty(wind) ? wind : "null") + '\'' +
                ", maxTemperature='" + (TextUtils.isEmpty(maxTemperature) ? maxTemperature : "null") + '\'' +
                ", minTemperature='" + (TextUtils.isEmpty(minTemperature) ? minTemperature : "null") + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }
}