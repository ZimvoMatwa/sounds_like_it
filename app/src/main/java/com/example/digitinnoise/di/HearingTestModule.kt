package com.example.digitinnoise.di

import android.content.Context
import com.example.digitinnoise.hearingTest.AndroidAudioPlayer
import com.example.digitinnoise.hearingTest.AudioPlayer
import com.example.digitinnoise.hearingTest.HearingTest
import com.example.digitinnoise.hearingTest.Test
import com.example.digitinnoise.hearingTest.TripletGenerator
import com.example.digitinnoise.hearingTest.UniqueTripletGenerator
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
        audioPlayer: AudioPlayer
    ): Test {
        return HearingTest(tripletGenerator, audioPlayer)
    }

    @Provides
    @Singleton
    fun provideAudioPlayer(
        @ApplicationContext activityContext: Context
    ): AudioPlayer {
        return AndroidAudioPlayer(activityContext)
    }
}