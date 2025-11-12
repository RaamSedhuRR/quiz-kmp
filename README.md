# 🧠 QuizKmpApp

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![KMM](https://img.shields.io/badge/KMM-Enabled-FF6F00?logo=android&logoColor=white)](https://kotlinlang.org/lp/mobile/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS-lightgrey)](#)

---

A **multiplatform quiz app** built with **Kotlin Compose Multiplatform**, featuring offline caching, swipe gestures, streak tracking, and a clean MVVM architecture.

---

## 🏗 Tech Stack

- **Frontend:** Kotlin Compose Multiplatform (Android, iOS)
- **Networking:** Ktor
- **Local Storage:** SQLDelight
- **Dependency Injection:** Koin
- **State Management:** StateFlow
- **Architecture:** Clean MVVM + Repository Pattern

---

## 🚀 Features

### ⚙️ App Initialization
- Loads quiz data from **cache** or **remote server**.
- Initializes `QuizViewModel` on app start.
- Navigation based on app state:
  - **SplashScreen** → **QuizScreen** → **ResultScreen**

---

### 🌀 SplashScreen
- Circular progress bar with **“Loading quiz...”** text.
- Visible while data is being loaded (`QuizUiState.isLoading = true`).

---

### 🧩 QuizScreen
- Displays **question** and **multiple choice options**.
- **Progress bar** and **streak indicator** for tracking.
- **Swipe gestures** (left/right) for navigation.
- **Skip button** to move to the next question.
- **Auto-navigation** after selecting an answer.
- Observes `QuizUiState` for reactive UI updates.

---

### 🏁 ResultScreen
- Displays total **correct answers**, **current streak**, and **max streak**.
- `onRestart` resets quiz state and reloads questions.
- Triggered when `QuizUiState.isCompleted = true`.

---

## 🎥 Demo

📱 **Watch the Demo (Android & iOS):**  
[▶️ Google Drive Demo Link](https://drive.google.com/drive/folders/10FyBqBShRTAUO-RBRjatadp_YyASEDUh?usp=sharing
)

---
