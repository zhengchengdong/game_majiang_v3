package com.gamemajiang.repository;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.framework.AggregateRepository;

public class GameAggregateRepository extends AggregateRepository<Game> {

	@Override
	protected String getKey(Game game) {
		return game.getId();
	}

}
