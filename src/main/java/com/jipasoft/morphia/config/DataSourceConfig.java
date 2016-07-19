package com.jipasoft.morphia.config;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jipasoft.morphia.entity.Entities;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DataSourceConfig {
	@Autowired
	private MongoProperties mongoProperties;

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(Entities.class);

		return morphia;
	}

	@Bean(destroyMethod = "close")
	public MongoClient mongoClient() throws UnknownHostException {
		log.debug("Starting MongoClient...");
		//@formatter:off
		MongoCredential credential = MongoCredential.createCredential(
				mongoProperties.getUsername(), 
				mongoProperties.getAuthenticationDatabase(), 
				mongoProperties.getPassword());
		//@formatter:on
		MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));

		log.debug("MongoClient configuration succeeded without exceptions");

		return mongoClient;
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
		datastore.ensureIndexes();

		return datastore;
	}
}
