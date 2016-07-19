package com.jipasoft.morphia;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.jipasoft.morphia.entity.Author;
import com.jipasoft.morphia.entity.Book;
import com.jipasoft.morphia.repository.AuthorRepository;
import com.jipasoft.morphia.repository.BookRepository;

@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner init(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			Book ci = new Book("Continous Integration", new Date());
			bookRepository.save(ci);

			Author julius = new Author("Julius");
			julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
			authorRepository.save(julius);
		};
	}
}
