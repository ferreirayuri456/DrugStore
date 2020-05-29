package br.com.example.farmacia.swagger;



import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfiguration {



	@Bean
	public Docket detailsApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("br.com.example.farmacia")).paths(PathSelectors.any())
				.build().apiInfo(this.informationsApi().build());
		
		return docket;
	}

	private ApiInfoBuilder informationsApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		
		apiInfoBuilder.title("API Drugstore");
		apiInfoBuilder.description("API for conducting a CRUD of products and manufacturers ");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Terms of Service; Use for studies");
		apiInfoBuilder.license("License - Open Source");
		apiInfoBuilder.contact(this.contact());
		
		return apiInfoBuilder;
	}
	
	private Contact contact() {
		return new Contact("Yuri Ferreira da Silva", "yuri.silva@compasso.com.br", null);
	}
}
