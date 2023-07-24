package com.example.main.di.injectables

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.common.Constants
import com.ekenya.rnd.common.api.getparkings.ParkingService
import com.ekenya.rnd.common.api.registration.RegistrationService
import com.ekenya.rnd.common.utils.NetworkInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {//provide HttpLogginInterceptor
@Provides
@ModuleScope
fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }



    @Provides
    @ModuleScope
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    //provide GsonConverterFactory
    @ModuleScope
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    //provide OkHttpClient
    @Provides
    @ModuleScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(NetworkInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().addHeader("User-Agent", "APP")
                        .build()
                )
            }.build()



    @Provides
    @ModuleScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @ModuleScope
    fun provideParkingApi(retrofit: Retrofit): ParkingService =
        retrofit.create(ParkingService::class.java)
}