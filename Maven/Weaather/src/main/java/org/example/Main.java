package org.example;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.println("================================================================");
            System.out.println("Welcome to the Weather App!");
            System.out.println("Please select an option:");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (LoginSystem.login()) {
                        System.out.println("Login successful. ");
                        System.out.println("================================================================");
                        System.out.println("Enter city name: ");
                        String city = scanner.nextLine();
                        String apiKey = "8a787b1c916f458ebaa3bf9e8a40c0e9";
                        WeatherData weatherData = new WeatherData(city, apiKey);
                        weatherData.extractData();
                    }
                    break;

                case 2:
                    if (LoginSystem.signUp()) {
                        System.out.println("Sign up successful. ");
                        System.out.println("================================================================");

                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("================================================================");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("================================================================");
            }
        }
    }
}
