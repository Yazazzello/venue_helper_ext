# venue-helper sample project
 An app that shows 10 venues around a given place (e.g. Rotterdam) with a radius of 1000 meters. The App contains 2 screens, list and detail. For the list screen uses the following call to search the venues
 The UI should be very simple, a search field and a list with venues.
 The second screen will appear if a user clicks on a venue. This page contains more detailed information about the venue such as:
 - Photo’s
 - Title
 - Description
 - Contact information
 - Address
 - Rating

 Make the app to cache the data in the database to be able to working (partly) offline. The cached data needs to be updated once the connection is available.
## Used:

- Kotlin
- Sample Espresso, Instrumentation & JUnit tests.
- Gradle Kotlin
- Dependency versions managed via `buildSrc`.
- Kotlin Static Analysis via `ktlint` and `detekt`.
- Koin, Retrofit, Coroutines, Workmanager, MVVM

## Things I'd like to improve
- UI
- fix weird bug - have to tap twice on back button in order to dismiss details fragment first time (it's connected with SearchView dismissal)
- tests

## note
- Foursquare API doesn't always provide description field (at least for places I tested)
- I deliberately didn't fix warnings from ktlint and detekt