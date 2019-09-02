package com.gamemajiang.domain.process.service.impl;

import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.bean.PlayerGoldAccount;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;
import com.gamemajiang.domain.process.service.GameService;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.GameAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

public class GameServiceImpl implements GameService {

	private RootAggregateRepository rootAggregateRepository;

	public GameServiceImpl(RootAggregateRepository rootAggregateRepository) {
		this.rootAggregateRepository = rootAggregateRepository;
	}

	@Override
	public CreateGameResult createGame(String creatorPlayerId, String gameId) {
		PlayerGoldAccountAggregateRepository playerGoldAccountAggregateRepository = rootAggregateRepository
				.get(PlayerGoldAccountAggregateRepository.class.getName());

		PlayerGoldAccount account = playerGoldAccountAggregateRepository.findByKey(creatorPlayerId);
		if (account.getBalance() < 100) {// 没钱开个毛钱
			return CreateGameResult.unsuccess();
		}

		account.withdraw(100);

		GameAggregateRepository gameAggregateRepository = rootAggregateRepository
				.get(GameAggregateRepository.class.getName());

		Game newGame = new Game();
		newGame.setId(gameId);
		newGame.playerJoin(creatorPlayerId);

		gameAggregateRepository.put(newGame);
		return CreateGameResult.success(newGame, account);
	}

	@Override
	public JoinGameResult joinGame(String playerId, String gameId) {
		GameAggregateRepository gameAggregateRepository = rootAggregateRepository
				.get(GameAggregateRepository.class.getName());

		Game game = gameAggregateRepository.findByKey(gameId);
		if (game.playerJoin(playerId)) {
			return JoinGameResult.success(game, playerId);
		} else {
			return JoinGameResult.unsuccess(game);
		}

	}

	@Override
	public ReadyForGameResult readyForGame(String playerId, String gameId) {
		GameAggregateRepository gameAggregateRepository = rootAggregateRepository
				.get(GameAggregateRepository.class.getName());

		Game game = gameAggregateRepository.findByKey(gameId);
		if (game.playerReady(playerId)) {
			return ReadyForGameResult.success(game, playerId);
		} else {
			return ReadyForGameResult.unsuccess(game, playerId);
		}

	}

	@Override
	public FinishGameResult finishGame(String gameId) {
		GameAggregateRepository gameAggregateRepository = rootAggregateRepository
				.get(GameAggregateRepository.class.getName());
		Game game = gameAggregateRepository.findByKey(gameId);
		if (game.finish()) {
			gameAggregateRepository.remove(game);
			return FinishGameResult.success(game);
		} else {
			return FinishGameResult.unsuccess();
		}
	}

}
