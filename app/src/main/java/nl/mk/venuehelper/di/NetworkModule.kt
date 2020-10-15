package nl.mk.venuehelper.di

import nl.mk.venuehelper.BuildConfig
import nl.mk.venuehelper.network.FoursquareService
import nl.mk.venuehelper.network.VenuesRemoteSource
import nl.mk.venuehelper.network.VenuesRemoteSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideLoggingInterceptor() }

    single { provideOkHttpClient(loggingInterceptor = get()) }

    single<FoursquareService> { provideRetrofitService(okHttpClient = get()) }

    single<VenuesRemoteSource> { VenuesRemoteSourceImpl(foursquareService = get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

private fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

inline fun <reified T> provideRetrofitService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}