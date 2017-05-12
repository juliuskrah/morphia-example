package com.juliuskrah.morphia.repository.impl;

import java.io.Serializable;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.morphia.repository.CrudRepository;

public class BaseRepository<T, ID extends Serializable> implements CrudRepository<T, ID> {
	@Autowired
	private Datastore datastore;
	private Class<T> t;

	public BaseRepository(Class<T> t) {
		this.t = t;
	}

	@Override
	public Key<T> create(T entity) {
		return datastore.save(entity);
	}

	@Override
	public T read(ID id) {
		return datastore.get(t, id);
	}

	@Override
	public Key<T> update(T author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T author) {
		// TODO Auto-generated method stub

	}

}
