package com.gamemajiang.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamemajiang.domain.process.result.CreatePlayerGoldAccountResult;
import com.gamemajiang.domain.process.result.DepositResult;
import com.gamemajiang.domain.process.service.GoldService;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

	@Autowired
	private GoldService goldService;

	@RequestMapping("/createaccount")
	@ResponseBody
	public CreatePlayerGoldAccountResult create(String playerId) {
		return goldService.createPlayerGoldAccount(playerId);
	}

	@RequestMapping("/deposit")
	@ResponseBody
	public DepositResult deposit(String playerId, int gold) {
		return goldService.deposit(playerId, gold);
	}

}
