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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.juliuskrah.morphia.entity.Author;
import com.juliuskrah.morphia.entity.Book;
import com.juliuskrah.morphia.repository.AuthorRepository;
import com.juliuskrah.morphia.repository.BookRepository;

@Slf4j
@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		BookRepository bookRepo = ctx.getBean(BookRepository.class);
		AuthorRepository authorRepo = ctx.getBean(AuthorRepository.class);
		
		Book ci = new Book("Continous Integration", LocalDate.now());
		bookRepo.save(ci);
		
		Author julius = new Author("Julius");
		julius.setBooks(Stream.of(ci).collect(Collectors.toSet()));
		authorRepo.save(julius);

		log.info("Book: {}", ci);

		log.info("Author: {}", julius);
		
		Optional<Author> read = authorRepo.findOne(julius.getId());
		
		read.get().setName("Abeiku");
		authorRepo.save(read.get());
		
		authorRepo.delete(julius);
	}

}
