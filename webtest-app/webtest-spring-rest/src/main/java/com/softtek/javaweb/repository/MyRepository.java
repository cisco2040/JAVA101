package com.softtek.javaweb.repository;

import java.util.List;

public interface MyRepository<T, K> {
	T getOne(final K id);
	List<T> list();
	T add(final T entity);
	T update(final T entity);
	int delete(final K id);
}
