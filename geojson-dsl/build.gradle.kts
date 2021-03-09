plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm()
    js {
        browser {
        }
        nodejs {
        }
    }
    // For ARM, should be changed to iosArm32 or iosArm64
    // For Linux, should be changed to e.g. linuxX64
    // For MacOS, should be changed to e.g. macosX64
    // For Windows, should be changed to e.g. mingwX64
    linuxX64("native")
    mingwX64("mingw")
    macosX64("macos")
    ios("ios")

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(project(":geojson"))
        implementation(deps.kotlinx.serialization)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }

    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }

    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        implementation(kotlin("stdlib-js"))
    }

    sourceSets["jsTest"].dependencies {
        implementation(kotlin("test-js"))
    }

    sourceSets["nativeMain"].dependencies {
    }
    sourceSets["nativeTest"].dependencies {
    }

    sourceSets {
        val nativeMain by getting {}
        getByName("macosMain").dependsOn(nativeMain)
        getByName("iosMain").dependsOn(nativeMain)
        getByName("mingwMain").dependsOn(nativeMain)

        val nativeTest by getting {}
        getByName("macosTest").dependsOn(nativeTest)
        getByName("iosTest").dependsOn(nativeTest)
        getByName("mingwTest").dependsOn(nativeTest)
    }
}

apply(plugin = "com.vanniktech.maven.publish")
