package com.carlosjr.eventsapp.di

import com.carlosjr.eventsapp.data.remote.EventsAPI
import com.carlosjr.eventsapp.data.repository.EventsRepositoryImp
import com.carlosjr.eventsapp.domain.repository.EventsRepository
import com.carlosjr.eventsapp.domain.usecase.CheckInUseCase
import com.carlosjr.eventsapp.domain.usecase.GetEventsUseCase
import com.carlosjr.eventsapp.domain.usecase.GetEventsUseCaseImpl
import com.carlosjr.eventsapp.helper.Constants
import com.carlosjr.eventsapp.presentation.viewmodel.DetailsViewModel
import com.carlosjr.eventsapp.presentation.viewmodel.EventsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun providesEventsApi(): EventsAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkhttp())
            .build()
            .create(EventsAPI::class.java)
    }

    @Provides
    fun provideEventsRepository(api: EventsAPI): EventsRepository {
        return EventsRepositoryImp(api)
    }

    @Provides
    fun provideEventsUseCase(repository: EventsRepository): GetEventsUseCase {
        return GetEventsUseCaseImpl(repository)
    }

    @Provides
    fun provideCheckInUseCase(repository: EventsRepository): CheckInUseCase {
        return CheckInUseCase(repository)
    }

    @Provides
    fun providesEventsViewModel(useCase: GetEventsUseCase): EventsViewModel {
        return EventsViewModel(useCase)
    }

    @Provides
    fun providesDetailsViewModel(useCase: CheckInUseCase): DetailsViewModel {
        return DetailsViewModel(useCase)
    }
}