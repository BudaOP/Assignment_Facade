package com.example;

import java.io.IOException;

public class ApiFacadeDemo {
    public static void main(String[] args) {
        ApiFacade facade = new ApiFacade();
        
        try {
            // chucknorris
            String joke = facade.getAttributeValueFromJson(
                "https://api.chucknorris.io/jokes/random", 
                "value");
            System.out.println("Random Joke: " + joke);

            // fxrates
            String rates = facade.getAttributeValueFromJson(
                "https://api.fxratesapi.com/latest", 
                "rates");
            System.out.println("\nRates: " + rates.split(",")[0] + "..."); // otherwise too long

            // nhl
            String gamesToday = facade.getAttributeValueFromJson(
                "https://api-web.nhle.com/v1/schedule/now",
                "numberOfGames");
            
            System.out.println("Games today: " + (gamesToday != null ? gamesToday : "0"));
            
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}