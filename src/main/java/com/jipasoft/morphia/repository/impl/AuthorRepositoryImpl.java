package com.jipasoft.morphia.repository.impl;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jipasoft.morphia.entity.Author;
import com.jipasoft.morphia.repository.AuthorRepository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

	@Autowired
	private Datastore datastore;

	@Override
	public Iterable<Key<Author>> save(Author... authors) {
		return datastore.save(authors);
	}

	@Override
	public Key<Author> save(Author author) {
		return datastore.save(author);
	}

}
