package com.lzaprojects.hypertrophy_backend.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration 
{
	@Bean
	public OpenAPI openApi() 
	{
		Info info = new Info();
		info.title("Hypetrophy App API");
		info.description("Provides APIs that faciliates HypertrophyApp.");
		
		return new OpenAPI().info(info);
	}
}
