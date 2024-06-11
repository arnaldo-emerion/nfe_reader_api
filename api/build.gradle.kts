plugins {
    id(Plugins.springFrameworkBoot) version Versions.springFramework
    id(Plugins.springDependencyManagement) version Versions.springDependencyManagement
    kotlin(Plugins.jetbrainsKotlinJvm) version Versions.kotlin
    kotlin(Plugins.spring) version Versions.kotlin
}

repositories {
    mavenCentral()
}

springBoot {
    buildInfo()
}

dependencies {
    implementation(project(":adapters"))
    implementation(project(":application"))
    implementation(SpringBootDependencies.web)
    implementation(SpringBootDependencies.oauth2)
    implementation(SpringBootDependencies.security)
    implementation(DatabaseDependencies.postgresql)
    implementation(SwaggerDependencies.parser)
    implementation(SpringBootDependencies.springDocOpenApi)
    implementation(JavaxDependencies.jaxbApi)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}