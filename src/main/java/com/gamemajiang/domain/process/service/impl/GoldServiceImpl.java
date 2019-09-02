package com.gamemajiang.domain.process.service.impl;

import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;
import com.gamemajiang.domain.process.service.GoldService;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

public class GoldServiceImpl implements GoldService {

	private RootAggregateRepository rootAggregateRepository;

	public GoldServiceImpl(RootAggregateRepository rootAggregateRepository) {
		this.rootAggregateRepository = rootAggregateRepository;
	}

	@Override
	public CreatePlayerGoldAccountResult createPlayerGoldAccount(String playerId) {
		PlayerGoldAccountAggregateRepository playerGoldAccountAggregateRepository = rootAggregateRepository
				.get(PlayerGoldAccountAggregateRepository.class.getName());

		PlayerGoldAccount account = new PlayerGoldAccount();
		account.setPlayerId(playerId);

		playerGoldAccountAggregateRepository.put(account);
		return CreatePlayerGoldAccountResult.success(playerId);

	}

	@Override
	public DepositResult deposit(String playerId, int gold) {
		PlayerGoldAccountAggregateRepository playerGoldAccountAggregateRepository = rootAggregateRepository
				.get(PlayerGoldAccountAggregateRepository.class.getName());

		PlayerGoldAccount account = playerGoldAccountAggregateRepository.findByKey(playerId);
		account.deposit(gold);

		return DepositResult.success(account);

	}

}
