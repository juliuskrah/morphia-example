package com.jipasoft.morphia.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jipasoft.morphia.entity.Entities;
import com.mongodb.MongoClient;

@Configuration
public class DataSourceConfig {
	@Autowired
	private MongoProperties mongoProperties;

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(Entities.class);

		return morphia;
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
		datastore.ensureIndexes();

		return datastore;
	}
}
