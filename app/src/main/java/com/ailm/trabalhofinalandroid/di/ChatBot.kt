package com.ailm.trabalhofinalandroid.di

import com.ailm.trabalhofinalandroid.domain.Message
import com.ailm.trabalhofinalandroid.domain.Response
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

@Module
@InstallIn(SingletonComponent::class)
object ChatBotyModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://botcatador2.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun provideRickAndMortyRetrofitService(retrofit: Retrofit): HerokuDialogFlow =
        retrofit.create(HerokuDialogFlow::class.java)
}

    interface HerokuDialogFlow {

        @POST("message/text/send")
        @Headers("Content-Type: application/json")
        suspend fun sendMessageAsync(@Body message: Message): Array<Response>

    }

