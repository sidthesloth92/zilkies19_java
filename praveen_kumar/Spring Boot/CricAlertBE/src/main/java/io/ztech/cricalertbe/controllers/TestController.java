package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.cricalertbe.beans.Player;
import io.ztech.cricalertbe.beans.User;
import io.ztech.cricalertbe.delegates.PlayerDelegate;

@RestController
@RequestMapping("/CricAlertBE")
public class TestController {
	
	@RequestMapping("/test")
	public String test() {
		return "HelloWorld";
	}
	
	@RequestMapping("/Players")
	public ArrayList<Player> fetchPlayers(@RequestBody User user) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		return playerDelegate.fetchPlayers(user);
	}
}
