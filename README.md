# Thermometer

This is an application for the Android platform that is my final project. With the help of this software, it's possible to see the temperature and humidity of the environment in real-time on a mobile device. For this project, I used the ESP32 development board and the DHT22 sensor, and the Arduino code for this project is available in this repository.


## Environment Variables

To run this project, you will need to create a Supabase account and take API URL and API KEY and the create Credentials.h file in `Thermometer/Arduino/Thermometer-Arduino/src` directory and add the following  variables to your Credentials.h file

1 - `String SSID ="your wifi ssid";`

2 - `String PASSWORD ="your wifi password";`

3 - `String API_URL ="your api url from supabase dashboard";`

4 - `String API_KEY ="you api key from supabase dashboard";`

5 - `String temperatureTable  ="a table name for temperature with "celcius" (float type) and "fahrenheit" (float type) columns";`

6 - `String humidityTable  ="a table name for humidity with "percent" (float type) column"`

<h2>Screenshots</h2>
<table>
<tr>
    <td><img src="https://github.com/Ramink78/Thermometer/blob/develop/Screenshots/Temperature%20Screen%20Large.png?raw=true" alt="drawing" width="250"/></td>
    <td><img src="https://github.com/Ramink78/Thermometer/blob/develop/Screenshots/Humidity%20Screen%20Large.png?raw=true alt="drawing" width="250"/></td>
  </tr>
</table>

## Features

- Offline-first: The app can be accessed even without an internet connection.
- Real-Time: Sync the temperature and humidity in real-time
- Temperature Alarm: You will receive a notification when the temperature reaches a certain value.
- Humidity Alarm: You will receive a notification when the humidity reaches a certain value.

## Built With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UIs.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.

    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [Navigation Components](https://developer.android.com/develop/ui/compose/navigation) - The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component's infrastructure and features.
    - [Room](https://developer.android.google.cn/jetpack/androidx/releases/room) - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

- [Dependency Injection](https://developer.android.com/training/dependency-injection)
    - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android application.
- [Supabase](https://supabase.com/docs/guides/database/overview) - Provides a full Postgres database for every project with Realtime functionality, database backups, extensions, and more.


 