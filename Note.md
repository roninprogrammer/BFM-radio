#BFM podcast App Documentation 


## Overview

The app is designed to display podcast titles, subtitles , guest details and published date (Converted from UNIX Time). The app also allows users to play audio streams for each podcast.

### The project uses:
- **Retrofit** for fetching data from the API
- **Jetpack Compose** for building the UI
- **Room** for caching podcast data locally
- **Coroutines** for handling background tasks


## Key Features
- Fetch podcast data from the API and display it in a list.
- Caching data using Room for offline access.
- Audio playback for podcasts.
- Display podcast details like title, subtitle, guest details, and published date.

## API Integration
The app retrieves data from an API that returns a list of podcasts with details such as:
- Title
- Subtitle
- Guest details
- Published date (in UNIX time format)
- Audio URL for playback
- Image URL for display

The data is fetched using Retrofit and displayed in the app. The response data is cached in Room for offline usage.

### Example API response:
```json
 {
  "title": "Ep10: Competitive Excel, That's A Sport?",
  "subtitle": "Andrew Grigolyunovich, Founder & CEO, Financial Modelling World Cup",
  "type": "podcast",
  "source": ".m3u8",
  "data": {
    "id": "0509f03d-be05-4fa9-9fca-b1fc00c3f781",
    "mp3_duration": "1778",
    "mp3file_size": "28496935",
    "pagetitle": "Ep10: Competitive Excel, That's A Sport?",
    "mp3": "https://traffic.omny.fm/d/clips/de62ff84-6498-49d0-a266-a9d50120c712/8d3d4942-d271-4d59-9aa1-ab09005b3136/0509f03d-be05-4fa9-9fca-b1fc00c3f781/audio.mp3",
    "content": "<p>In this episode of That's A Sport, we dive into the world of Competitive Excel, where spreadsheet&nbsp;enthusiasts showcase their creativity, speed, and problem-solving skills through nail-biting timed challenges of complex data problems - all using the world's best puzzle-solving tool.</p><p></p><p>Today, the Microsoft Excel World Championships is quickly becoming the next big thing in esports, bringing together the world's foremost Excel experts to battle it out head-to-head in spectator-friendly showdowns in packed arenas and watched by millions online. Andrew Grigolyunovich, founder and CEO of the Financial Modelling World Cup, takes us behind the scenes of the spreadsheet spectacle.</p><p></p><p>That's A Sport is a monthly series where we look to explore and discover some lesser known sport around the globe.&nbsp;</p><p><br>Photo credit: Robert McMillan/The Wall Street Journal</p>",
    "image": "https://bfmcms.s3.ap-southeast-1.amazonaws.com/websiteimages/bar-none/2024-10-01_ep10-competitive-excel-thats-a-sport/website_b3197506-f999-4ad1-ad2e-9b1aca766a76.png",
    "fbimage": "https://bfmcms.s3.ap-southeast-1.amazonaws.com/websiteimages/bar-none/2024-10-01_ep10-competitive-excel-thats-a-sport/og_b3197506-f999-4ad1-ad2e-9b1aca766a76.png",
    "alias": "https://www.bfm.my/ep10-competitive-excel-thats-a-sport",
    "slug": "ep10-competitive-excel-thats-a-sport",
    "interviewtime": "1727784011",
    "content_type": "podcast",
    "editedon": "1727787827",
    "publishedon": "1727758811",
    "parent": "24",
    "belt_name": "Night Shift",
    "hidedownload": "0",
    "hidesponsor": "0",
    "selected_highlight": "no",
    "show_name": "Bar None",
    "delisted": "no",
    "producers": "Daryl Ong, Julian Yap",
    "presenters": "Daryl Ong",
    "category": [
      "sports"
    ],
    "tags": [
      "competitive excel",
      "financial modelling",
      "financial modelling competitions",
      "spreadsheets",
      "financial modelling world cup",
      "microsoft excel world championships",
      "Diarmuid Early",
      "Andrew Ngai",
      "bar none",
      "that's a sport",
      "microsoft excel"
    ],
    "createdon": "1727783608",
    "guest_names": [
      "Andrew Grigolyunovich"
    ],
    "salutations": [
      "none"
    ],
    "guest_designations": [
      "Founder & CEO"
    ],
    "guest_organisations": [
      "Financial Modelling World Cup"
    ],
    "guests": [
      "Andrew Grigolyunovich, Founder & CEO, Financial Modelling World Cup"
    ]
  }
}
```

## DATABASE 

The Podcast data is stored locally using Room . This ensure that the app has fallback mechanism when the user is offline. 


- *PodcastEntity*: Represents the podcast object in the Room database.
- *PodcastDao*: Data Access Object that defines methods to interact with the database (e.g., insert, query, delete).
- *PodcastDatabase*: Main database class that provides an instance of the database and DAO.


## ViewModel and Repository

The PodcastViewModel is responsible for managing UI related data. it interact with with PodcastRepository, which abstracts the data sources(API and Room). 
The ViewModel retrieves cached data from Room First and the fetches fresh data from the API, updating the UI accordingly. 


## Jetpack Compose UI 

The app uses Jetpack Compose to build the UI , ensure the modern , reactive UI.

- *MainScreen*: Displays a list of podcasts, showing their title, subtitle, guest details, and published date.
- *Audio Playback*: The user can play podcasts directly from the list. The playback controls can be extended with additional functionalities like pause, resume, and stop.

## Formatting Dates 

Required provided in UNIX format and is converted to a readable format using the following functions: 

```kotlin
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatUnixTime(unixTime: Long): String {
val date = Date(unixTime * 1000L)
val format = SimpleDateFormat("d MMM yyyy, h:mm a", Locale.getDefault())
return format.format(date)
}
```
## Audio Playback

The app allows users to play podcasts through the audio player. You can extend the player with additional features like: 

- Pause nad resume 
- Seek forward and backward
- Display playback progress

## Future improvement 
- improving the offline mode with full feature caching
- Implementing media controls (pause, seek, resume) in the player
- Adding a notification for ongoing playback.

## How to run the App

1. *Clone the Repository*: Clone the project repository to local machine
2. *Install Dependencies*: Ensure that you have the necerssary dependencies set up in build.gradle.
3. *Run the App*: Open the project in Android Studio and run it on an emulator and a physical devices.

