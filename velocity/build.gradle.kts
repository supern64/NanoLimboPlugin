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
    implementation(libs.lamp.brigadier)

    compileOnly(libs.kyori.adventure.text.serializer.minimessage)
    compileOnly(libs.configurate.yaml)
    compileOnly(libs.velocity.api)
    annotationProcessor(libs.velocity.api)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    build {
        dependsOn("shadowJar")
    }
    jar {
        from("../LICENSE")
        archiveBaseName = "NanoLimboPlugin"
        archiveClassifier = "velocity-slim"
    }
    shadowJar {
        relocate("revxrsal.commands", "com.bivashy.shaded.revxrsal.commands")
        minimize()
        archiveBaseName = "NanoLimboPlugin"
        archiveClassifier = "velocity"
    }
    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}