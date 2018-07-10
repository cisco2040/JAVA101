package com.softtek.javaweb.repository;

import java.util.List;

public interface MyRepository<T, K> {
	T getOne(final K id);
	List<T> list();
	int add(final T entity);
	int update(final T entity);
	int delete(final K id);
}
