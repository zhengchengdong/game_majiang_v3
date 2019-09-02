package com.gamemajiang.adapter.persistence.mongodb.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.GameState;

public class GameDTO {

	private String id;

	private Map<String, PlayerDTO> players;

	private GameState state;

	public GameDTO() {
		players = new HashMap<>();
	}

	public Game toEntity() {
		Game game = new Game();
		game.setId(id);
		game.setState(state);
		for (Entry entry : players.entrySet()) {
			game.getPlayers().put((String) entry.getKey(), ((PlayerDTO) entry.getValue()).toEntity());
		}
		return game;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, PlayerDTO> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, PlayerDTO> players) {
		this.players = players;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

}
