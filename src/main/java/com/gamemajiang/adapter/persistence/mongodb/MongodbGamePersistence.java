package com.gamemajiang.adapter.persistence.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.gamemajiang.adapter.persistence.GamePersistence;
import com.gamemajiang.adapter.persistence.mongodb.dto.GameDTO;
import com.gamemajiang.adapter.persistence.mongodb.dto.PlayerDTO;
import com.gamemajiang.adapter.persistence.mongodb.repository.GameDTORepository;
import com.gamemajiang.domain.bean.Game;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;

@Component
public class MongodbGamePersistence implements GamePersistence {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GameDTORepository repository;

	@Override
	public void createGame(CreateGameResult createGameResult) {
		if (createGameResult.isSuccess()) {
			GameDTO game = new GameDTO();
			game.setId(createGameResult.getNewGameId());
			game.setState(createGameResult.getGameState());
			PlayerDTO player = new PlayerDTO();
			player.setId(createGameResult.getFirstPlayerId());
			game.getPlayers().put(player.getId(), player);
			repository.save(game);
		}
	}

	@Override
	public void joinGame(JoinGameResult joinGameResult) {
		if (joinGameResult.isSuccess()) {
			PlayerDTO player = new PlayerDTO();
			player.setId(joinGameResult.getJoinedPlayerId());
			Query query = new Query(Criteria.where("id").is(joinGameResult.getGameId()));
			Update update = new Update().set("players." + player.getId(), player);
			mongoTemplate.updateFirst(query, update, GameDTO.class);
		}
	}

	@Override
	public void readyForGame(ReadyForGameResult readyForGameResult) {
		if (readyForGameResult.isSuccess()) {
			Query query = new Query(Criteria.where("id").is(readyForGameResult.getGameId()));
			Update update = new Update().set("players." + readyForGameResult.getPlayerId() + ".ready",
					readyForGameResult.isReady());
			mongoTemplate.updateFirst(query, update, GameDTO.class);
			update = new Update().set("state", readyForGameResult.getGameState());
			mongoTemplate.updateFirst(query, update, GameDTO.class);
		}
	}

	@Override
	public void finishGame(FinishGameResult finishGameResult) {
		if (finishGameResult.isSuccess()) {
			Query query = new Query(Criteria.where("id").is(finishGameResult.getFinishedGameId()));
			Update update = new Update().set("state", finishGameResult.getGameState());
			mongoTemplate.updateFirst(query, update, GameDTO.class);
		}
	}

	@Override
	public Game load(String gameId) {
		GameDTO dto = repository.findById(gameId).get();
		return dto.toEntity();
	}

}
