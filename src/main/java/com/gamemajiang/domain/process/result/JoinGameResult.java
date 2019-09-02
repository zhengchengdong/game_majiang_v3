package com.gamemajiang.domain.process.result;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.Player;

public class JoinGameResult {

	public static JoinGameResult success(Game game, String playerId) {
		JoinGameResult result = new JoinGameResult();
		result.success = true;
		result.gameId = game.getId();
		result.playersCount = game.countPlayers();
		Player player = game.findPlayer(playerId);
		if (player != null) {
			result.joinedPlayerId = player.getId();
		}
		return result;
	}

	public static JoinGameResult unsuccess(Game game) {
		JoinGameResult result = new JoinGameResult();
		result.playersCount = game.countPlayers();
		return result;
	}

	private boolean success;

	private String gameId;

	private String joinedPlayerId;

	private int playersCount;

	public boolean isSuccess() {
		return success;
	}

	public String getGameId() {
		return gameId;
	}

	public String getJoinedPlayerId() {
		return joinedPlayerId;
	}

	public int getPlayersCount() {
		return playersCount;
	}

}
