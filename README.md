# 📰 News App

A modern **Jetpack Compose-based** News App that fetches and displays the latest news using **Retrofit, Paging 3, and Hilt** for Dependency Injection.

## 🚀 Features
- 🌍 Browse the latest news articles
- 🔍 Search for specific topics
- 📜 Infinite scrolling with **Paging 3**
- 🎨 Beautiful UI with **Jetpack Compose**
- ⚡ Efficient networking with **Retrofit**
- 🗄️ Offline caching using **Room Database**
- 🏗️ Dependency Injection with **Hilt**

## 📸 Screenshots


## 🛠️ Tech Stack
- **Kotlin** - Programming Language
- **Jetpack Compose** - UI Toolkit
- **Retrofit** - Networking Library
- **Hilt** - Dependency Injection
- **Paging 3** - Infinite Scrolling
- **Room Database** - Local Storage
- **Coil** - Image Loading

## 🏗️ Installation & Setup
1. **Clone the repository**  
   ```sh
   git clone https://github.com/your-username/NewsApp.git
   cd NewsApp
   
2. **Get a News API Key**
  -  Sign up at NewsAPI.org and get your free API key.

3. **Paste your API Key**
-  Open local.properties and add:
   ```sh
   API_KEY=your_api_key_here
-  Open app/build.gradle and modify the buildConfigField inside defaultConfig
   ```sh
   buildConfigField("String", "API_KEY", "\"${API_KEY}\"")
4. **Run the app**
-  Open the project in **Android Studio**
-  Click Run ▶

   
