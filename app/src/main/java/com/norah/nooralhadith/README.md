Noor Al-Hadith
Noor Al-Hadith is a small Android application built as a graduation project.

The app helps users read selected Hadith, learn about their narrators, and test their knowledge through a simple quiz.

⸻
Project Overview

The application provides:

	•	A clean and simple UI suitable for learning

	•	Selected Hadiths with explanations

	•	Narrator information

	•	A quiz feature to test understanding

	•	Loading and error states for better user experience

⸻
Architecture

The project follows a clean and simple architecture with clear separation of concerns:

	•	UI Layer

	•	Jetpack Compose screens

	•	UI state handling (Loading / Success / Error)

	•	Domain Layer

	•	Repository interfaces

	•	Business logic

	•	Data Layer

	•	Local data (Hadith metadata, explanations, images)

	•	Remote data using a public Hadith API

⸻
API

Hadith texts are fetched from a public Hadith API:

	•	api.hadith.sutanlab.id

	•	No authentication required

	•	Used only to retrieve Hadith text (matn)

⸻
Key Features

	•	Splash Screen

	•	Main Screen

	•	Hadith Details Screen

	•	Quiz Screen

	•	Public API integration

	•	Proper state handling using ViewModel and StateFlow

⸻
Technologies Used

	•	Kotlin

	•	Jetpack Compose

	•	ViewModel & StateFlow

	•	Retrofit

	•	Clean Architecture principles
 