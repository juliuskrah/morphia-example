package com.jipasoft.test.morphia.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import com.jipasoft.morphia.entity.Author;
import com.jipasoft.morphia.entity.Book;
import com.jipasoft.morphia.repository.AuthorRepository;
import com.jipasoft.morphia.repository.BookRepository;
import com.jipasoft.test.morphia.ApplicationTests;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorRepositoryTest extends ApplicationTests {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testSave() {
		Book ci = new Book("Continous Integration", new Date());
		bookRepository.save(ci);

		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		Key<Author> author = authorRepository.save(julius);
		assertNotNull(author);
		log.info("Id created is {}", author.getId());
	}

}
