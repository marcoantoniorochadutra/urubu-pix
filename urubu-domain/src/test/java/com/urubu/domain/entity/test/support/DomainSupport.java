package com.urubu.domain.entity.test.support;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@EntityScan("com.urubu.domain.entity")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DomainSupport.class)
@EnableAutoConfiguration(exclude = { QuartzAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class DomainSupport extends AbstractTestDbSetup {
	


}
