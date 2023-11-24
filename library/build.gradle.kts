import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.maven.publish)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    jvm()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
        }
        androidMain.dependencies {
            implementation(dependencies.platform(libs.androidx.compose.bom))
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
    }
}

android {
    namespace = "io.github.andremion.boomerang.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    publishing {
        publishing {
            multipleVariants {
                allVariants()
                withSourcesJar()
            }
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["default"])
            }
        }
    }
}
