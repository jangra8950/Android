package com.example.module

import com.example.services.ApiHelper
import com.example.services.ApiHelperImpl
import com.example.services.ApiRequest
import com.example.services.MyRepo
import com.example.services.RetrofitInstance
import com.example.services.RetrofitInstance.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("BASE")
    fun provideBaseUrl():String{
        return BASE_URL
    }

    @Provides
    @Named("FAKE")
    fun provideFakeUrl():String{
        return RetrofitInstance.FAKE_URL
    }

    @Provides
    @Singleton
    fun provideApiRequest(@Named("BASE") url:String):ApiRequest{
      return Retrofit.Builder()
          .baseUrl(url)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(ApiRequest::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(api:ApiRequest):ApiHelper{
        return ApiHelperImpl(api)
    }

    @Provides
    @Singleton
    fun provideMyRepo(api:ApiHelper):MyRepo{
        return MyRepo(api)
    }
}