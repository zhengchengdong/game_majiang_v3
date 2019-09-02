package com.gamemajiang.domain.process.result;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.GameState;

public class ReadyForGameResult {

	public static ReadyForGameResult success(Game game, String playerId) {
		ReadyForGameResult result = new ReadyForGameResult();
		result.success = true;
		result.playerId = playerId;
		result.ready = game.isPlayerReady(playerId);
		result.gameId = game.getId();
		result.gameState = game.getState();
		return result;
	}

	public static ReadyForGameResult unsuccess(Game game, String playerId) {
		ReadyForGameResult result = new ReadyForGameResult();
		result.playerId = playerId;
		result.ready = game.isPlayerReady(playerId);
		result.gameId = game.getId();
		result.gameState = game.getState();
		return result;
	}

	private boolean success;

	private String playerId;

	private boolean ready;

	private String gameId;

	private GameState gameState;

	public boolean isSuccess() {
		return success;
	}

	public String getPlayerId() {
		return playerId;
	}

	public boolean isReady() {
		return ready;
	}

	public String getGameId() {
		return gameId;
	}

	public GameState getGameState() {
		return gameState;
	}

}
