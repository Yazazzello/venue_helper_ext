plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.foursquare.com/v2/\"")
            buildConfigField("String", "F_CLIENT_ID", "\"CLIENT_ID\"")
            buildConfigField("String", "F_CLIENT_SECRET", "\"CLIENT_SECRET\"")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }
    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {

    // Kotlin and Coroutines
    implementation(Libs.KOTLIN)
    implementation(Libs.COROUTINESCORE)
    implementation(Libs.COROUTINESANDROID)
    // UI and Appcompat
    implementation(Libs.ANDROIDX_APPCOMPAT)
    implementation(Libs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(Libs.ANDROIDX_CORE_KTX)
    implementation(Libs.FRAGMENTKTX)
    implementation(Libs.CONSTRAINTLAYOUT)
    implementation(Libs.SWIPEREFRESHLAYOUT)
    implementation(Libs.RECYCLERVIEW)
    implementation(Libs.VIEWPAGER)
    implementation(Libs.MATERIAL)
    // ViewModel and LiveData
    implementation(Libs.VIEWMODELKTX)
    implementation(Libs.LIVEDATAKTX)
    implementation(Libs.LIFECYCLERUNTIMEKTX)
    // Navigation
    implementation(Libs.NAVIGATIONKTX)
    implementation(Libs.NAVIGATIONUIKTX)
    // Room
    implementation(Libs.ROOMRUNTIME)
    implementation(Libs.ROOMKTX)
    kapt(Libs.ROOMCOMPILER)
    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFITCONVERTERGSON)
    implementation(Libs.LOGGINGINTERCEPTOR)
    // Koin
    implementation(Libs.KOINANDROID)
    implementation(Libs.KOINVIEWMODEL)
    // Glide
    implementation(Libs.GLIDE)
    kapt(Libs.GLIDECOMPILER)
    // PhotoView
    implementation(Libs.PHOTOVIEW)
    // Timber
    implementation(Libs.TIMBER)
    // Workmanager
    implementation(Libs.WORKMANAGER)


    // Unit Tests
    testImplementation(TestingLibs.JUNIT)
    testImplementation(TestingLibs.MOCKK)
    testImplementation(TestingLibs.KOTESTASSERTIONS)
    testImplementation(TestingLibs.ANDROIDXTEST)
    testImplementation(TestingLibs.ARCHTEST)
    testImplementation(TestingLibs.COROUTINESTEST)
    testImplementation(TestingLibs.ROBOLECTRIC)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)
}
