Noor Al-Hadith
Noor Al-Hadith is a small Android application developed as a Graduation Project.

The app aims to help users read selected Hadiths, learn about their narrators, and reinforce understanding through a simple and interactive quiz.

⸻
Project Overview

The application focuses on simplicity and clarity, making it suitable for learning and exploration.

It provides:

	•	A clean and friendly user interface

	•	A curated list of selected Hadiths

	•	Clear explanations suitable for beginners

	•	Narrator information for each Hadith

	•	A quiz feature to test understanding

	•	Proper loading and error states for a smooth user experience

⸻
Architecture

The project follows a clean and organized architecture with clear separation of responsibilities:
UI Layer

	•	Built using Jetpack Compose

	•	Handles UI rendering and user interaction

	•	Manages UI states (Loading / Success / Error)
Domain Layer

	•	Contains repository interfaces

	•	Handles business logic
Data Layer

	•	Local data (Hadith metadata, explanations, images)

	•	Remote data fetched from a public Hadith API

⸻
API

Hadith texts are retrieved from a public Hadith API:

	•	api.hadith.sutanlab.id

	•	No authentication required

	•	Used only to fetch the Hadith text (matn)

⸻
Key Features

    •  Splash Screen

    •  Main Screen

    •  Hadith List & Details Screen

    •  Quiz Screen

    •  Public API integration

    •  Proper state handling using ViewModel and StateFlow

    •  Dark Mode support (follows system theme automatically)

    • Quiz feature with audio feedback for correct and incorrect answers




⸻
Technologies Used

	•	Kotlin

	•	Jetpack Compose

	•	ViewModel & StateFlow

	•	Retrofit

	•	Clean Architecture principles
 