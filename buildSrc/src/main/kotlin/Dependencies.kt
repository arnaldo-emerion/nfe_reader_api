object KotlinDependencies {
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}"
    const val reactorExtension = "io.projectreactor.kotlin:reactor-kotlin-extensions:${Versions.kotlinReactorExtension}"
}

object JacksonDependencies {
    const val moduleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"
    const val datatypeJSR = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}"
    const val dataBindNullable = "org.openapitools:jackson-databind-nullable:${Versions.jacksonDataBind}"
}

object SpringBootDependencies {
    const val security = "org.springframework.boot:spring-boot-starter-security:${Versions.springFramework}"
    const val web = "org.springframework.boot:spring-boot-starter-web:${Versions.springFramework}"
    const val oauth2 = "org.springframework.boot:spring-boot-starter-oauth2-resource-server:${Versions.springFramework}"
    const val dataJpa = "org.springframework.boot:spring-boot-starter-data-jpa:${Versions.springFramework}"
    const val springDocOpenApi = "org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.springDocOpenApi}"

}

object SpringdocDependencies{
}

object TestDependencies {
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val jUnit = "org.junit.jupiter:junit-jupiter:${Versions.jUnit}"
    const val starterTest = "org.springframework.boot:spring-boot-starter-test:${Versions.springFramework}"
    const val testContainerCore = "org.testcontainers:testcontainers:${Versions.testContainer}"
    const val testContainerKafka = "org.testcontainers:kafka:${Versions.testContainer}"
    const val testContainerJupiter = "org.testcontainers:junit-jupiter:${Versions.testContainer}"
}

object WiremockDependencies {
    const val wiremock = "com.github.tomakehurst:wiremock-jre8-standalone:${Versions.wiremock}"
    const val jakartaServletApi = "jakarta.servlet:jakarta.servlet-api:${Versions.jakartaServletApi}"
}

object MonitoringDependencies {
    const val micrometerPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometerPrometheus}"
}

object OkHttpDependencies {
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
}

object JavaxDependencies {
    const val annotation = "javax.annotation:javax.annotation-api:${Versions.javaxAnnotation}"
    const val jaxbApi = "javax.xml.bind:jaxb-api:${Versions.jaxbApi}"
    const val JAVAX_SERVLET = "javax.servlet:servlet-api:${Versions.javaxServlet}"
    const val JAVAX_VALIDATION = "javax.validation:validation-api:${Versions.javaxValidation}"
}

object SwaggerDependencies {
    const val annotations = "io.swagger.core.v3:swagger-core:${Versions.swaggerAnnotations}"
    const val parser = "io.swagger.parser.v3:swagger-parser:${Versions.swaggerParser}"

}

object TracingDependencies {
    const val micrometerTracing = "io.micrometer:micrometer-tracing:${Versions.micrometer}"
    const val micrometerBridge = "io.micrometer:micrometer-tracing-bridge-otel:${Versions.micrometer}"
    const val exporterOtlp = "io.opentelemetry:opentelemetry-exporter-otlp:${Versions.exporterOtlp}"
}

object DatabaseDependencies {
    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
    const val flywayCore = "org.flywaydb:flyway-core:${Versions.flywayCore}"
}

object AvroDependencies {
    const val avro = "org.apache.avro:avro:${Versions.avro}"
    const val confluentAvroSerializer = "io.confluent:kafka-avro-serializer:${Versions.confluentAvroSerializer}"
}

object HibernateDependencies {
    const val hibernateUtils = "io.hypersistence:hypersistence-utils-hibernate-62:${Versions.hibernateUtils}"
    const val hibernateCore = "org.hibernate.orm:hibernate-core:${Versions.hibernateCore}"
    const val validator = "org.hibernate.validator:hibernate-validator:${Versions.hibernateValidator}"
}
