plugins {
    kotlin(Plugins.jetbrainsKotlinJvm) version Versions.kotlin
    kotlin(Plugins.spring) version Versions.kotlin
}

dependencies {
    implementation(project(":domain"))
    implementation(SpringBootDependencies.web)
    implementation(SpringBootDependencies.dataJpa)
    implementation(SpringBootDependencies.security)
    implementation(SpringBootDependencies.oauth2)
    testImplementation(TestDependencies.kotlinTest)
}
