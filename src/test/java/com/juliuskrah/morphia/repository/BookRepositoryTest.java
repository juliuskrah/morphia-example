package com.juliuskrah.morphia.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.morphia.ApplicationTests;
import com.juliuskrah.morphia.entity.Book;

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
		fail("Not implemented yet");
	}

	@Test
	public void testDelete() {
		fail("Not implemented yet");
	}

}
