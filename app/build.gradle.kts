import com.hustunique.Dep.Build

plugins {
    id("com.android.application")
    id("org.mozilla.rust-android-gradle.rust-android")
}

android {
    ndkVersion = Build.NDK_VERSION
    compileSdk = Build.COMPILE_SDK_VERSION
    buildToolsVersion = Build.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = Build.APPLICATION_ID
        minSdk = Build.MIN_SDK_VERSION
        targetSdk = Build.TARGET_SDK_VERSION
        versionCode = Build.BUILD_VERSION_CODE
        versionName = Build.BUILD_VERSION_NAME
    }
}

tasks.whenTaskAdded {
    if (name == "javaPreCompileDebug" || name == "javaPreCompileRelease") {
        dependsOn("cargoBuild")
    }
}

cargo {
    module = "../rust"
    libname = "rust_app"
    targets = arrayListOf("arm64")
    verbose = true
//    profile = "release"
}
