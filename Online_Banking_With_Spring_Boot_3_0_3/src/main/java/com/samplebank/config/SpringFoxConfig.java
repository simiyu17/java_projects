package com.samplebank.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

/*
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Online Banking App APIs",
                "APIs for Online Banking App.",
                "1.0",
                "Terms of service",
                new Contact("API", "www.org.com", "api@banking.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }*/
}
