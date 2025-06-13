package ru.glindaquint.everwell.hilt.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.network.services.AdviceRepository
import ru.glindaquint.everwell.network.services.AuthorizationNetworkService
import ru.glindaquint.everwell.network.services.BloodPressureNetworkService
import ru.glindaquint.everwell.network.services.FamilyGroupService
import ru.glindaquint.everwell.network.services.FeedNetworkService
import ru.glindaquint.everwell.network.services.ProductsNetworkService
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

    @Provides
    fun provideProductsService(retrofitFactory: RetrofitFactory): ProductsNetworkService =
        retrofitFactory.getInstance().create(ProductsNetworkService::class.java)

    @Provides
    fun provideFeedService(retrofitFactory: RetrofitFactory): FeedNetworkService =
        retrofitFactory.getInstance().create(FeedNetworkService::class.java)

    @Provides
    fun provideAdviceRepository(retrofitFactory: RetrofitFactory): AdviceRepository =
        retrofitFactory.getInstance().create(AdviceRepository::class.java)

    @Provides
    fun provideFamilyGroup(retrofitFactory: RetrofitFactory): FamilyGroupService =
        retrofitFactory.getInstance().create(FamilyGroupService::class.java)
}
