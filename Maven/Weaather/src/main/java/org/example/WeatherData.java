package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherData {
    private final String city;
    private final String apiKey;

    public WeatherData(String city, String apiKey) {
        this.city = city;
        this.apiKey = apiKey;
    }

    public String getResponse() throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }

    public void extractData() throws IOException {
        String response = getResponse();
        JSONObject json_data = new JSONObject(response);
        JSONArray forecasts = json_data.getJSONArray("list");

        // Extract required data from JSON for the first 5 days
        String name = json_data.getJSONObject("city").getString("name");
        String country = json_data.getJSONObject("city").getString("country");
        System.out.println("================================================================");
        System.out.println("================================================================");
        System.out.println("City: " + name + ", " + country);
        System.out.println("==================================================================================================================================================================");

        for (int i = 0; i < 5; i++) {
            JSONObject forecast = forecasts.getJSONObject(i);
            String date = forecast.getString("dt_txt");
            double temp = forecast.getJSONObject("main").getDouble("temp");
            int humidity = forecast.getJSONObject("main").getInt("humidity");
            double wind_speed = forecast.getJSONObject("wind").getDouble("speed");
            int pressure = forecast.getJSONObject("main").getInt("pressure");
            String weather_description = forecast.getJSONArray("weather").getJSONObject(0).getString("description");
            // convert the temperature from Kelvin to Celsius and round the value to 2 decimal places
            temp = Math.round((temp - 273.15) * 100.0) / 100.0;
            if (weather_description.equals("heavy intensity rain") || weather_description.equals("thunderstorm") || weather_description.equals("heavy intensity drizzle") || weather_description.equals("heavy intensity drizzle rain") || weather_description.equals("heavy shower rain and drizzle") || weather_description.equals("heavy intensity shower rain") || weather_description.equals("ragged shower rain")) {
                System.out.println("Alert: " + weather_description);
            }
            String formatted = String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Date/Time", "Temperature (C)", "Humidity (%)", "Wind Speed", "Pressure", "Weather Description");
            formatted += String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", date, temp + " C", humidity + " %", wind_speed + " m/s", pressure + " hPa", weather_description);
            System.out.println(formatted);

        }
        System.out.println("==================================================================================================================================================================");
    }
}
