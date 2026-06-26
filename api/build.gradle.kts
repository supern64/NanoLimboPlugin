dependencies {
    implementation(libs.configurate.yaml)
    implementation(libs.netty.handler)
    implementation(libs.kyori.adventure.nbt)
}

tasks.shadowJar {
    manifest {
        attributes(
            mapOf("Main-Class" to "ua.nanit.limbo.NanoLimbo")
        )
    }
}