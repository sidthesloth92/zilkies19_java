package io.zilker.fantasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="io.zilker.fantasy.controllers")
public class FantasyLeagueBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyLeagueBootApplication.class, args);
	}
}
