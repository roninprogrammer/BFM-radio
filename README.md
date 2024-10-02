# BFM-Radio

This repository contains the code for a mobile application created as part of the second phase of the interview process with BFM. The app displays podcasts and live stream data fetched from a JSON endpoint and allows users to play audio streams.

## Task Overview

- **Start date**: 3 October 2024
- **End date**: 9 October 2024
- **Submission email**: techjobs@bfm.my

### Requirements

The app must:
1. Display the following details from the podcast endpoint:
   - **First Headline**: Podcast title
   - **Second Headline**: Podcast subtitle
   - **Subtitle**: Provided in the API
   - **Guest Details**: Provided in the API
   - **Published Date**: Displayed in the format "5 Jan 2024, 10:00am" (converted from UNIX time format in the `interviewtime` field)
   - **Podcast Image**: Display the corresponding images.
2. Include an audio player that plays the podcast streams.

### Optional Enhancements

- Additional styling for better user experience.
- Extra player functions such as pause, seek, or volume control.

## JSON Endpoint

The app fetches podcast and live stream data from the following endpoint:

https://bfmcms.s3.ap-southeast-1.amazonaws.com/api/tests/endpoint.json


## Features

- **Podcast Listing**: Displays a list of podcasts with titles, subtitles, guest details, published dates, and images.
- **Audio Playback**: Plays the audio for each podcast.
- **Date Formatting**: UNIX timestamps are converted to a user-friendly date format.

## Installation

To run this app locally:

1. Clone the repository:
   ```bash
   git clone <repository-url>

2. Open the project in android Studio
3. Build the project to install the necessary dependencies.
4. Run the app on the Android device or emulator.

## Technologies Used 

Kotlin/JetCompose : For App Development
Retrofit : To handle API Request
Room/SQLite : For Local Data Storage
Glide : Caching the Image loading 
ExoPlayer: To Handle audio playback.

## Design

The app follows the design provided by BFM, which includes the podcast title, subtitle, guest details, published date, and podcast image.

## License

This project is for interview purposes only and is not intended for public use.
