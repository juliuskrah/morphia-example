package com.juliuskrah.morphia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import xyz.morphia.query.UpdateOperations;
import xyz.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.morphia.ApplicationTests;
import com.juliuskrah.morphia.entity.Book;
import com.mongodb.WriteResult;

public class BookRepositoryTest extends ApplicationTests {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testCreate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);

		assertThat(ci).isNotNull();
		assertThat(ci.getId()).isNotNull();
	}

	@Test
	public void testRead() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);

		assertThat(ci).isNotNull();

		Book read = bookRepository.read(ci.getId());

		assertThat(read).isNotNull();
		assertThat(read.getId()).isEqualTo(ci.getId());
	}

	@Test
	public void testUpdate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);
		
		assertThat(ci).isNotNull();
		
		UpdateOperations<Book> ops = bookRepository.createOperations()
				.set("title", "Enterprise Integration")
				.set("publicationDate", LocalDate.now());
		UpdateResults results = bookRepository.update(ci, ops);
		
		assertThat(results.getUpdatedExisting()).isTrue();
	}

	@Test
	public void testDelete() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);
		
		assertThat(ci).isNotNull();
		
		WriteResult result = bookRepository.delete(ci);
		
		assertThat(result.wasAcknowledged()).isTrue();
	}

}
