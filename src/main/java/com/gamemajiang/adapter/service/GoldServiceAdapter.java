package com.gamemajiang.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamemajiang.adapter.persistence.PlayerGoldAccountPersistence;
import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;
import com.gamemajiang.domain.process.service.GoldService;
import com.gamemajiang.domain.process.service.impl.GoldServiceImpl;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

@Component("goldService")
public class GoldServiceAdapter implements GoldService {

	@Autowired
	private GoldServiceImpl goldServiceImpl;

	@Autowired
	private RootAggregateRepository rootAggregateRepository;

	@Autowired
	private PlayerGoldAccountPersistence playerGoldAccountPersistence;

	@Override
	public CreatePlayerGoldAccountResult createPlayerGoldAccount(String playerId) {
		CreatePlayerGoldAccountResult createPlayerGoldAccountResult = goldServiceImpl.createPlayerGoldAccount(playerId);
		persistAggregate(createPlayerGoldAccountResult);
		updateForQuery(createPlayerGoldAccountResult);
		return createPlayerGoldAccountResult;
	}

	@Override
	public DepositResult deposit(String playerId, int gold) {
		checkAndLoadPlayerGoldAccountAggregate(playerId);
		DepositResult depositResult = goldServiceImpl.deposit(playerId, gold);
		persistAggregate(depositResult);
		updateForQuery(depositResult);
		return depositResult;
	}

	private PlayerGoldAccount checkAndLoadPlayerGoldAccountAggregate(String playerId) {
		PlayerGoldAccountAggregateRepository playerGoldAccountAggregateRepository = rootAggregateRepository
				.get(PlayerGoldAccountAggregateRepository.class.getName());

		PlayerGoldAccount account = playerGoldAccountAggregateRepository.findByKey(playerId);
		if (account == null) {
			synchronized (playerGoldAccountAggregateRepository) {
				account = playerGoldAccountAggregateRepository.findByKey(playerId);
				if (account == null) {
					account = playerGoldAccountPersistence.load(playerId);
					playerGoldAccountAggregateRepository.put(account);
				}
			}
		}
		return account;
	}

	private void persistAggregate(CreatePlayerGoldAccountResult createPlayerGoldAccountResult) {
		playerGoldAccountPersistence.createPlayerGoldAccount(createPlayerGoldAccountResult);
	}

	private void persistAggregate(DepositResult depositResult) {
		playerGoldAccountPersistence.deposit(depositResult);
	}

	private void updateForQuery(CreatePlayerGoldAccountResult createPlayerGoldAccountResult) {
		// TODO Auto-generated method stub
	}

	private void updateForQuery(DepositResult depositResult) {
		// TODO Auto-generated method stub
	}

}
