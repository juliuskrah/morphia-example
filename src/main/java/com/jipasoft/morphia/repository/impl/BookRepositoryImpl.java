package com.jipasoft.morphia.repository.impl;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jipasoft.morphia.entity.Book;
import com.jipasoft.morphia.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
	@Autowired
	private Datastore datastore;

	@Override
	public Iterable<Key<Book>> save(Book... books) {
		return datastore.save(books);
	}

	@Override
	public Key<Book> save(Book book) {
		return datastore.save(book);
	}

}
