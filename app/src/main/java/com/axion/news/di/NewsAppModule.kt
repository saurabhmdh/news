package com.axion.news.di

import android.app.Application
import com.axion.news.BuildConfig
import com.axion.news.constants.Constants
import com.axion.news.network.api.NetworkApi
import com.axion.news.network.retrofit.RetrofitBuilderFactory
import com.axion.news.network.retrofit.UtilOkHttpClient
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [ViewModelModule::class, DBProviderModule::class])
class NewsAppModule {
    @Singleton
    @Provides
    fun provideWebService(okHttpClient: OkHttpClient): NetworkApi = RetrofitBuilderFactory.getApiServices(okHttpClient)


    @Provides
    @Singleton
    internal fun provideOkHttpClient(context: Application): OkHttpClient {
        val client = UtilOkHttpClient.unsafeOkHttpClient
        val builder = client.newBuilder()

        builder.takeIf { BuildConfig.DEBUG}.also {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            it?.addInterceptor(interceptor)
            it?.addInterceptor(ChuckInterceptor(context))
        }

        builder.apply {
            readTimeout(Constants.TIMEOUT_SECOND.toLong(), TimeUnit.SECONDS)
            connectTimeout(Constants.TIMEOUT_SECOND.toLong(), TimeUnit.SECONDS)
        }

        return builder.build()
    }
}