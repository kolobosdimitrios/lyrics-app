# LyricsApp

LyricsApp is an Android application for managing song lyrics projects.
The UI is built entirely with Jetpack Compose and dependency injection is handled with Hilt.

## Prerequisites

- **Android SDK:** compileSdkVersion 34, minSdkVersion 29, targetSdkVersion 34
- **Kotlin:** 2.0.0
- **Android Gradle Plugin:** 8.7.2
- **JDK:** 11 (used for Kotlin `jvmTarget`)

Ensure that you have a recent version of Android Studio with these SDK levels installed.

## Build Instructions

Use the included Gradle wrapper to build the project:

```bash
./gradlew build
```

This will download dependencies and compile the app. You can also assemble a debug APK with:

```bash
./gradlew assembleDebug
```

## Running the App

To install the app on a connected device or emulator, run:

```bash
./gradlew installDebug
```

Alternatively, open the project in Android Studio and run the **app** configuration.

## Architecture Notes

- **Jetpack Compose** is used for all UI components. `HomeActivity` sets the `Navigation()` composable via `setContent` and Compose themes are defined in `presentation/theme/`.
- **Hilt** is used for dependency injection. `LyricsApplication` is annotated with `@HiltAndroidApp`, and modules under `di/` provide repositories, databases, and view models.

These libraries simplify state management and navigation in the app.

## Latest Updates

Recent commits introduced several usability improvements:

- Swipe to delete bars in a project.
- Delete individual lines from a bar.
- Clear bar contents with a dedicated button.
- Empty bar containers appear immediately when creating a new bar.

## Running Tests

Execute the unit test suite with:

```bash
./gradlew test
```

This runs all repository, mapper and use case tests under `app/src/test`.
