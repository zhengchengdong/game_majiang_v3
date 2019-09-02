package com.gamemajiang.adapter.persistence.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gamemajiang.adapter.persistence.mongodb.dto.PlayerGoldAccountDTO;

public interface PlayerGoldAccountDTORepository extends MongoRepository<PlayerGoldAccountDTO, String> {

	PlayerGoldAccountDTO findOneByPlayerId(String playerId);

}
