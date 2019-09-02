package com.gamemajiang.domain.process.service;

import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;

public interface GoldService {

	CreatePlayerGoldAccountResult createPlayerGoldAccount(String playerId);

	DepositResult deposit(String playerId, int gold);

}
