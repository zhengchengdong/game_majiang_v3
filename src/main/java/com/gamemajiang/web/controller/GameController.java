package com.gamemajiang.web.controller;

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

	@RequestMapping("/join")
	@ResponseBody
	public JoinGameResult join(String playerId, String gameId) {
		return gameService.joinGame(playerId, gameId);
	}

}
