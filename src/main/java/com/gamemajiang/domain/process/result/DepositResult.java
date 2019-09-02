package com.gamemajiang.domain.process.result;

import com.gamemajiang.domain.bean.PlayerGoldAccount;

public class DepositResult {

	public static DepositResult success(PlayerGoldAccount account) {
		DepositResult result = new DepositResult();
		result.success = true;
		result.playerId = account.getPlayerId();
		result.balanceAfter = account.getBalance();
		return result;
	}

	private boolean success;

	private String playerId;

	private int balanceAfter;

	public boolean isSuccess() {
		return success;
	}

	public String getPlayerId() {
		return playerId;
	}

	public int getBalanceAfter() {
		return balanceAfter;
	}

}
