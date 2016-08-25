package net.smartcosmos.edge.things.config;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.classmate.TypeResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.security.user.SmartCosmosUser;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Configuration for Swagger generation using Springfox.
 */
@Configuration
@EnableSwagger2
@Profile("docs")
public class SpringfoxDocumentationConfig {
    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket objectsExtenstionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            // @formatter:off
            .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(SmartCosmosRdao.class))
                .build()
            .apiInfo(apiInfo())
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(SmartCosmosUser.class)
            .alternateTypeRules(
                newRule(typeResolver.resolve(DeferredResult.class,
                                             typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .host("https://objects.smartcosmos.net")
            .pathMapping("/rest/things")
            .securitySchemes(securitySchemes())
            .forCodeGeneration(true)
            .enableUrlTemplating(true)
            .alternateTypeRules(
                newRule(typeResolver.resolve(DeferredResult.class,
                                             typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)));
            // @formatter:on
    }

    private ApiKey apiKey() {
        return new ApiKey("basic_auth", "api_key", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("/v2/.*"))
            .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("basic_auth", authorizationScopes));
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfiguration.DEFAULT;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Objects Server Metadata Endpoints")
            .description("The endpoints that support the operations for working with Metadata.")
            .version("3.0")
            .contact(new Contact("Smartrac Technologies", "https://api.smartcosmos.net/", "api@smartrac-group.com"))
            .build();
    }

    private List<SecurityScheme> securitySchemes() {
        SecurityScheme[] schemes = { new BasicAuth("basic") };
        return Arrays.asList(schemes);
    }
}
