package com.jipasoft.morphia.entity;

import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity("author")
@NoArgsConstructor
public class Author {
	@Id
	private ObjectId id;
	private String name;
	@Reference
	private Set<Book> books;

	public Author(String name) {
		this.name = name;
	}
}
