# QuizKmpApp

A **multiplatform quiz app** built with **Kotlin Compose Multiplatform**, featuring offline caching, swipe gestures, streak tracking, and clean MVVM architecture.

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

### 1. QuizKmpApp Initialization
- Loads quiz data from **cache** or **remote server**.
- Initializes `QuizViewModel` on app start.
- Navigation based on state:
  - **SplashScreen** → **QuizScreen** → **ResultScreen**

### 2. Screens

#### **SplashScreen**
- Circular progress bar with "Loading quiz..." text.
- Shows until data is loaded (`QuizUiState.isLoading = true`).

#### **QuizScreen**
- Displays **question** and **multiple choice options**.
- **Progress bar** and **streak indicator**.
- **Swipe gestures** (left/right) for navigation.
- **Skip button**.
- **Auto-navigation** to the next question after selection.
- Observes `QuizUiState` for reactive UI updates.

#### **ResultScreen**
- Shows total **correct answers**, **streak**, and **max streak**.
- `onRestart` resets quiz state and questions.
- Triggered when `QuizUiState.isCompleted = true`.

---
