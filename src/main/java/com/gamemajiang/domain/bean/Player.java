package com.gamemajiang.domain.bean;

public class Player {

	private String id;

	private boolean ready;

	public Player() {
	}

	public void ready() {
		ready = true;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

}
