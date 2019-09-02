package com.gamemajiang.domain.process.result;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.GameState;
import com.gamemajiang.domain.bean.Player;
import com.gamemajiang.domain.bean.PlayerGoldAccount;

public class CreateGameResult {

	public static CreateGameResult success(Game newGame, PlayerGoldAccount account) {
		CreateGameResult result = new CreateGameResult();
		result.success = true;
		result.newGameId = newGame.getId();
		result.gameState = newGame.getState();
		for (Player player : newGame.getPlayers().values()) {
			result.firstPlayerId = player.getId();
			break;
		}
		result.playerGoldBalance = account.getBalance();
		return result;
	}

	public static CreateGameResult unsuccess() {
		return new CreateGameResult();
	}

	private boolean success;

	private String newGameId;

	private GameState gameState;

	private String firstPlayerId;

	private int playerGoldBalance;

	public boolean isSuccess() {
		return success;
	}

	public String getNewGameId() {
		return newGameId;
	}

	public GameState getGameState() {
		return gameState;
	}

	public String getFirstPlayerId() {
		return firstPlayerId;
	}

	public int getPlayerGoldBalance() {
		return playerGoldBalance;
	}

}
