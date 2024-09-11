package com.cronometers.chronometer_stopwatch_jcr

import android.os.SystemClock
import android.widget.Chronometer

class ChronometerManager(private val chronometer: Chronometer) {

    private var pauseOffset: Long = 0
    private var isRunning = false

    // Start the chronometer
    fun startChronometer() {
        if (!isRunning) {
            chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
            chronometer.start()
            isRunning = true
        }
    }

    // Pause the chronometer
    fun pauseChronometer() {
        if (isRunning) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            isRunning = false
        }
    }

    // Reset the chronometer
    fun resetChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        chronometer.stop()
        chronometer.text = "00:00:00"  // Explicitly set the text to 00:00:00
        isRunning = false
    }

    // Start the chronometer from a specific time (in milliseconds)
    fun startFromSpecificTime(timeInMillis: Long) {
        chronometer.base = SystemClock.elapsedRealtime() - timeInMillis
        pauseOffset = 0
        chronometer.start()
        isRunning = true
    }   // Start the chronometer from a specific time (in milliseconds)

    // Check if the chronometer is running
    fun isChronometerRunning(): Boolean {
        return isRunning
    }

    // Optionally format the chronometer to HH:MM:SS
    fun setChronometerFormat() {
        chronometer.setOnChronometerTickListener { chrono ->
            val elapsedMillis = SystemClock.elapsedRealtime() - chrono.base
            chrono.text = getFormattedTime(elapsedMillis)
        }
    }

    // Optionally format the chronometer to HH:MM:SS
    fun setSpecificTripTime(timeInMillis: Long, isTripTime: Boolean) {
        pauseOffset = if (isTripTime) {
            timeInMillis
        } else {
            timeInMillis
        }
        chronometer.text = getFormattedTime(timeInMillis)
    }

    companion object {
        fun getFormattedTime(elapsedTime: Long): String {
            val seconds = (elapsedTime / 1000 % 60).toInt()
            val minutes = (elapsedTime / (60 * 1000) % 60).toInt()
            val hours = (elapsedTime / (60 * 60 * 1000)).toInt()
            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            return time
        }

        fun timeStringToSeconds(timeString: String): Int {
            // Split the time string into hours, minutes, and seconds
            val timeParts = timeString.split(":")

            // Parse the string parts into integers
            val hours = timeParts[0].toInt()
            val minutes = timeParts[1].toInt()
            val seconds = timeParts[2].toInt()

            // Convert the entire time to seconds
            return hours * 3600 + minutes * 60 + seconds
        }
    }
}
