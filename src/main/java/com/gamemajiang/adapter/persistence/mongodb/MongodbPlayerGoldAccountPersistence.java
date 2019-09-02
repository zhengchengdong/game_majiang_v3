package com.gamemajiang.adapter.persistence.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.gamemajiang.adapter.persistence.PlayerGoldAccountPersistence;
import com.gamemajiang.adapter.persistence.mongodb.dto.PlayerGoldAccountDTO;
import com.gamemajiang.adapter.persistence.mongodb.repository.PlayerGoldAccountDTORepository;
import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;

@Component
public class MongodbPlayerGoldAccountPersistence implements PlayerGoldAccountPersistence {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PlayerGoldAccountDTORepository repository;

	@Override
	public void createGame(CreateGameResult createGameResult) {
		if (createGameResult.isSuccess()) {
			Query query = new Query(Criteria.where("playerId").is(createGameResult.getFirstPlayerId()));
			Update update = new Update().set("balance", createGameResult.getPlayerGoldBalance());
			mongoTemplate.updateFirst(query, update, PlayerGoldAccountDTO.class);
		}
	}

	@Override
	public void createPlayerGoldAccount(CreatePlayerGoldAccountResult createPlayerGoldAccountResult) {
		if (createPlayerGoldAccountResult.isSuccess()) {
			PlayerGoldAccountDTO account = new PlayerGoldAccountDTO();
			account.setPlayerId(createPlayerGoldAccountResult.getPlayerId());
			repository.save(account);
		}
	}

	@Override
	public void deposit(DepositResult depositResult) {
		if (depositResult.isSuccess()) {
			Query query = new Query(Criteria.where("playerId").is(depositResult.getPlayerId()));
			Update update = new Update().set("balance", depositResult.getBalanceAfter());
			mongoTemplate.updateFirst(query, update, PlayerGoldAccountDTO.class);
		}
	}

	@Override
	public PlayerGoldAccount load(String playerId) {
		PlayerGoldAccountDTO dto = repository.findOneByPlayerId(playerId);
		return dto.toEntity();
	}

}
