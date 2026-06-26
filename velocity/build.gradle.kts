plugins {
    id("java")
    alias(libs.plugins.shadow)
}

group = "com.bivashy.limbo"

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    implementation(project(":api"))
    implementation(libs.lamp.common)
    implementation(libs.lamp.velocity)

    compileOnly(libs.kyori.adventure.text.serializer.minimessage)
    compileOnly(libs.configurate.yaml)
    compileOnly(libs.velocity.api)
    annotationProcessor(libs.velocity.api)
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    relocate("revxrsal.commands", "com.bivashy.shaded.revxrsal.commands")
    minimize()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}