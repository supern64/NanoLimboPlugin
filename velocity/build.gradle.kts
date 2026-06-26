group = "com.bivashy.limbo"

repositories {
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    implementation(project(":api"))
    implementation(libs.lamp.common)
    implementation(libs.lamp.velocity)
    implementation(libs.kyori.adventure.text.serializer.minimessage)

    compileOnly(libs.configurate.yaml)
    compileOnly(libs.velocity.api)
    annotationProcessor(libs.velocity.api)
}

tasks.shadowJar {
    manifest {
        attributes(
            mapOf("Main-Class" to "ua.nanit.limbo.NanoLimbo")
        )
    }
}