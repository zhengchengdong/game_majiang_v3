package com.gamemajiang.repository;

import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.framework.AggregateRepository;

public class PlayerGoldAccountAggregateRepository extends AggregateRepository<PlayerGoldAccount> {

	@Override
	protected String getKey(PlayerGoldAccount account) {
		return account.getPlayerId();
	}

}
