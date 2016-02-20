package com.mediaocean.rest.services.test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:META-INF/spring/web-application-context.xml",
		"classpath:META-INF/spring/db-hsqldb-config.xml"
	})
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
public abstract class BaseTestClass {
	

}
