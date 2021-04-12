package com.houm.android.houmweather.dependency

import com.google.gson.GsonBuilder
import com.houm.android.houmweather.BuildConfig
import com.houm.android.houmweather.api.WeatherApi
import com.houm.android.houmweather.retrofit.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(ApplicationComponent::class)
object RetrofitServiceModule {

    private object Config {
        const val READ_TIMEOUT = 20L
        const val WRITE_TIMEOUT = 20L
        const val CONNECT_TIMEOUT = 15L

        const val API_VERSION = "data/2.5/"

        const val URL_STAGING = "https://api.openweathermap.org/$API_VERSION"
        const val URL_PRODUCTION = "https://api.openweathermap.org/$API_VERSION"
    }

    fun getBaseUrl(): String {
        return if (BuildConfig.DEBUG) Config.URL_STAGING else Config.URL_PRODUCTION
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    fun provideNetworkResponseAdapterFactory(): NetworkResponseAdapterFactory =
        NetworkResponseAdapterFactory()

    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(networkResponseAdapterFactory)
        .client(okHttpClient)

    private val loggingInterceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        loggingInterceptor
    }

    @Provides
    fun provideLegacyApi(builder: Retrofit.Builder): WeatherApi =
        builder.baseUrl(getBaseUrl()).build().create(WeatherApi::class.java)
}
