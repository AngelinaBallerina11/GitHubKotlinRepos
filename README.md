# Trending Kotlin GitHub repositories

This is a DEMO app to display a short list of the most popular Kotlin repositories in GitHub. The infinite scrolling is not implemented because of the demo nature of the app. Click on each list item opens a detail page.

### Tech stack
* Kotlin
* coroutines and Flow
* Retrofit & Moshi
* Koin
* Picasso
* MVVM and Clean architecture
* Jetpack Navigation Component
* Kotest, ArchUnit and JUnit 5 for unit tests
* CI GitHub Actions

### Architecture
The app follows Clean Architecture principles and the presentation layer is done in the MVVM pattern.
The layers are described below:

| Layer name | Description |
| ------ | ------ |
| Infrastructure | Fetch data from the remote API or observe the local DB. Data in DTO objects |
| Data | Data caching strategy and transformation |
| Domain | Business Logic and UseCases |
| Model | Business entities |
| Presentation | ViewModels |
| System | Android dependencies with Activity and Fragment |
| DI | Dependency Injection (has to see all levels) |

The layers are defined by separate packages and their integrity is checked by the [ArchUnit](https://www.archunit.org/) testing library.
