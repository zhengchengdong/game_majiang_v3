package com.gamemajiang.domain.bean;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private String id;

	private int majiangPlayersCount = 4;

	private Map<String, Player> players;

	private GameState state;

	public Game() {
		players = new HashMap<>();
		state = GameState.watingToStart;
	}

	public boolean playerJoin(String playerId) {

		if (!state.equals(GameState.watingToStart)) {
			return false;
		}

		if (players.size() >= majiangPlayersCount) {
			return false;
		}

		if (players.containsKey(playerId)) {
			return false;
		}

		Player player = new Player();
		player.setId(playerId);
		players.put(playerId, player);
		return true;
	}

	public boolean playerReady(String playerId) {
		if (!state.equals(GameState.watingToStart)) {
			return false;
		}
		Player readyPlayer = players.get(playerId);
		if (readyPlayer.isReady()) {
			return false;
		}
		readyPlayer.ready();

		boolean allPlayersReady = true;
		for (Player player : players.values()) {
			if (!player.isReady()) {
				allPlayersReady = false;
				break;
			}
		}
		if (allPlayersReady) {
			state = GameState.ongoing;
		}
		return true;
	}

	public boolean finish() {
		if (state.equals(GameState.ongoing)) {
			state = GameState.finished;
			return true;
		} else {
			return false;
		}
	}

	public Player findPlayer(String playerId) {
		return players.get(playerId);
	}

	public int countPlayers() {
		return players.size();
	}

	public boolean isPlayerReady(String playerId) {
		return players.get(playerId).isReady();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

}
