object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.37.2"
    const val NAVIGATION = "2.3.0"
    const val AGP = "4.0.0"
    const val DETEKT = "1.10.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT_GRADLE = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"

    const val ANDROIDGRADLEPLUGIN = "4.0.1"
    const val COROUTINES = "1.3.8"
    const val FRAGMENT = "1.2.5"
    const val CONSTRAINTLAYOUT = "1.1.3"
    const val SWIPEREFRESHLAYOUT = "1.1.0"
    const val RECYCLERVIEW = "1.1.0"
    const val VIEWPAGER = "1.0.0"
    const val MATERIAL = "1.2.0"
    const val LIFECYCLE = "2.2.0"
    const val ARCH = "2.1.0"
    const val ROOM = "2.2.5"
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.8.1"
    const val KOIN = "2.1.6"
    const val GLIDE = "4.11.0"
    const val PHOTOVIEW = "2.0.0"
    const val TIMBER = "4.7.1"
    const val WORKMANAGER = "2.4.0"
    const val MOCKK = "1.10.0"
    const val KOTEST = "4.1.2"
    const val ANDROIDXTEST = "1.2.0"
    const val ROBOLECTRIC = "4.3.1"
}

object BuildPlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.AGP}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val safeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
}

object Libs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val COROUTINESCORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINESANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val FRAGMENTKTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINTLAYOUT}"
    const val SWIPEREFRESHLAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPEREFRESHLAYOUT}"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
    const val VIEWPAGER = "androidx.viewpager2:viewpager2:${Versions.VIEWPAGER}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val VIEWMODELKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIVEDATAKTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLERUNTIMEKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
    const val NAVIGATIONKTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATIONUIKTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val ROOMRUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOMKTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOMCOMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFITCONVERTERGSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val LOGGINGINTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val KOINANDROID = "org.koin:koin-android:${Versions.KOIN}"
    const val KOINVIEWMODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDECOMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val PHOTOVIEW = "com.github.chrisbanes:PhotoView:${Versions.PHOTOVIEW}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val WORKMANAGER = "androidx.work:work-runtime-ktx:${Versions.WORKMANAGER}"
}

object TestingLibs {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val KOTESTASSERTIONS = "io.kotest:kotest-assertions-core-jvm:${Versions.KOTEST}"
    const val ANDROIDXTEST = "androidx.test:core:${Versions.ANDROIDXTEST}"
    const val ARCHTEST = "androidx.arch.core:core-testing:${Versions.ARCH}"
    const val COROUTINESTEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
