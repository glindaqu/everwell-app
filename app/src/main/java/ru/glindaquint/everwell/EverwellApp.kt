package ru.glindaquint.everwell

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.glindaquint.everwell.services.stepCounter.StepCounterRepository
import ru.glindaquint.everwell.services.stepCounter.StepCounterWorker

@HiltAndroidApp
class EverwellApp : Application() {
    override fun onCreate() {
        super.onCreate()
        StepCounterRepository.initialize(this)
        StepCounterWorker.scheduleDailyReset(this)
    }
}
