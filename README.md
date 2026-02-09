# 🎮 Games Explorer App

A modern Android application that allows users to browse video games by genre using the RAWG API. Built with Clean Architecture, MVVM pattern, and Jetpack Compose.

## 📱 Features

### Games List Screen
- Browse games by selected genre (Action, RPG, Strategy, etc.)
- Search games locally without making API calls
- Genre filter chips for easy navigation
- Pagination - automatically loads more games when scrolling
- Display game information:
  - Game name
  - Cover image
  - Rating with star icon
  - Metacritic score with color coding
  - Release date
  - Genres

### Game Details Screen
- Comprehensive game information:
  - Name and background image
  - Rating and Metacritic score
  - Release date and playtime
  - Genres
  - Platforms
  - Full description
  - Developers and Publishers
  - ESRB Rating

### State Management
- **Loading**: Circular progress indicator during API calls
- **Error**: User-friendly error messages
- **Empty**: Clear indication when no games match search/genre
- **Success**: Smooth display of game data


### Modules Structure

- **app**: Presentation layer (UI, ViewModels, Navigation, DI)
- **domain**: Business logic (Use cases, Domain models, Repository interfaces)
- **data**: Data handling (API, DTOs, Mappers, Repository implementation)
- **core**: Shared utilities (Result sealed class, Constants)


## 👨‍💻 Developed by : Mohamed Khaled
