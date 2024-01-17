##  Jetpack Compose MVVM-WeatherMan App
WeatherMan is an Android app that fetches Weather updates for currrent or any selected location with the new mordern UI tool Jetpack Compose. It uses openweathermap API to fetch weather data and Places API to fetch cities' geolocation. It has 4 screens:
 1. Fetches and displays weather data for a city for the day and also lists weather forecasts for the next 5 days in 3-hour interval.
 2. Search page - users can search for any city and display its weather data.
 3. Display weather data for search location.
 4. Favourite screen- contains a list of locations/cities that users have added to favourites- stored in the Room database and can be accessed offline.

## 📐✏️ Architecture
 The app is built with the Model-View-ViewModel (MVVM) architecture with Repository pattern, which separates the app's user interface, logic, and data:
 
 * Model: Represents the data and the business logic of the app. It's the actual data and data sources, like databases or network requests.
 * View: Represents the UI of the app. In your app, it would be the Jetpack Compose components that display the weather data to the user.
 * ViewModel: It holds the data that the View needs, exposing this data through StateFlow.
 * Repository: This is an additional layer between the ViewModel and the data source (in this case, the OpenWeatherMap API). The Repository centralizes the data fetching mechanism, so if in the future we decide to add another data source, such as a local database for offline caching, the ViewModel wouldn't need to know where the data is coming from. The Repository will handle data retrieval, whether it's from the API, local database, or other sources.

#🛠 Tech Stack and Libraries used:
  * MVVM Architecture (Model - ComposableView - ViewModel)
  * Repository pattern
  * Kotlin - Most of the Android community uses Kotlin as their preferred choice of language.
  * Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
  * Android KTX - Android KTX is a set of Kotlin extensions that are included with Android Jetpack and other Android libraries. KTX extensions provide concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.
  * AndroidX - The androidx namespace comprises the Android Jetpack libraries. It's a major improvement to the original Android Support Library, which is no longer maintained.
  * Lifecycle - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments. These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.
  * ViewModel - The ViewModel class is a business logic or screen-level state holder. It exposes state to the UI and encapsulates related business logic. Its principal advantage is that it caches state and persists it through configuration changes.
  * Dagger Hilt - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
  * Kotlin Coroutines - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously and it's the recommended way for asynchronous programming on Android.
  * Kotlin Flow - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
  * Retrofit - Retrofit is a REST client for Java/ Kotlin and Android by Square. Its a simple network library that is used for network transactions.
  * OkHttp - OkHttp is an HTTP client. It perseveres when the network is troublesome as it will silently recover from common connection problems.
  * GSON - JSON Parser, used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
  * Logging Interceptor - An OkHttp interceptor which logs HTTP request and response data.
  * Timber- A logger with a small, extensible API which provides utility on top of Android's normal Log class.
  * JsonToKotlin Model- an android studio plugin to convert JSON string to Kotlin model using GSON

![mvvm](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/eb70c54e-d9d8-4e5c-9e52-f2c890d03e2c)


📱 Screenshots

![Screenshot_20240117_075422](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/de5bcfb2-99fe-430f-94d2-2c40a74201e8)
![Screenshot_20240117_075319](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/b70ff6b5-e151-4f02-bfc5-af25748a6027)
![sanfra-sunny](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/abd433ee-b60b-4bea-ab87-87c0b8cf5484)
![portland](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/b4d71698-f33d-4f6a-8c70-516c4ee32e25)
![nair](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/18f52f27-2efe-4248-8661-a4bd1433119a)
![lusaka-favourite](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/eefe59f8-cc63-4966-af82-ae46e3d9fc90)
![favourite](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/9214192e-08ae-4d32-9c0c-246f82113456)


[demo.webm](https://github.com/eskeikim/MVVM-Weather-app/assets/30348600/f1d86c03-6fde-4267-b7dc-20635bf92168)
