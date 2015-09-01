package com.ggingenieria.webservices;


import com.ggingenieria.estacion.port.LectorLlavero;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ggingenieria")
public class Application {

    public static void main(String[] args) {
        //ApplicationContext ctx = SpringApplication.run(Application.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        ConfigurableApplicationContext ctx = builder.run(args);
        Systray st = new Systray();
        LectorLlavero ll = (LectorLlavero) ctx.getBean("lectorLlavero");
        Thread tr = new Thread(ll);
        tr.start();

    }
}