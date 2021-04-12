package com.houm.android.houmweather.dependency

import android.content.Context
import com.houm.android.houmweather.feature.dashboard.Dashboard
import com.houm.android.houmweather.feature.dashboard.DashboardModel
import com.houm.android.houmweather.repository.HoumRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DashboardModule {

    @Provides
    fun provideModel(
        @ApplicationContext context: Context,
        houmRepository: HoumRepositoryImpl
    ): Dashboard.Model = DashboardModel(context, houmRepository)

}