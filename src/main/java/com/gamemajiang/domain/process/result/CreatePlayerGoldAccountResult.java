package com.gamemajiang.domain.process.result;

public class CreatePlayerGoldAccountResult {

	public static CreatePlayerGoldAccountResult success(String playerId) {
		CreatePlayerGoldAccountResult result = new CreatePlayerGoldAccountResult();
		result.success = true;
		result.playerId = playerId;
		return result;
	}

	private boolean success;

	private String playerId;

	public boolean isSuccess() {
		return success;
	}

	public String getPlayerId() {
		return playerId;
	}

}
