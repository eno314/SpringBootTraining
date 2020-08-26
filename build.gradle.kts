import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val ktlintVersion = "9.3.0"
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
    id("org.jlleitschuh.gradle.ktlint-idea") version ktlintVersion

    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    jacoco
}

group = "com.github.eno314"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

repositories {
    mavenCentral()
}

dependencies {
    val mapstructKotlinVersion = "1.3.1.2"
    val mapstructVersion = "1.3.1.Final"

    api("com.github.pozo:mapstruct-kotlin:$mapstructKotlinVersion")
    api("org.mapstruct:mapstruct:${mapstructVersion}")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.github.pozo:mapstruct-kotlin:1.3.1.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    kapt("com.github.pozo:mapstruct-kotlin-processor:$mapstructKotlinVersion")
    kapt("org.mapstruct:mapstruct-processor:${mapstructVersion}")

    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
}
