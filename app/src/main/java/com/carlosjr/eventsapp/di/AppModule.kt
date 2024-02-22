package com.carlosjr.eventsapp.di

import com.carlosjr.eventsapp.data.remote.EventsAPI
import com.carlosjr.eventsapp.data.repository.EventsRepositoryImp
import com.carlosjr.eventsapp.domain.repository.EventsRepository
import com.carlosjr.eventsapp.domain.usercase.GetEventsUseCase
import com.carlosjr.eventsapp.domain.usercase.GetEventsUseCaseImp
import com.carlosjr.eventsapp.helper.Constants
import com.carlosjr.eventsapp.presentation.viewmodel.EventsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesEventsApi(): EventsAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventsAPI::class.java)
    }

    @Provides
    fun provideEventsRepository(api: EventsAPI): EventsRepository {
        return EventsRepositoryImp(api)
    }

    @Provides
    fun provideEventsUseCase(repository: EventsRepository): GetEventsUseCase {
        return GetEventsUseCaseImp(repository)
    }

    @Provides
    fun providesUserViewModel(useCase: GetEventsUseCase): EventsViewModel {
        return EventsViewModel(useCase)
    }
}