package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This program will start the springboot application
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-29
 */

@SpringBootApplication
public class StockServiceApplication {


    /**
     * This is the main method to start the springboot application
     *
     * @param args - args
     */
    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }
}
