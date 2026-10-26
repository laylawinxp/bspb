plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")

    testImplementation("io.qameta.allure:allure-rest-assured:2.24.0")
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")

    testImplementation("org.junit.platform:junit-platform-suite")

    testImplementation(platform("io.cucumber:cucumber-bom:7.34.8"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")

    testImplementation("org.seleniumhq.selenium:selenium-java:4.51.0")
    testImplementation("org.seleniumhq.selenium:selenium-grid:4.51.0")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")

    testImplementation("org.springframework:spring-web:7.0.9")

    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 1
}
