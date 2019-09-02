package com.gamemajiang.adapter.persistence;

import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;

public interface PlayerGoldAccountPersistence {

	void createGame(CreateGameResult createGameResult);

	void createPlayerGoldAccount(CreatePlayerGoldAccountResult createPlayerGoldAccountResult);

	void deposit(DepositResult depositResult);

	PlayerGoldAccount load(String playerId);

}
