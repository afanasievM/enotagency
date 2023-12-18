
import java.net.URI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "ua.com"
version = "2.2.1"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = URI("https://jitpack.io") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.1.0")
	implementation("io.github.microutils:kotlin-logging:2.0.8")
	implementation("com.taskadapter:trello-java-wrapper:0.14")
	implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
	implementation("commons-net:commons-net:3.10.0")
	implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
	implementation("com.google.apis:google-api-services-oauth2:v2-rev157-1.25.0")
	implementation("com.google.apis:google-api-services-sheets:v4-rev612-1.25.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot {
	buildInfo()
}
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
