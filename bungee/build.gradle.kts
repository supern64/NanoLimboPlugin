group = "com.bivashy.limbo"

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/groups/public/") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://libraries.minecraft.net") }
}

dependencies {
    implementation(project(":api"))
    implementation(libs.lamp.common)
    implementation(libs.lamp.bungee)

    compileOnly(libs.configurate.yaml)
    compileOnly(libs.bungeecord.api)
}

tasks.shadowJar {
    manifest {
        attributes(
            mapOf("Main-Class" to "ua.nanit.limbo.NanoLimbo")
        )
    }
}