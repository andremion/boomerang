import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

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

val GROUP: String by project
val AUTO_VERSION = project.property(
    if (project.hasProperty("AUTO_VERSION")) {
        "AUTO_VERSION"
    } else {
        "XCFRAMEWORK_VERSION"
    }
) as String

group = GROUP
version = AUTO_VERSION

val BUILD_TYPE = if (project.hasProperty("ENABLE_PUBLISHING")) {
    NativeBuildType.RELEASE
} else {
    NativeBuildType.DEBUG
}

kmmbridge {
    buildType.set(BUILD_TYPE)
    mavenPublishArtifacts()
    spm()
}

// Set up in your ~/.gradle/gradle.properties file
// GITHUB_REPO=your_repo
// GITHUB_PUBLISH_USER=your_username
// GITHUB_PUBLISH_TOKEN=your_token
addGithubPackagesRepository()
