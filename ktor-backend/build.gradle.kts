val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val pgVersion: String by project
val exposedVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.0"
}

group = "com.example"

tasks {
    create("stage").dependsOn("installDist")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()

    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:$pgVersion")
    implementation("com.zaxxer:HikariCP:5.0.1")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")


    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}