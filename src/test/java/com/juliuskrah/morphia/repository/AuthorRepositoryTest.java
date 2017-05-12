package com.juliuskrah.morphia.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.juliuskrah.morphia.ApplicationTests;
import com.juliuskrah.morphia.entity.Author;
import com.juliuskrah.morphia.entity.Book;

public class AuthorRepositoryTest extends ApplicationTests {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testCreate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);

		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.create(julius);

		assertThat(julius).isNotNull();
		assertThat(julius.getId()).isNotNull();
	}

	@Test
	public void testRead() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.create(ci);

		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.create(julius);
		
		assertThat(julius).isNotNull();

		Author read = authorRepository.read(julius.getId());

		assertThat(read).isNotNull();
		assertThat(read.getId()).isEqualTo(julius.getId());
		assertThat(read.getBooks()).contains(ci);
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
