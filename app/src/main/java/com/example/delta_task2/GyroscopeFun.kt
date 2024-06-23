package com.example.delta_task2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorEvent

data class GyroscopeData(
    val x: Float,
    val y: Float,
    val z: Float
)

interface Gyroscope {
    fun startListening(listener: (GyroscopeData) -> Unit)
    fun stopListening()
}

// Gyroscope implementation
class AndroidGyroscope(context: Context) : Gyroscope {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private var listener: ((GyroscopeData) -> Unit)? = null
    private val sensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val data = GyroscopeData(
                        x = it.values[0],
                        y = it.values[1],
                        z = it.values[2]
                    )
                    listener?.invoke(data)
                }
            }
    }

    override fun startListening(listener: (GyroscopeData) -> Unit) {
        this.listener = listener
        sensorManager.registerListener(sensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun stopListening() {
        sensorManager.unregisterListener(sensorEventListener)
        listener = null
    }
}