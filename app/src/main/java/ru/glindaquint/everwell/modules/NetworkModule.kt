package ru.glindaquint.everwell.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.network.services.AuthorizationNetworkService
import ru.glindaquint.everwell.network.services.TasksNetworkService
import ru.glindaquint.everwell.network.services.UserNetworkService

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {
    @Provides
    fun provideAuthService(): AuthorizationNetworkService = RetrofitFactory.build(AuthorizationNetworkService::class.java)

    @Provides
    fun provideTasksService(): TasksNetworkService = RetrofitFactory.build(TasksNetworkService::class.java)

    @Provides
    fun provideUserService(): UserNetworkService = RetrofitFactory.build(UserNetworkService::class.java)
}
