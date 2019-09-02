package com.gamemajiang.domain.bean;

public class PlayerGoldAccount {

	private String playerId;

	private int balance;

	public PlayerGoldAccount() {
	}

	public void deposit(int gold) {
		balance += gold;
	}

	public void withdraw(int gold) {
		balance -= gold;
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
