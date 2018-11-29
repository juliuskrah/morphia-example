/*
* Copyright 2016, Julius Krah
* by the @authors tag. See the LICENCE in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.juliuskrah.morphia;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import xyz.morphia.query.Query;
import xyz.morphia.query.UpdateOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.juliuskrah.morphia.entity.Author;
import com.juliuskrah.morphia.entity.Book;
import com.juliuskrah.morphia.repository.AuthorRepository;
import com.juliuskrah.morphia.repository.BookRepository;

@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		final ApplicationContext ctx = SpringApplication.run(Application.class, args);
		final BookRepository bookRepo = ctx.getBean(BookRepository.class);
		final AuthorRepository authorRepo = ctx.getBean(AuthorRepository.class);

		final Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepo.create(ci);

		final Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepo.create(julius);

		final Author read = authorRepo.read(julius.getId());

		final Query<Author> updateQuery = authorRepo.createQuery().filter("id =", julius.getId());

		final UpdateOperations<Author> ops = authorRepo.createOperations().set("name", "Portia");
		// authorRepo.update(read, ops);
		authorRepo.update(updateQuery, ops);

		read.setName("Abeiku");
		authorRepo.create(read);

		authorRepo.delete(julius);
	}

}
