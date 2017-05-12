package com.juliuskrah.morphia;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.juliuskrah.morphia.entity.Entities;
import com.juliuskrah.morphia.repository.AuthorRepository;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Profile("test")
@SpringBootApplication(scanBasePackageClasses = AuthorRepository.class)
public class JUnitConfig {
	@Autowired
	private MongoProperties mongoProperties;
	private int port;

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(Entities.class);

		return morphia;
	}

	private int getPort() throws IOException {
		if (mongoProperties.getPort() == 0)
			port = Network.getFreeServerPort();
		else
			port = mongoProperties.getPort();

		return port;
	}

	public static void main(String[] args) {
		SpringApplication.run(JUnitConfig.class, args);
	}

	@Bean
	public MongoClient mongoClient(MongodExecutable mongodExecutable) {
		return new MongoClient(new ServerAddress("localhost", port));
	}

	@Bean
	public IMongodConfig mongodConfig() throws IOException {
		//@formatter:off
		MongodConfigBuilder builder = new MongodConfigBuilder()
			.version(Version.Main.PRODUCTION)
			.withLaunchArgument("--storageEngine", "mmapv1")
			.net(new Net(getPort(), 
					Network.localhostIsIPv6()));
		//@formatter:on
		return builder.build();
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
		datastore.ensureIndexes();

		return datastore;
	}
}
