package com.jipasoft.morphia.repository;

import org.mongodb.morphia.Key;

import com.jipasoft.morphia.entity.Book;

public interface BookRepository {
	public Iterable<Key<Book>> save(Book... books);

	public Key<Book> save(Book book);
}
