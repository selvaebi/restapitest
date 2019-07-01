package uk.ac.ebi.test.configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {

    private SecurityReference securityReference = SecurityReference.builder()
            .reference("Authorization").scopes(new AuthorizationScope[0]).build();

    private SecurityContext securityContext = SecurityContext.builder()
            .securityReferences(Arrays.asList(securityReference)).build();

    @Bean
    public Docket metadataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(getScanRestServicesPathPredicate())
                .build()
                .apiInfo(getApiInfo())
                .pathMapping("/")
                .globalResponseMessage(RequestMethod.POST, getResponseMessagesForPostAndPatch())
                .globalResponseMessage(RequestMethod.PATCH, getResponseMessagesForPostAndPatch())
                .genericModelSubstitutes(ResponseEntity.class)
                .securitySchemes(Arrays.asList(new ApiKey("Authorization", "Authorization", "header")))
                .securityContexts(Arrays.asList(securityContext));
    }

    private List<ResponseMessage> getResponseMessagesForPostAndPatch() {
        return Arrays.asList(new ResponseMessageBuilder()
                        .code(201)
                        .message("Created")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Validation error")
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized")
                        .build(),
                new ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden")
                        .build(),
                new ResponseMessageBuilder()
                        .code(404)
                        .message("Not Found")
                        .build());
    }

    private Predicate<String> getScanRestServicesPathPredicate() {
        return Predicates.and(
                Predicates.not(PathSelectors.regex("/actuator.*")), // Hide spring-actuator
                Predicates.not(PathSelectors.regex("/error.*")), // Hide spring-data error
                Predicates.not(PathSelectors.regex("/profile.*")), // Hide spring-data profile
                Predicates.not(PathSelectors.regex("/securityUser.*")) // Hide security-user profile
        );
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayRequestDuration(true)
                .filter(false)
                .validatorUrl("")
                .build();
    }

    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Rest API Test")
                .description("CRUD operations through Rest API")
                .version("1")
                .build();
    }
}
