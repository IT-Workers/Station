package com.unitmb.station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class App {

    public static void main( String[] args ) throws InterruptedException {

        ConfigurableApplicationContext context =  SpringApplication.run(App.class, args);

       // Thread.sleep(10000);

       // SpringApplication.exit(context);
    }

}
