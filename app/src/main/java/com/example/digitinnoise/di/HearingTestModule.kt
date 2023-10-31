package com.example.digitinnoise.di

import android.content.Context
import com.example.digitinnoise.hearingTest.domain.AndroidAudioPlayer
import com.example.digitinnoise.hearingTest.domain.AudioPlayer
import com.example.digitinnoise.hearingTest.domain.HearingTest
import com.example.digitinnoise.hearingTest.domain.Test
import com.example.digitinnoise.hearingTest.domain.TripletGenerator
import com.example.digitinnoise.hearingTest.domain.UniqueTripletGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
class HearingTestModule {

    @Provides
    @Singleton
    fun provideTripletGenerator(): TripletGenerator {
        return UniqueTripletGenerator(Random)
    }

    @Provides
    @Singleton
    fun provideHearingTest(
        tripletGenerator: TripletGenerator,
    ): Test {
        return HearingTest(tripletGenerator)
    }

    @Provides
    @Singleton
    fun provideAudioPlayer(
        @ApplicationContext activityContext: Context
    ): AudioPlayer {
        return AndroidAudioPlayer(activityContext)
    }
}