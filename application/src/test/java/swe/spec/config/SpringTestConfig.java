package swe.spec.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"swe"})
@EnableJpaRepositories(basePackages = {"swe"})
@EntityScan("swe")
@PropertySources({
        @PropertySource("classpath:application-test.properties"),
})
public class SpringTestConfig {}
