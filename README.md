# MaximusTask — Android Cat Facts App

A lightweight Android application built with Kotlin that demonstrates REST API integration, MVVM-style separation of concerns, reactive data handling, and lifecycle-aware UI updates.

The application retrieves random cat facts from a remote API and presents the returned fact together with its length in a simple Android interface.

## Features

- Fetch random cat facts from a REST API
- Display the returned fact and its character length
- Pull-to-refresh support
- Loading state while retrieving remote data
- Splash screen flow
- Lifecycle-aware UI updates
- Structured networking and repository layers
- Reactive API handling with RxJava

## Tech Stack

- Kotlin
- Android SDK
- AndroidX
- XML Layouts
- ViewBinding
- ViewModel
- LiveData
- Retrofit
- OkHttp
- Gson
- RxJava
- SwipeRefreshLayout
- Gradle

## Architecture

The project follows an MVVM-style architecture with responsibilities separated into dedicated layers.

```text
com.offneo.maximustask
│
├── networks
│   ├── ApiClient
│   ├── ApiInterface
│   └── ApiUri
│
├── repository
│   └── Data and API repository layer
│
├── responseModel
│   └── API response models
│
├── screens
│   ├── SplashActivity
│   └── MainActivity
│
├── viewmodel
│   └── UI state and data interaction
│
└── adapter
    └── UI adapter components
