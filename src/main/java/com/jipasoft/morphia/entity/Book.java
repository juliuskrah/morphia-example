package com.jipasoft.morphia.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity("book")
@NoArgsConstructor
public class Book {
	@Id
	private ObjectId id;
	private String title;
	private Date publicationDate;

	public Book(String title, Date publicationDate) {
		this.title = title;
		this.publicationDate = publicationDate;
	}
}
