package ru.glindaquint.everwell.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager

@Module
@InstallIn(ActivityRetainedComponent::class)
object SharedPreferencesModule {
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences("everwell_prefs", Context.MODE_PRIVATE)

    @Provides
    fun providePreferenceManager(preferenceManagerImpl: PreferenceManagerImpl): PreferencesManager = preferenceManagerImpl
}
