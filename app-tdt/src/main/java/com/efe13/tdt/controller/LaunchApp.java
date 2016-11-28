package com.efe13.tdt.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaunchApp {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel( Level.ERROR );
		
		SpringApplication.run( LaunchApp.class, args );
	}
}