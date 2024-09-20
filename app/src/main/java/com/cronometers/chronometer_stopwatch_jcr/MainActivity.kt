package com.cronometers.chronometer_stopwatch_jcr

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tripChronometerManager: ChronometerManager
    private lateinit var waitingChronometerManager: ChronometerManager

    private var inTripMode = true // Flag to switch between trip and waiting chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.w("msg","getFormattedTime : " + ChronometerManager.getFormattedTime(36000))
        Log.w("msg","getFormattedTime : " + ChronometerManager.timeStringToSeconds("00:01:00"))

        // Initialize ChronometerManager for each chronometer
        tripChronometerManager = ChronometerManager(findViewById(R.id.tripChronometer))
        tripChronometerManager.setChronometerFormat(isTripTime=true)
        waitingChronometerManager = ChronometerManager(findViewById(R.id.waitingChronometer))
        waitingChronometerManager.setChronometerFormat(isTripTime=false)

        val startButton: Button = findViewById(R.id.startButton)
        val pauseButton: Button = findViewById(R.id.pauseButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val switchModeButton: Button = findViewById(R.id.switchModeButton)
        val startFromSpecificTimeButton: Button = findViewById(R.id.startFromSpecificTimeButton)

        // Start both chronometers when the start button is clicked
        startButton.setOnClickListener {
            startBothChronometers()
        }
        // Pause both chronometers when the start button is clicked
        pauseButton.setOnClickListener {
            pauseBothChronometers()
        }

        // Reset both chronometers
        resetButton.setOnClickListener {
            tripChronometerManager.resetChronometer()
            waitingChronometerManager.resetChronometer()
        }

        // Switch between Trip and Waiting modes
        switchModeButton.setOnClickListener {
            inTripMode = !inTripMode
            val mode = if (inTripMode) "Trip Mode" else "Waiting Mode"
            Toast.makeText(this, "Switched to $mode", Toast.LENGTH_SHORT).show()
        }

        // Start both chronometers from a specific given time (e.g., 5 minutes)
        startFromSpecificTimeButton.setOnClickListener {
            val specificTimeMillis: Long = 5 * 60 * 1000 // 5 minutes in milliseconds
            tripChronometerManager.startFromSpecificTime(specificTimeMillis)
            waitingChronometerManager.startFromSpecificTime(specificTimeMillis)
        }
    }

    // Start both chronometers
    private fun startBothChronometers() {
        if (!tripChronometerManager.isChronometerRunning() && !waitingChronometerManager.isChronometerRunning()) {
            tripChronometerManager.startChronometer()
            waitingChronometerManager.startChronometer()
        } else {
            // Optionally handle the case where one or both are already running
            Toast.makeText(this, "Both chronometers are already running", Toast.LENGTH_SHORT).show()
        }
    }

    // Pause both chronometers
    private fun pauseBothChronometers() {
        if (tripChronometerManager.isChronometerRunning() && waitingChronometerManager.isChronometerRunning()) {
            tripChronometerManager.pauseChronometer()
            waitingChronometerManager.pauseChronometer()
        } else {
            // Optionally handle the case where one or both are already Paused
            Toast.makeText(this, "Both chronometers are already Paused", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        tripChronometerManager.setSpecificTripTime(50000,true)
        waitingChronometerManager.setSpecificTripTime(10000,false)
    }
}
