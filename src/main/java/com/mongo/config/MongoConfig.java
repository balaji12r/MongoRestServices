package com.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * Created by a526903 on 3/21/16.
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableMongoRepositories(basePackages = "com.mongo.repository")
  public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    String databaseName;

    @Value("${spring.data.mongodb.uri}")
    String url;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Bean
    public MongoClient mongo() throws UnknownHostException {
        System.out.println("url "+url);
        String mongoURL = "mongodb://"+ url;
        MongoClientURI uri = new MongoClientURI(mongoURL);
        MongoClient client = new MongoClient(uri);
        client.setWriteConcern(WriteConcern.JOURNAL_SAFE);

        return client;

    }

    @Override
    protected String getMappingBasePackage() {
        return "com.mongo.repository";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
