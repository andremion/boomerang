plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kmmbridge)
    `maven-publish`
}

kmmbridge {
    mavenPublishArtifacts()
    spm(projectDir.path)
    timestampVersions()
}

group = "io.github.andremion"
version = "0.1"

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

publishing {
    repositories {
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/andremion/boomerang")
            // Setup your credentials in ~/.gradle/gradle.properties
            // githubUsername=your_username
            // githubPassword=your_token
            credentials(PasswordCredentials::class)
        }
    }
}
