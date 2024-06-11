package br.com.asoft.nfereader.security

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private lateinit var jwkSetUri: String

    @Bean
    @Throws(Exception::class)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/actuator/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher("/openapi/**")).permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfig()) }
            .oauth2ResourceServer { oauthResourceServer ->
                oauthResourceServer.jwt(Customizer.withDefaults())
            }.build()
    }

    @Bean
    fun reactiveJwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build()
    }

    @Bean
    @Profile("!unittest")
    fun openIdConfiguration(
        @Value("\${spring.security.oauth2.openid-configuration}") openIdConfiguration: String?
    ): OAuthOpenIDConfig? {
        val restTemplate = RestTemplate()
        val openIDConfig = restTemplate.getForObject(openIdConfiguration!!, OAuthOpenIDConfig::class.java)
        return openIDConfig
    }

    private fun corsConfig(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration(
                "/**",
                CorsConfiguration().apply {
                    allowedOriginPatterns = listOf("*")
                    allowedMethods = listOf("*")
                    allowedHeaders = listOf("*")
                    allowCredentials = true
                },
            )
        }
    }

    class OAuthOpenIDConfig(
        @JsonProperty("token_endpoint") val tokenEndpoint: String
    )
}