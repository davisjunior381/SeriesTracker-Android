package com.example.seriestracker.di

import com.example.seriestracker.data.remote.EpisodateApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://www.episodate.com/api/"  // IMPORTANT : le slash final est obligatoire !

@Module
@InstallIn(SingletonComponent::class)  // Singleton = une seule instance pour toute l'app
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // Intercepteur qui affiche les requêtes/réponses dans Logcat (très utile en dev)
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // BODY = tout (headers + body)
            // Alternatives possibles : BASIC, HEADERS, NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)  // ajoute le logging
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)                     // on passe le client avec logging
            .addConverterFactory(GsonConverterFactory.create())  // Gson transforme JSON → objets Kotlin
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): EpisodateApiService {
        // Crée l'interface Retrofit à partir de la config
        return retrofit.create(EpisodateApiService::class.java)
    }
}