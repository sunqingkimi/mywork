package weather_zuul;

import com.fasterxml.jackson.annotation.JsonProperty;


import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LiveResult {

    private LiveData[] results;

    @Getter
    @Setter
    public static class LiveData extends Authenticator.Result {
        private LiveWeatherData now;
    }

    @Getter
    @Setter
    public static class LiveWeatherData {

        private String text;
        private int code;
        private float temperature;
        @JsonProperty("feels_like")
        private float feelsLike;

        private float pressure;

        private float humidity;

        private float visibility;

        @JsonProperty("wind_direction")
        private String windDirection;

        @JsonProperty("wind_direction_degree")
        private String windDirectionDegree;

        @JsonProperty("wind_speed")
        private float windSpeed;

        @JsonProperty("wind_scale")
        private int windScale;

        private float clouds;

        @JsonProperty("dew_point")
        private float dewPoint;
    }
}
