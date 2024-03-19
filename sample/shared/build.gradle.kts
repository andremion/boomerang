plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.library)
            api(libs.precompose.viewmodel)
            implementation(libs.kotlinx.coroutines)
        }
    }
}
