package com.gamemajiang.adapter.persistence;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;

public interface GamePersistence {

	void createGame(CreateGameResult createGameResult);

	void joinGame(JoinGameResult joinGameResult);

	void readyForGame(ReadyForGameResult readyForGameResult);

	void finishGame(FinishGameResult finishGameResult);

	Game load(String gameId);

}
