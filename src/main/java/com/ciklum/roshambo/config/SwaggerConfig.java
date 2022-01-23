package com.ciklum.roshambo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Bean used by Swagger.
   */
  @Bean
  public Docket api(@Value("${api.swagger.title}") String title,
                    @Value("${api.swagger.description}") String description,
                    @Value("${api.swagger.version}") String version,
                    @Value("${api.swagger.author}") String author,
                    @Value("${api.swagger.url}") String url,
                    @Value("${api.swagger.email}") String email) {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ciklum.roshambo.controller"))
        .paths(PathSelectors.any())
        .build().apiInfo(getApiInfo(title, description, version, author, url, email));
  }

  private ApiInfo getApiInfo(String title, String description, String version, String author, String url, String email) {
    return new ApiInfoBuilder().title(title).description(description).version(version).
        contact(new Contact(author, url, email)).build();
  }
}
