package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.cricalertbe.beans.Player;
import io.ztech.cricalertbe.delegates.PlayerDelegate;

@RestController
@RequestMapping("/CricAlertBE/players")
public class PlayerController {
	
	@GetMapping("/{id}")
	public Player fetchPlayer(@PathVariable ("id") int id) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		return playerDelegate.fetchPlayer(id);
	}

	@GetMapping("/user/{id}")
	public ArrayList<Player> fetchPlayers(@PathVariable ("id") int userId) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		return playerDelegate.fetchPlayers(userId);
	}
	
	@PostMapping("/")
	public void addNewPlayer(@RequestBody Player player) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		playerDelegate.addNewPlayer(player);
	}
	
	@PutMapping("/{id}")
	public void updatePlayer(@RequestBody Player player) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		playerDelegate.updatePlayer(player);
	}
	
	@DeleteMapping("/{id}")
	public void removePlayer(@RequestBody Player player) {
		PlayerDelegate playerDelegate = new PlayerDelegate();
		playerDelegate.removePlayer(player);
	}
}
