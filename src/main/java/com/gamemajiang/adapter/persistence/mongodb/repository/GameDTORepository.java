package com.gamemajiang.adapter.persistence.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gamemajiang.adapter.persistence.mongodb.dto.GameDTO;

public interface GameDTORepository extends MongoRepository<GameDTO, String> {

}
