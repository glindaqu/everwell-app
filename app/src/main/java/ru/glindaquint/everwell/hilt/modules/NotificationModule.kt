package ru.glindaquint.everwell.hilt.modules

import android.app.Application
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.glindaquint.everwell.services.notificationsScheduler.AndroidNotificationScheduler
import ru.glindaquint.everwell.services.notificationsScheduler.NotificationScheduler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationManagerCompat(context: Context): NotificationManagerCompat = NotificationManagerCompat.from(context)

    @Provides
    @Singleton
    fun provideNotificationScheduler(
        context: Application,
        notificationManager: NotificationManagerCompat,
    ): NotificationScheduler = AndroidNotificationScheduler(context, notificationManager)
}
