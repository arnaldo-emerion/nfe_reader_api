package br.com.asoft.nfereader.configs

import br.com.asoft.nfereader.security.SecurityConfig
import com.google.common.io.Resources
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Component

@Component
class SwaggerApiGroupBuilder(
    val oauthOAuthOpenIDConfig: SecurityConfig.OAuthOpenIDConfig?
) {
    companion object {
        private const val CLIENT_CREDENTIALS_SCHEME_NAME = "clientCredentials"
    }

    @Autowired
    private lateinit var buildProperties: BuildProperties

    fun build(groupName: String, specFileLocation: String): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group(groupName)
            .addOpenApiCustomizer {
                applyAPISpecToGroupOpenAPI(specFileLocation, it)
            }
            .pathsToMatch("/**")
            .build()
    }

    private fun applyAPISpecToGroupOpenAPI(specFileLocation: String, groupOpenAPI: OpenAPI) {
        val openapiYaml = Resources.getResource(specFileLocation).readText()
        val openAPI = OpenAPIV3Parser().readContents(openapiYaml).openAPI
        injectClientCredentialsTokenUrl(openAPI, oauthOAuthOpenIDConfig)
        openAPI.info.version = buildProperties.version
        copyOpenAPITo(newOpenAPIInfo = openAPI, toOpenApi = groupOpenAPI)
    }

    private fun injectClientCredentialsTokenUrl(
        openAPI: OpenAPI,
        oauthOAuthOpenIDConfig: SecurityConfig.OAuthOpenIDConfig?
    ) {
        oauthOAuthOpenIDConfig?.let {
            val clientCredentialsScheme = openAPI.components.securitySchemes[CLIENT_CREDENTIALS_SCHEME_NAME]
            clientCredentialsScheme?.flows?.clientCredentials?.tokenUrl = it.tokenEndpoint
        }
    }

    private fun copyOpenAPITo(newOpenAPIInfo: OpenAPI, toOpenApi: OpenAPI): OpenAPI {
        return toOpenApi
            .info(newOpenAPIInfo.info)
            .components(newOpenAPIInfo.components)
            .servers(newOpenAPIInfo.servers)
            .paths(newOpenAPIInfo.paths)
            .security(newOpenAPIInfo.security)
            .tags(newOpenAPIInfo.tags)
    }
}
