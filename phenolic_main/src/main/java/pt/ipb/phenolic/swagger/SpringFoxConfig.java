import pt.ipb.phenolic.swagger.*;

@Configuration
@EnableSwagger2

public class SpringFoxConfig {
	
	@Value("${swagger.host.url}")
	private String hostUrl;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.host(hostUrl)
			.groupName("Spring Actuator")
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}