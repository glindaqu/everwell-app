package ru.glindaquint.everwell.hilt.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.network.services.AuthorizationNetworkService
import ru.glindaquint.everwell.network.services.BloodPressureNetworkService
import ru.glindaquint.everwell.network.services.TasksNetworkService
import ru.glindaquint.everwell.network.services.UserNetworkService

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {
    @Provides
    fun provideAuthService(retrofitFactory: RetrofitFactory): AuthorizationNetworkService =
        retrofitFactory.getInstance().create(AuthorizationNetworkService::class.java)

    @Provides
    fun provideTasksService(retrofitFactory: RetrofitFactory): TasksNetworkService =
        retrofitFactory.getInstance().create(TasksNetworkService::class.java)

    @Provides
    fun provideUserService(retrofitFactory: RetrofitFactory): UserNetworkService =
        retrofitFactory.getInstance().create(UserNetworkService::class.java)

    @Provides
    fun provideBloodPressureService(retrofitFactory: RetrofitFactory): BloodPressureNetworkService =
        retrofitFactory.getInstance().create(BloodPressureNetworkService::class.java)
}
