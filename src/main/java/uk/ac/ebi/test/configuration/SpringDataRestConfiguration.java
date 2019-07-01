package uk.ac.ebi.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import uk.ac.ebi.test.entities.Person;
import uk.ac.ebi.test.entities.SecurityUser;

@Configuration
public class SpringDataRestConfiguration {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.getCorsRegistry().addMapping("/**").allowedMethods("*").allowedOrigins("*");
                config.exposeIdsFor(
                        Person.class,
                        SecurityUser.class
                );
            }

        };
    }
}
