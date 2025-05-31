package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.await
import ru.glindaquint.everwell.network.services.AdviceRepository
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import ru.glindaquint.everwell.utils.isSameDay
import java.util.Date
import javax.inject.Inject

@ActivityRetainedScoped
class AdviceService
    @Inject
    constructor(
        private val adviceRepository: AdviceRepository,
        private val preferencesManager: PreferencesManager,
    ) {
        private var _advice: String = ""

        suspend fun getAdvice(): String {
            if (shouldUpdate()) {
                fetchAdviceFromServer()
            } else {
                fetchAdviceFromDisk()
            }
            return _advice
        }

        private fun shouldUpdate(): Boolean {
            val lastUpdate =
                preferencesManager.getString(PreferencesKeys.ADVICE_LAST_UPD)?.toLongOrNull()
            return lastUpdate == null || !isSameDay(Date(), Date(lastUpdate))
        }

        private suspend fun fetchAdviceFromServer() {
            val response = adviceRepository.getRandomAdvice().await()
            if (response.error == null) {
                response.data?.takeIf { it.isNotBlank() }?.let { advice ->
                    _advice = advice
                    saveAdvice(advice)
                }
            }
        }

        private fun saveAdvice(advice: String) {
            if (advice.isNotBlank()) {
                preferencesManager.saveString(PreferencesKeys.LAST_ADVICE, advice)
                preferencesManager.saveString(PreferencesKeys.ADVICE_LAST_UPD, Date().time.toString())
            }
        }

        private fun fetchAdviceFromDisk(): String =
            preferencesManager
                .getString(PreferencesKeys.LAST_ADVICE, "")
                .orEmpty()
                .also { _advice = it }
    }
