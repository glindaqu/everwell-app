package ru.glindaquint.everwell.services.stepCounter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StepCounterRepository {
    internal const val PREFS_NAME = "step_counter_prefs"
    private const val KEY_CURRENT_STEPS = "current_steps"
    private const val KEY_LAST_DATE = "last_date"
    private const val KEY_TOTAL_STEPS = "total_steps"

    private val _currentSteps = MutableLiveData<Int>()
    private val _totalSteps = MutableLiveData<Int>()

    val currentSteps: LiveData<Int> = _currentSteps
    val totalSteps: LiveData<Int> = _totalSteps

    fun initialize(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        _currentSteps.value = prefs.getInt(KEY_CURRENT_STEPS, 0)
        _totalSteps.value = prefs.getInt(KEY_TOTAL_STEPS, 0)
        checkDateReset(context)
    }

    fun addSteps(
        count: Int,
        context: Context,
    ) {
        _currentSteps.value = (_currentSteps.value ?: 0) + count
        _totalSteps.value = (_totalSteps.value ?: 0) + count
        saveData(context)
    }

    internal fun checkDateReset(context: Context) {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val lastDate = prefs.getString(KEY_LAST_DATE, "")

        if (lastDate != currentDate) {
            if (!lastDate.isNullOrEmpty()) {
                saveDailyStats(context, lastDate, _currentSteps.value ?: 0)
            }

            _currentSteps.value = 0
            prefs.edit().putString(KEY_LAST_DATE, currentDate).apply()
            saveData(context)
        }
    }

    private fun saveData(context: Context) {
        context
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(KEY_CURRENT_STEPS, _currentSteps.value ?: 0)
            .putInt(KEY_TOTAL_STEPS, _totalSteps.value ?: 0)
            .apply()
    }

    private fun saveDailyStats(
        context: Context,
        date: String,
        steps: Int,
    ) {
        val historyPrefs = context.getSharedPreferences("step_history", Context.MODE_PRIVATE)
        historyPrefs.edit().putInt(date, steps).apply()
    }
}
