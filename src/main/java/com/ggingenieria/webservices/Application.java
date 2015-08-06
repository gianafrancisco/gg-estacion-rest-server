package com.ggingenieria.webservices;


import com.ggingenieria.estacion.port.LectorLlavero;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ggingenieria")
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        LectorLlavero ll = (LectorLlavero) ctx.getBean("lectorLlavero");
        Thread tr = new Thread(ll);
        tr.start();

    }
}