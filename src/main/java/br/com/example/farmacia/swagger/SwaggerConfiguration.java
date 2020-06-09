package br.com.example.farmacia.swagger;

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
public class SwaggerConfiguration {

	public Docket compassoApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compasso.PMA.Parameters"))
				.paths(PathSelectors.ant("/**")).build();
	}

	private ApiInfo informationsApi() {
		return new ApiInfoBuilder().title("API Drugstore")
				.description("API for conducting a CRUD of products and manufacturers ").version("1.0")
				.termsOfServiceUrl("Terms of Service; Use for studies").license("License - Open Source")
				.contact(this.contact()).build();

	}

	private Contact contact() {
		return new Contact("Yuri Ferreira da Silva", "yuri.silva@compasso.com.br", null);
	}
}
