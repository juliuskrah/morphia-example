package com.juliuskrah.morphia.repository;

import java.io.Serializable;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.WriteResult;

public interface CrudRepository<T, ID extends Serializable> {
	Key<T> create(T entity);

	T read(ID id);

	UpdateResults update(T entity, UpdateOperations<T> operations);

	UpdateResults update(Query<T> query, UpdateOperations<T> operations);

	WriteResult delete(T entity);

	UpdateOperations<T> createOperations();

	Query<T> createQuery();
}
