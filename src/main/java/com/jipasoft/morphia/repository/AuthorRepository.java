package com.jipasoft.morphia.repository;

import org.mongodb.morphia.Key;

import com.jipasoft.morphia.entity.Author;

public interface AuthorRepository {
	public Iterable<Key<Author>> save(Author... authors);

	public Key<Author> save(Author author);
}
