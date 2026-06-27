plugins {
    id("java")
}

group = "ua.nanit"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.configurate.yaml)

    compileOnly(libs.netty.handler)
    compileOnly(variantOf(libs.netty.transport.native.epoll) { classifier("linux-x86_64") })
    compileOnly(variantOf(libs.netty.transport.native.epoll) { classifier("linux-aarch_64") })
    compileOnly(variantOf(libs.netty.transport.native.io.uring) { classifier("linux-x86_64") })
    compileOnly(variantOf(libs.netty.transport.native.io.uring) { classifier("linux-aarch_64") })
    compileOnly(variantOf(libs.netty.transport.native.kqueue) { classifier("osx-x86_64") })
    compileOnly(variantOf(libs.netty.transport.native.kqueue) { classifier("osx-aarch_64") })

    compileOnly(libs.kyori.adventure.api)
    compileOnly(libs.kyori.adventure.text.serializer.gson)
    compileOnly(libs.kyori.adventure.text.serializer.legacy)
    compileOnly(libs.kyori.adventure.text.serializer.json.legacy.impl)
    compileOnly(libs.kyori.adventure.text.serializer.plain)
    compileOnly(libs.kyori.adventure.text.serializer.minimessage)
    compileOnly(libs.kyori.adventure.nbt)

    compileOnly(libs.gson)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    jar {
        from("../LICENSE")
        archiveBaseName = "NanoLimboPlugin"
        archiveClassifier = "api"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}