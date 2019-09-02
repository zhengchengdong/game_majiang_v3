package com.gamemajiang.domain.process.service;

import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;

public interface GameService {

	CreateGameResult createGame(String creatorPlayerId, String gameId);

	JoinGameResult joinGame(String playerId, String gameId);

	ReadyForGameResult readyForGame(String playerId, String gameId);

	FinishGameResult finishGame(String gameId);

}
