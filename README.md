# Chronometer Manager

This Kotlin-based Android project implements a **Chronometer Manager** that allows the user to control multiple chronometers (such as trip and waiting timers) with a common button. The manager provides functionality to start, pause, reset, and start from a specific time (in `hh:mm:ss` format). The chronometers can also handle a maximum time limit and display a message when that limit is reached.

The project demonstrates how to use background services in Android to manage chronometers, ensuring timers keep running even when the app is minimized or closed.

---

## Features
- Start, pause, and reset chronometers.
- Manage multiple chronometers (trip and waiting time).
- Set chronometers to a specific time and format them as `hh:mm:ss`.
- Handle timers stopping after reaching the maximum value of `99:59:59`.

---

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/chiragjasani/chronometer_stopwatch.git
    ```

2. Open the project in Android Studio.

3. Build and run the app on your Android device or emulator.

---

## Example Usage

```kotlin
val tripChronometer = findViewById<Chronometer>(R.id.tripChronometer)
val chronometerManager = ChronometerManager(tripChronometer)

chronometerManager.startChronometer() // Start the chronometer
chronometerManager.pauseChronometer() // Pause the chronometer
chronometerManager.resetChronometer() // Reset the chronometer
chronometerManager.startFromSpecificTime(300000) // Start from 5 minutes



