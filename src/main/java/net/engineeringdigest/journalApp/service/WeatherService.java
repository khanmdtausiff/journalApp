package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.respone.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse!=null){
            return weatherResponse;
        }else {
            String finalApi = appCache.appCacheMap.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.API_KEY, apiKey).replace(PlaceHolder.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body!=null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }

    //Way to consume POST API

//    public WeatherResponse getWeather1(String city){
//        String finalApi = API.replace("API_KEY", apiKey).replace("CITY", city);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "value");
//        User user = User.builder().username("Tausiff").password("Tausiff").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
//
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }

    //Way to consume POST API
}
