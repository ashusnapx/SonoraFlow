# SonoraFlow 🎵

> **The Emotional, AI-Powered Local Music Experience for Android.**

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-purple?logo=kotlin) ![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-green?logo=android) ![Android](https://img.shields.io/badge/Platform-Android-green?logo=android) ![License](https://img.shields.io/badge/License-MIT-blue)

SonoraFlow is a next-generation Android music player designed to bring your local music library to life. Built with a "Privacy First, Emotion First" philosophy, it combines a stunning pixel-perfect UI with an on-device AI engine to rediscover your favorite tracks.

---

## 🏗️ Architected by [Ashutosh Kumar](https://github.com/ashusnapx)

This project was conceived, architected, and built by **Ashutosh Kumar** with a focus on modern Android engineering excellence and high-fidelity user experience.

---

## ✨ Features

- **🎨 Warm & Vibrant Design System**: A custom-built UI language that feels alive. Fluid animations, dynamic colors, and adaptive layouts (Phone, Foldable).
- **🧠 Local AI Engine**: An intelligent recommendation system that runs 100% on-device. No data leaves your phone. (See [`:core:ai`](./core/ai)).
- **🎧 Audiophile Playback**: Built on top of **Google Media3 (ExoPlayer)** for gapless, high-fidelity audio playback.
- **📱 Modern Widget**: Controls right on your home screen, built with **Jetpack Glance**.
- **🧩 Modular Architecture**: Highly scalable codebase using Clean Architecture + Multi-Module strategy.

## 🛠️ Technology Stack

SonoraFlow showcases the cutting edge of Modern Android Development (MAD):

### Core
- **Kotlin**: 100% Kotlin codebase.
- **Jetpack Compose**: Declarative UI toolkit (Material3 Design).
- **Coroutines & Flow**: Asynchronous programming and reactive streams.

### Architecture
- **Clean Architecture**: Separation of concerns (UI -> Domain -> Data).
- **Multi-Module**: Feature-based modularization (`:feature:home`, `:feature:player`, etc.) for faster builds and separation.
- **Hilt**: Dependency Injection.
- **Room**: Local database persistence for efficient library querying.

### Media & AI
- **Media3 / ExoPlayer**: Robust background audio service.
- **TensorFlow Lite**: Scaffolding for on-device ML models.
- **Coil**: Efficient image loading for album art.

## 📦 Project Structure

```text
SonoraFlow/
├── app/                # Application entry point (Navigation, DI root)
├── core/               # Shared foundational modules
│   ├── ai/             # Recommendation Engine & ML Logic
│   ├── audio/          # Media3 Player & Service implementation
│   ├── common/         # Extension functions & base classes
│   ├── data/           # Repositories & Data Sources (MediaStore)
│   ├── database/       # Room Database & DAOs
│   ├── model/          # Domain models (Track, Album, Artist)
│   └── ui/             # Design System (Theme, Type, Color, Shape)
└── feature/            # Feature-specific modules
    ├── home/           # Home screen with Recommendations
    ├── library/        # Library listing (Grid/List)
    ├── player/         # Main Player UI, MiniPlayer, & Widget
    └── settings/       # App preferences
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer.
- JDK 17.

### Installation
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/ashutosh-kumar/SonoraFlow.git
    cd SonoraFlow
    ```
2.  **Open in Android Studio**:
    Select `Open` and navigate to the project directory.
3.  **Sync Gradle**:
    Allow Gradle to download dependencies.
4.  **Run**:
    Select the `app` configuration and run on an Emulator or Physical Device (API 26+).

## 🔮 Roadmap

We have an exciting year ahead! Check out our [12-Month Product Roadmap](product_roadmap.md) for details on upcoming features like:
- **Phase 2**: Neural Audio Upscaling & Smart Playlists.
- **Phase 3**: Social "Sonic Identity" Cards.
- **Phase 4**: WearOS Companion App.

## 🤝 Contribution

Contributions are welcome! Please read our [Contribution Guidelines](contribution_guide.md) before submitting a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
*Built with ❤️ and ☕ by Ashutosh Kumar.*
