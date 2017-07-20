package com.sumo.traffic.Models;

/**
 * Created by kixkikx on 1/11/2017.
 */
public class CurrentWeather extends WeatherForecast {
    private final float mTemperature;  // Current temperature.

    public CurrentWeather(final String locationName,
                          final long timestamp,
                          final String description,
                          final float temperature,
                          final float minimumTemperature,
                          final float maximumTemperature) {

        super(locationName, timestamp, description, minimumTemperature, maximumTemperature);
        mTemperature = temperature;
    }

    public float getTemperature() {
        return mTemperature;
    }
}
