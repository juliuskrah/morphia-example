package com.juliuskrah.morphia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;
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
		bookRepository.save(ci);

		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.save(julius);

		assertThat(julius).isNotNull();
		assertThat(julius.getId()).isNotNull();
	}

	@Test
	public void testRead() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);

		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.save(julius);
		
		assertThat(julius).isNotNull();

		Optional<Author> read = authorRepository.findOne(julius.getId());

		assertThat(read).isNotNull();
		assertThat(read.isPresent()).isTrue();
		read.ifPresent(r -> {
			assertThat(r.getBooks()).contains(ci);
		});

	}

	@Test
	public void testUpdate() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);
		
		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.save(julius);
		
		assertThat(julius).isNotNull();

		julius.setName("Deborah");
		Optional<Author> results = authorRepository.save(julius);
		
		assertThat(results.isPresent()).isTrue();
		assertThat(results.get().getName()).isEqualTo("Deborah");
	}

	@Test
	public void testDelete() {
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepository.save(ci);
		
		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepository.save(julius);
		
		assertThat(julius).isNotNull();

		authorRepository.delete(julius);
        Optional<Author> read = authorRepository.findOne(julius.getId());

		assertThat(read.isPresent()).isFalse();
	}

}
