package com.gamemajiang.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RootAggregateRepository {

	private Map<String, Object> nameRootAggregateMap = new ConcurrentHashMap<>();

	public <T> T get(String name) {
		return (T) nameRootAggregateMap.get(name);
	}

	public void put(String name, Object rootAggregate) {
		nameRootAggregateMap.put(name, rootAggregate);
	}

}
