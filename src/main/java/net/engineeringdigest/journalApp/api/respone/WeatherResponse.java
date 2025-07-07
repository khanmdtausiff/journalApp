package net.engineeringdigest.journalApp.api.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherResponse {
    private Current current;
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Current{
        private int temperature;
        @JsonProperty("weather_descriptions")  //it maps pojo field weatherDescriptions with json field weather_descriptions
        private List<String> weatherDescriptions;
        @JsonProperty("feelslike")
        private int feelsLike;
    }
}
