package test.gamemajiang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gamemajiang.domain.bean.GameState;
import com.gamemajiang.domain.process.result.CreateGameResult;
import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;
import com.gamemajiang.domain.process.result.FinishGameResult;
import com.gamemajiang.domain.process.result.JoinGameResult;
import com.gamemajiang.domain.process.result.ReadyForGameResult;
import com.gamemajiang.domain.process.service.GameService;
import com.gamemajiang.domain.process.service.GoldService;
import com.gamemajiang.domain.process.service.impl.GameServiceImpl;
import com.gamemajiang.domain.process.service.impl.GoldServiceImpl;
import com.gamemajiang.framework.RootAggregateRepository;
import com.gamemajiang.repository.GameAggregateRepository;
import com.gamemajiang.repository.PlayerGoldAccountAggregateRepository;

public class GameTest {

	@Test
	public void test() {

		RootAggregateRepository rootAggregateRepository = createRootAggregateRepository();
		GoldService goldService = createGoldService(rootAggregateRepository);
		GameService gameService = createGameService(rootAggregateRepository);

		// 给开房玩家创建金币账户并充1000个金币
		CreatePlayerGoldAccountResult createPlayerGoldAccountResult = goldService.createPlayerGoldAccount("p1");
		assertTrue(createPlayerGoldAccountResult.isSuccess());
		assertEquals("p1", createPlayerGoldAccountResult.getPlayerId());
		DepositResult depositResult = goldService.deposit("p1", 1000);
		assertTrue(depositResult.isSuccess());
		assertEquals("p1", depositResult.getPlayerId());
		assertEquals(1000, depositResult.getBalanceAfter());

		// 使用100个金币创建一个麻将游戏
		CreateGameResult createGameResult = gameService.createGame("p1", "g1");
		assertTrue(createGameResult.isSuccess());
		assertEquals("g1", createGameResult.getNewGameId());
		assertEquals("p1", createGameResult.getFirstPlayerId());
		assertEquals(900, createGameResult.getPlayerGoldBalance());

		// 加入1个玩家
		JoinGameResult joinGameResult1 = gameService.joinGame("p2", "g1");
		assertTrue(joinGameResult1.isSuccess());
		assertEquals("p2", joinGameResult1.getJoinedPlayerId());

		// 上一个玩家试图重复加入
		JoinGameResult joinGameResult2 = gameService.joinGame("p2", "g1");
		assertFalse(joinGameResult2.isSuccess());

		// 再加入两个玩家，凑齐一桌麻将
		gameService.joinGame("p3", "g1");
		JoinGameResult joinGameResult3 = gameService.joinGame("p4", "g1");
		assertTrue(joinGameResult3.isSuccess());
		assertEquals(4, joinGameResult3.getPlayersCount());

		// 第五个人想来加入麻将
		JoinGameResult joinGameResult4 = gameService.joinGame("p5", "g1");
		assertFalse(joinGameResult4.isSuccess());
		assertEquals(4, joinGameResult4.getPlayersCount());

		// 第一个人点准备
		ReadyForGameResult readyForGameResult1 = gameService.readyForGame("p1", "g1");
		assertTrue(readyForGameResult1.isSuccess());
		assertTrue(readyForGameResult1.isReady());

		// 第一个人试图再次点准备
		ReadyForGameResult readyForGameResult2 = gameService.readyForGame("p1", "g1");
		assertFalse(readyForGameResult2.isSuccess());
		assertTrue(readyForGameResult2.isReady());

		// 又有两个人点了准备
		gameService.readyForGame("p2", "g1");
		gameService.readyForGame("p3", "g1");

		// 最后一个人点了准备，游戏开始
		ReadyForGameResult readyForGameResult3 = gameService.readyForGame("p4", "g1");
		assertTrue(readyForGameResult3.isSuccess());
		assertEquals(GameState.ongoing, readyForGameResult3.getGameState());

		// 游戏都已经开始了，还有人试图加入
		JoinGameResult joinGameResult5 = gameService.joinGame("p5", "g1");
		assertFalse(joinGameResult5.isSuccess());

		// 游戏结束
		FinishGameResult finishGameResult = gameService.finishGame("g1");
		assertTrue(finishGameResult.isSuccess());

	}

	private GoldService createGoldService(RootAggregateRepository rootAggregateRepository) {
		GoldServiceImpl goldServiceImpl = new GoldServiceImpl(rootAggregateRepository);
		return goldServiceImpl;
	}

	private GameService createGameService(RootAggregateRepository rootAggregateRepository) {
		GameServiceImpl gameServiceImpl = new GameServiceImpl(rootAggregateRepository);
		return gameServiceImpl;
	}

	private RootAggregateRepository createRootAggregateRepository() {
		RootAggregateRepository rootAggregateRepository = new RootAggregateRepository();
		rootAggregateRepository.put(GameAggregateRepository.class.getName(), new GameAggregateRepository());
		rootAggregateRepository.put(PlayerGoldAccountAggregateRepository.class.getName(),
				new PlayerGoldAccountAggregateRepository());
		return rootAggregateRepository;
	}

}
