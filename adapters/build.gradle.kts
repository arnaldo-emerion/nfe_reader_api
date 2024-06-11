plugins {
    id(Plugins.springDependencyManagement) version Versions.springDependencyManagement
    kotlin(Plugins.jetbrainsKotlinJvm) version Versions.kotlin
    kotlin(Plugins.spring) version Versions.kotlin
    id(Plugins.openApiGenerate) version Versions.openApiGenerate
    id(Plugins.kotlinJpa) version Versions.kotlinJpa
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation(SpringBootDependencies.web)
    implementation(SpringBootDependencies.dataJpa)
    implementation(HibernateDependencies.validator)
    implementation(OkHttpDependencies.okHttp)
    implementation(JacksonDependencies.moduleKotlin)
    implementation(JavaxDependencies.JAVAX_SERVLET)
    implementation(JavaxDependencies.JAVAX_VALIDATION)
    implementation(SwaggerDependencies.annotations)

    testImplementation(TestDependencies.kotlinTest)
}

/*
val appNamespace = "$group.nfereader"
val openApiGeneratedResources = "openapi-generated"
val openApiSrcFolder = "$projectDir/$openApiGeneratedResources"
val openApiFileLocation = "$projectDir/src/main/resources/static/openapi.yaml"

val openApiGeneratorName = "kotlin"
val openApiKotlinSrcFolder = "$openApiSrcFolder/src/main/kotlin"
val openApiJavaSrcFolder = "$openApiSrcFolder/src/main/java"
openApiGenerate {
    val configs = mapOf(
        "interfaceOnly" to "true",
        "reactive" to "false",
        "exceptionHandler" to "false",
        "gradleBuildFile" to "true",
        "useTags" to "true",
        "enumPropertyNaming" to "original",
        "serializationLibrary" to "jackson"
    )

    inputSpec.set(openApiFileLocation)
    outputDir.set(openApiSrcFolder)
    apiPackage.set("$appNamespace.api")
    modelPackage.set("$appNamespace.dtos")
    packageName.set(appNamespace)
    generatorName.set(openApiGeneratorName)
    configOptions.set(configs)
    typeMappings.put("DateTime", "Date")
    importMappings.put("Date", "java.util.Date")
}*/

val openApiGeneratedResources = "openapi-generated"
val openApiSrcFolder = "$buildDir/$openApiGeneratedResources"
val openApiKotlinSrcFolder = "$openApiSrcFolder/src/main/kotlin"
openApiGenerate {
    inputSpec.set("$rootDir/adapters/src/main/resources/static/openapi.yaml")
    generatorName.set("kotlin-spring")
    library.set("spring-boot")
    outputDir.set(openApiSrcFolder)
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useBeanValidation" to "true",
            "performBeanValidation" to "true",
            "modelPackage" to "br.com.asoft.nfereader.model",
            "apiPackage" to "br.com.asoft.nfereader.controller",
            "serializableModel" to "true",
            "developerEmail" to "arnaldotecadm@hotmail.com",
            "developerOrganizationUrl" to "https://www.linkedin.com/in/arnaldocicero",
            "skipDefaultInterface" to "true"
        )
    )
}

sourceSets {
    main {
        java {
            //setSrcDirs(srcDirs.plus(openApiKotlinSrcFolder).plus(openApiJavaSrcFolder))
            setSrcDirs(srcDirs.plus(openApiKotlinSrcFolder))
        }
    }
}

tasks {
    build {
        dependsOn("openApiGenerate")
    }
    compileKotlin {
        dependsOn("openApiGenerate")
    }
}