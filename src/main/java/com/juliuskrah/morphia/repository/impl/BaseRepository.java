package com.juliuskrah.morphia.repository.impl;

import java.io.Serializable;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.morphia.repository.CrudRepository;
import com.mongodb.WriteResult;

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
	public UpdateResults update(T entity, UpdateOperations<T> operations) {
		return datastore.update(entity, operations);
	}

	@Override
	public UpdateResults update(Query<T> query, UpdateOperations<T> operations) {
		return datastore.update(query, operations);
	}

	@Override
	public WriteResult delete(T entity) {
		return datastore.delete(entity);
	}

	@Override
	public UpdateOperations<T> createOperations() {
		return datastore.createUpdateOperations(t);
	}

	@Override
	public Query<T> createQuery() {
		return datastore.createQuery(t);
	}

}
