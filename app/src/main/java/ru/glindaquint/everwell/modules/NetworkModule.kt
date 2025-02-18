package ru.glindaquint.everwell.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.network.services.AuthorizationService
import ru.glindaquint.everwell.network.services.TasksService

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {
    @Provides
    fun provideAuthService(): AuthorizationService = RetrofitFactory.build(AuthorizationService::class.java)

    @Provides
    fun provideTasksService(): TasksService = RetrofitFactory.build(TasksService::class.java)
}
