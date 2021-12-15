package com.kalugin.net.interactor;

import com.kalugin.net.helper.JsonHelper;
import com.kalugin.net.service.WeatherNetworkService;

import java.io.IOException;
import java.util.Map;

public class WeatherNetworkInteractor {
    private final WeatherNetworkService weatherNetworkService = new WeatherNetworkService();
    private final JsonHelper jsonHelper = new JsonHelper();

    public Map<String, String> get(String city) throws IOException {
        return jsonHelper.parseJson(weatherNetworkService.get(city));
    }
}
