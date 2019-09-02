package com.gamemajiang.domain.process.result;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.GameState;

public class FinishGameResult {

	public static FinishGameResult success(Game game) {
		FinishGameResult result = new FinishGameResult();
		result.success = true;
		result.finishedGameId = game.getId();
		result.gameState = game.getState();
		return result;
	}

	public static FinishGameResult unsuccess() {
		return new FinishGameResult();
	}

	private boolean success;

	private String finishedGameId;

	private GameState gameState;

	public boolean isSuccess() {
		return success;
	}

	public String getFinishedGameId() {
		return finishedGameId;
	}

	public GameState getGameState() {
		return gameState;
	}

}
