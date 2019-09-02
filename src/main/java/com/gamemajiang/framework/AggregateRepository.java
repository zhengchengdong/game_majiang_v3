package com.gamemajiang.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AggregateRepository<T> {

	private Map<String, T> keyAggregateMap = new ConcurrentHashMap<>();

	public T findByKey(String key) {
		return keyAggregateMap.get(key);
	}

	public void put(T aggregate) {
		keyAggregateMap.put(getKey(aggregate), aggregate);
	}

	public void remove(T aggregate) {
		keyAggregateMap.remove(getKey(aggregate));
	}

	protected abstract String getKey(T aggregate);

}
