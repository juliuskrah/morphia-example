package com.juliuskrah.morphia.repository;

import java.io.Serializable;

import org.mongodb.morphia.Key;

public interface CrudRepository<T, ID extends Serializable> {
	public Key<T> create(T entity);

	public T read(ID id);

	public Key<T> update(T author);

	public void delete(T author);
}
