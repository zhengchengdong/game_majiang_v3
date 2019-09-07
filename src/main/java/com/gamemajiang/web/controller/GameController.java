package com.gamemajiang.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping("/create")
	@ResponseBody
	public CreateGameResult create(String playerId, String gameId) {
		return gameService.createGame(playerId, gameId);
	}

	@RequestMapping("/create_pt")
	@ResponseBody
	public CreateGameResult create_pt(String playerId) {// 用于简单压力测试
		long t1 = System.currentTimeMillis();
		CreateGameResult result = gameService.createGame(playerId, UUID.randomUUID().toString());
		System.out.println(System.currentTimeMillis() - t1);
		return result;
	}

	@RequestMapping("/join")
	@ResponseBody
	public JoinGameResult join(String playerId, String gameId) {
		return gameService.joinGame(playerId, gameId);
	}

}
