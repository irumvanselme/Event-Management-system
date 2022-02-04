package rw.ac.rca.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RCAEventMISApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RCAEventMISApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RCAEventMISApplication.class);
    }
}
