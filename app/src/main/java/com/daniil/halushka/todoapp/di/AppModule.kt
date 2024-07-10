package com.daniil.halushka.todoapp.di

import android.content.Context
import com.daniil.halushka.todoapp.constants.NetworkConstants
import com.daniil.halushka.todoapp.data.network.api.TodoItemService
import com.daniil.halushka.todoapp.data.network.repository.SharedPreferencesRepository
import com.daniil.halushka.todoapp.data.network.server.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesRepository(context: Context): SharedPreferencesRepository {
        return SharedPreferencesRepository(context)
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(sharedPrefRepository: SharedPreferencesRepository): NetworkInterceptor {
        return NetworkInterceptor(sharedPrefRepository)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoItemService(retrofit: Retrofit): TodoItemService {
        return retrofit.create(TodoItemService::class.java)
    }
}
