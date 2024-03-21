plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kmmbridge)
    `maven-publish`
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

// Set up in your ~/.gradle/gradle.properties file
// GITHUB_REPO=your_repo
// GITHUB_PUBLISH_USER=your_username
// GITHUB_PUBLISH_TOKEN=your_token
addGithubPackagesRepository()

kmmbridge {
    mavenPublishArtifacts()
    spm()
}
