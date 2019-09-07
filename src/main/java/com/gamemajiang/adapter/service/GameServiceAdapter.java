package com.gamemajiang.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamemajiang.adapter.persistence.GamePersistence;
import com.gamemajiang.adapter.persistence.PlayerGoldAccountPersistence;
import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;
import com.gamemajiang.domain.process.service.GameService;
import com.gamemajiang.domain.process.service.impl.GameServiceImpl;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.GameAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

@Component("gameService")
public class GameServiceAdapter implements GameService {

	@Autowired
	private GameServiceImpl gameServiceImpl;

	@Autowired
	private RootAggregateRepository rootAggregateRepository;

	@Autowired
	private GamePersistence gamePersistence;

	@Autowired
	private PlayerGoldAccountPersistence playerGoldAccountPersistence;

	@Override
	public CreateGameResult createGame(String creatorPlayerId, String gameId) {
		checkAndLoadPlayerGoldAccountAggregate(creatorPlayerId);
		PlayerGoldAccountAggregateRepository playerGoldAccountAggregateRepository = rootAggregateRepository
				.get(PlayerGoldAccountAggregateRepository.class.getName());

		PlayerGoldAccount account = playerGoldAccountAggregateRepository.findByKey(creatorPlayerId);
		synchronized (account) {
			CreateGameResult createGameResult = gameServiceImpl.createGame(creatorPlayerId, gameId);
			persistAggregate(createGameResult);
			updateForQuery(createGameResult);
			return createGameResult;
		}

	}

	@Override
	public JoinGameResult joinGame(String playerId, String gameId) {
		checkAndLoadGameAggregate(gameId);
		JoinGameResult joinGameResult = gameServiceImpl.joinGame(playerId, gameId);
		persistAggregate(joinGameResult);
		updateForQuery(joinGameResult);
		return joinGameResult;
	}

	@Override
	public ReadyForGameResult readyForGame(String playerId, String gameId) {
		checkAndLoadGameAggregate(gameId);
		ReadyForGameResult readyForGameResult = gameServiceImpl.readyForGame(playerId, gameId);
		persistAggregate(readyForGameResult);
		updateForQuery(readyForGameResult);
		return readyForGameResult;
	}

	@Override
	public FinishGameResult finishGame(String gameId) {
		checkAndLoadGameAggregate(gameId);
		FinishGameResult finishGameResult = gameServiceImpl.finishGame(gameId);
		persistAggregate(finishGameResult);
		updateForQuery(finishGameResult);
		return finishGameResult;
	}

	private Game checkAndLoadGameAggregate(String gameId) {
		GameAggregateRepository gameAggregateRepository = rootAggregateRepository
				.get(GameAggregateRepository.class.getName());

		Game game = gameAggregateRepository.findByKey(gameId);
		if (game == null) {
			synchronized (gameAggregateRepository) {
				game = gameAggregateRepository.findByKey(gameId);
				if (game == null) {
					game = gamePersistence.load(gameId);
					gameAggregateRepository.put(game);
				}
			}
		}
		return game;
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

	private void persistAggregate(CreateGameResult createGameResult) {
		gamePersistence.createGame(createGameResult);
		playerGoldAccountPersistence.createGame(createGameResult);
	}

	private void persistAggregate(JoinGameResult joinGameResult) {
		gamePersistence.joinGame(joinGameResult);
	}

	private void persistAggregate(ReadyForGameResult readyForGameResult) {
		gamePersistence.readyForGame(readyForGameResult);
	}

	private void persistAggregate(FinishGameResult finishGameResult) {
		gamePersistence.finishGame(finishGameResult);
	}

	private void updateForQuery(CreateGameResult createGameResult) {
		// TODO Auto-generated method stub
	}

	private void updateForQuery(JoinGameResult joinGameResult) {
		// TODO Auto-generated method stub
	}

	private void updateForQuery(ReadyForGameResult readyForGameResult) {
		// TODO Auto-generated method stub
	}

	private void updateForQuery(FinishGameResult finishGameResult) {
		// TODO Auto-generated method stub
	}

}
