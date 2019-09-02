package com.gamemajiang.adapter.persistence.mongodb.dto;

import com.gamemajiang.domain.bean.PlayerGoldAccount;

public class PlayerGoldAccountDTO {

	private String id;

	private String playerId;

	private int balance;

	public PlayerGoldAccountDTO() {

	}

	public PlayerGoldAccount toEntity() {
		PlayerGoldAccount account = new PlayerGoldAccount();
		account.setPlayerId(playerId);
		account.setBalance(balance);
		return account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
