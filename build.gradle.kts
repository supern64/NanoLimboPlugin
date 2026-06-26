plugins {
    id("java")
    alias(libs.plugins.shadow)
}

allprojects {
    group = "me.cirnoslab"
    version = "1.12.0"
}

val libz = libs // small workaround
subprojects {
    apply(plugin = "java")
    apply(plugin = libz.plugins.shadow.get().pluginId)

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(libz.configurate.yaml)

        implementation(libz.netty.handler)
        implementation(variantOf(libz.netty.transport.native.epoll) { classifier("linux-x86_64") })
        implementation(variantOf(libz.netty.transport.native.epoll) { classifier("linux-aarch_64") })
        implementation(variantOf(libz.netty.transport.native.io.uring) { classifier("linux-x86_64") })
        implementation(variantOf(libz.netty.transport.native.io.uring) { classifier("linux-aarch_64") })
        implementation(variantOf(libz.netty.transport.native.kqueue) { classifier("osx-x86_64") })
        implementation(variantOf(libz.netty.transport.native.kqueue) { classifier("osx-aarch_64") })

        implementation(libz.kyori.adventure.api)
        implementation(libz.kyori.adventure.text.serializer.gson)
        implementation(libz.kyori.adventure.text.serializer.legacy)
        implementation(libz.kyori.adventure.text.serializer.json.legacy.impl)
        implementation(libz.kyori.adventure.text.serializer.plain)
        implementation(libz.kyori.adventure.text.serializer.minimessage)
        implementation(libz.kyori.adventure.nbt)

        implementation(libz.gson)

        compileOnly(libz.lombok)
        annotationProcessor(libz.lombok)
    }

    tasks.compileJava {
        options.encoding = "UTF-8"
    }

    tasks.build {
        dependsOn("shadowJar")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.shadowJar {
        from("LICENSE")
        minimize()
        relocate("io.netty", "ua.nanit.shaded.io.netty")
        relocate("io.leangen", "ua.nanit.shaded.io.leangen")
        relocate("com.google.errorprone", "ua.nanit.shaded.com.google.errorprone")
        relocate("com.google.gson", "ua.nanit.shaded.com.google.gson")
        relocate("net.kyori.adventure", "ua.nanit.shaded.net.kyori.adventure")
        relocate("net.kyori.examination", "ua.nanit.shaded.net.kyori.examination")
        relocate("net.kyori.option", "ua.nanit.shaded.net.kyori.option")
        relocate("org.spongepowered.configurate", "ua.nanit.shaded.org.spongepowered.configurate")
        relocate("revxrsal.commands", "ua.nanit.shaded.revxrsal.commands")
        minimize()
    }
}
