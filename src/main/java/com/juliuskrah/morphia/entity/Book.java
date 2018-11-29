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
package com.juliuskrah.morphia.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import xyz.morphia.annotations.Entity;
import xyz.morphia.annotations.Id;
import xyz.morphia.annotations.Property;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(value = "books", noClassnameStored = true)
@NoArgsConstructor
public class Book {
	@Id
	private ObjectId id;
	private String title;
	@Property("published")
	private LocalDate publicationDate;

	public Book(String title, LocalDate publicationDate) {
		this.title = title;
		this.publicationDate = publicationDate;
	}
}
