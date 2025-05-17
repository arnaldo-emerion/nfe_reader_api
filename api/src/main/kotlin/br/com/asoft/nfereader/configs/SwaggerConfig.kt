package br.com.asoft.nfereader.configs

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig(val swaggerApiGroupBuilder: SwaggerApiGroupBuilder) {
    @Bean
    fun apiVersion1(): GroupedOpenApi {
        return swaggerApiGroupBuilder.build(
            "NFe Reader API V1",
            "static/openapi/openapi.yaml"
        )
    }
}