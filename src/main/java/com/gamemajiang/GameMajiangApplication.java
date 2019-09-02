package com.gamemajiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.gamemajiang.domain.process.service.impl.GameServiceImpl;
import com.gamemajiang.domain.process.service.impl.GoldServiceImpl;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.GameAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

@SpringBootApplication
public class GameMajiangApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GameMajiangApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GameMajiangApplication.class);
	}

	@Bean
	public RootAggregateRepository rootAggregateRepository() {
		RootAggregateRepository rootAggregateRepository = new RootAggregateRepository();
		rootAggregateRepository.put(GameAggregateRepository.class.getName(), new GameAggregateRepository());
		rootAggregateRepository.put(PlayerGoldAccountAggregateRepository.class.getName(),
				new PlayerGoldAccountAggregateRepository());
		return rootAggregateRepository;
	}

	@Bean
	public GameServiceImpl gameServiceImpl(RootAggregateRepository rootAggregateRepository) {
		return new GameServiceImpl(rootAggregateRepository);
	}

	@Bean
	public GoldServiceImpl goldServiceImpl(RootAggregateRepository rootAggregateRepository) {
		return new GoldServiceImpl(rootAggregateRepository);
	}

}
