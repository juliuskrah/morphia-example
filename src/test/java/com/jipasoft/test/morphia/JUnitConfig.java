package com.jipasoft.test.morphia;

import java.io.IOException;
import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;

import com.jipasoft.morphia.entity.Entities;
import com.jipasoft.morphia.repository.AuthorRepository;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@SpringBootApplication(scanBasePackageClasses = AuthorRepository.class)
public class JUnitConfig {
	@Autowired
	private MongoProperties mongoProperties;

	int port = 0;
	private MongodStarter starter = MongodStarter.getDefaultInstance();

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackageFromClass(Entities.class);

		return morphia;
	}

	public int getPort() {
		return port = mongoProperties.getPort();
	}

	public static void main(String[] args) {
		SpringApplication.run(JUnitConfig.class, args);
	}

	@Bean
	public MongoClient mongoClient(MongodExecutable mongodExecutable) throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost", getPort());

		return mongoClient;
	}

	@Bean
	public MongodExecutable mongodExecutable() throws IOException {
		//@formatter:off
		IMongodConfig mongodConfig = new MongodConfigBuilder()
			.version(Version.Main.PRODUCTION).withLaunchArgument("--storageEngine", "mmapv1")
			.net(new Net(getPort(), Network.localhostIsIPv6()))
			.build();
		//@formatter:on
		MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();

		return mongodExecutable;
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
		datastore.ensureIndexes();

		return datastore;
	}
}
