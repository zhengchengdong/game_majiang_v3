package com.gamemajiang.adapter.persistence.mongodb.dto;

import com.gamemajiang.domain.bean.Player;

public class PlayerDTO {

	private String id;

	private boolean ready;

	public PlayerDTO() {

	}

	public Player toEntity() {
		Player player = new Player();
		player.setId(id);
		player.setReady(ready);
		return player;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

}
