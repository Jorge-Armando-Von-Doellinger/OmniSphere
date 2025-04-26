plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "omnisphere.microsservices"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven("https://repo.spring.io/release")
}

dependencies {
	// Web e validação
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux") // ✅ Mono/Flux
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.security:spring-security-core:6.2.1")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc") // ✅ R2DBC
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE") // ✅ R2DBC driver

	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	// Elasticsearch
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

	// JSON
	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Validação
	implementation("jakarta.validation:jakarta.validation-api:3.1.1")
	implementation("org.hibernate.validator:hibernate-validator:8.0.2.Final")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
