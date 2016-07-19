package com.jipasoft.test.morphia.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.junit.AfterClass;
import org.junit.Test;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import com.jipasoft.morphia.entity.Author;
import com.jipasoft.morphia.entity.Book;
import com.jipasoft.morphia.repository.AuthorRepository;
import com.jipasoft.morphia.repository.BookRepository;
import com.jipasoft.test.morphia.ApplicationTests;

import de.flapdoodle.embed.mongo.MongodExecutable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorRepositoryTest extends ApplicationTests {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MongodExecutable mongodExecutable;
	private static MongodExecutable staticMongo;

	@PostConstruct
	public void init() {
		this.staticMongo = mongodExecutable;
	}

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

	@AfterClass
	public static void tearDown() {
		staticMongo.stop();
	}
}
