package ru.glindaquint.everwell.services.stepCounter

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.activities.MainActivity
import kotlin.math.sqrt

class StepCounterService :
    Service(),
    SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var wakeLock: PowerManager.WakeLock
    private var lastAcceleration = 0f
    private var lastStepTime = 0L

    companion object {
        private const val CHANNEL_ID = "step_counter_channel"
        private const val NOTIFICATION_ID = 1
        private const val STEP_DELAY_MS = 300L
        private const val ACCELERATION_THRESHOLD = 10f

        fun start(context: Context) {
            val intent = Intent(context, StepCounterService::class.java)
            context.startForegroundService(intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context, StepCounterService::class.java)
            context.stopService(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForegroundService()
        initSensors()
        acquireWakeLock()
    }

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(
                CHANNEL_ID,
                "Шагомер",
                NotificationManager.IMPORTANCE_LOW,
            ).apply {
                description = "Отслеживание шагов в фоновом режиме"
            }

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    private fun startForegroundService() {
        val notification = buildNotification(0)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun buildNotification(steps: Int): Notification {
        val intent =
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            )

        return NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setContentTitle("Шагомер активен")
            .setContentText("Шагов сегодня: $steps")
            .setSmallIcon(R.drawable.sneakers)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun initSensors() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    private fun acquireWakeLock() {
        val powerManager = getSystemService(POWER_SERVICE) as PowerManager
        wakeLock =
            powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "StepCounter::WakeLock",
            )
        wakeLock.acquire(10 * 60 * 1000L)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta = acceleration - lastAcceleration
            lastAcceleration = acceleration

            if (delta > ACCELERATION_THRESHOLD && System.currentTimeMillis() - lastStepTime > STEP_DELAY_MS) {
                lastStepTime = System.currentTimeMillis()
                StepCounterRepository.addSteps(1, this)
                updateNotification()
            }
        }
    }

    private fun updateNotification() {
        val steps = StepCounterRepository.currentSteps.value ?: 0
        val notification = buildNotification(steps)
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int,
    ) {
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        if (::wakeLock.isInitialized && wakeLock.isHeld) {
            wakeLock.release()
        }
    }
}
