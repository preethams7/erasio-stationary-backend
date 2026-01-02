package com.hospital.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hospital.base.restaurant.food.FoodService;


@SpringBootApplication
public class Base 
{
     public static void main(String[] args) 
     {
    	 SpringApplication.run(Base.class, args);
    	
     }
}