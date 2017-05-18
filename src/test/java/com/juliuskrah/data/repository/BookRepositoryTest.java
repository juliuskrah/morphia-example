package com.juliuskrah.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.data.ApplicationTests;
import com.juliuskrah.data.entity.Book;
import com.juliuskrah.data.repository.BookRepository;
import com.mongodb.WriteResult;

public class BookRepositoryTest extends ApplicationTests {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testCreate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);

		assertThat(ci).isNotNull();
		assertThat(ci.getId()).isNotNull();
	}

	@Test
	public void testRead() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);

		assertThat(ci).isNotNull();

		Optional<Book> read = bookRepository.findOne(ci.getId());

		assertThat(read).isNotNull();
		assertThat(read.isPresent()).isTrue();
		assertThat(read.get().getId()).isEqualTo(ci.getId());
	}

	@Test
	public void testUpdate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);
		
		assertThat(ci).isNotNull();
        Optional<Book> read = bookRepository.findOne(ci.getId());

        assertThat(read.isPresent()).isTrue();
        read.get().setTitle("Server Fault");

		Optional<Book> results = bookRepository.save(read.get());
		
		assertThat(results.get().getTitle()).isEqualTo("Server Fault");
	}

	@Test
	public void testDelete() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);
		
		assertThat(ci).isNotNull();
		
		bookRepository.delete(ci);

        Optional<Book> read = bookRepository.findOne(ci.getId());
		
		assertThat(read.isPresent()).isFalse();
	}

}
