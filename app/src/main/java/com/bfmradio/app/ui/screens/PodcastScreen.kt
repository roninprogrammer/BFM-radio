package com.bfmradio.app.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bfmradio.app.R
import com.bfmradio.app.data.model.Podcast
import com.bfmradio.app.ui.viewmodels.PodcastViewModel
import com.bfmradio.app.utils.Utils
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bfmradio.app.utils.Utils.formatUnixTime

@SuppressLint("RememberReturnType")
@Composable
fun LiveStreamItem(title: String, isLiveStream: Boolean, onPlayClick: () -> Unit) {
    val backgroundColor = if (isLiveStream) Color(0xFF000000)
    else Color(0xFFE5E5E5)

    val textColor = if (isLiveStream) Color.White else Color.Black
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onPlayClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val alphaState = remember { Animatable(1f) }

            LaunchedEffect(isLiveStream) {
                if (isLiveStream) {
                    alphaState.animateTo(
                        targetValue = 0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = 1000,
                                easing = FastOutSlowInEasing
                            ),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                } else {
                    alphaState.snapTo(1f)
                }
            }
            if (isLiveStream) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Red, shape = CircleShape)
                        .graphicsLayer(alpha = alphaState.value)
                )
            }

            Text(
                text = title, fontFamily = FontFamily(
                    Font(R.font.roboto_regular)
                ), style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.graphicsLayer(alpha = alphaState.value),
                color = textColor
            )
            StreamIt(isPlaying = isLiveStream, onClick = onPlayClick)
        }
    }
}

@Composable
fun StreamIt(isPlaying: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(if (isPlaying) R.drawable.ic_pause_white else R.drawable.ic_play),
            contentDescription = if (isPlaying) "Pause" else "Play",
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PodcastItem(podcast: Podcast, isPodCastPlay: Boolean, playbackState: Map<Any, Any>, isPlaying: Boolean, onClick: () -> Unit) {
    val bfmFontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Medium)
    )
    val backgroundColor = Color(0xFFE5E5E5)
    Text(
        text = podcast.title,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = bfmFontFamily
        ),
        modifier = Modifier
            .padding(start = 5.dp, top = 8.dp, end = 5.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(podcast.data?.image)
                .crossfade(true)
                .build(),
            placeholder = rememberImagePainter(
                data = "https://i.sstatic.net/y9DpT.jpg",
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = podcast.data?.guests?.joinToString(", ") ?: Utils.placeHolderGuestDetails(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = podcast.data?.interviewtime?.toLongOrNull()?.let {
                    formatUnixTime(it)
                } ?: Utils.placeHolderTimeStamp(),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            )
        }

        CastIt(
            isPlaying = isPlaying,
            isPodCastPlay = isPodCastPlay,
            onClick = {
                onClick()
            }
        )
    }
}

@Composable
fun CastIt(isPlaying: Boolean, isPodCastPlay: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(if (isPlaying || isPodCastPlay) R.drawable.ic_pause_black else R.drawable.ic_play),
            contentDescription = if (isPlaying || isPodCastPlay) "Pause" else "Play",
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastScreen(viewModel: PodcastViewModel) {
    val podcastList by viewModel.podcasts.collectAsState(initial = emptyList())
    var isLiveStream by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var currentlyPlayingId by rememberSaveable { mutableStateOf<String?>(null) }


    val bfmFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Utils.getApplicationTitle(),
                        fontFamily = bfmFontFamily,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFF7F7F7),
                    titleContentColor = Color.Black
                )
            )
            Divider(
                color = Color.Gray,
                thickness = 2.dp
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            items(podcastList) { podcast ->
                when (podcast.type) {
                    Utils.livestream() -> {
                        LiveStreamItem(
                            title = podcast.title,
                            isLiveStream = isLiveStream,
                            onPlayClick = {
                                isLoading = true
                                currentlyPlayingId?.let {
                                    podcastList.find { p -> p.data?.id == it }?.data?.mp3?.let { mp3 ->
                                        viewModel.pauseAudio(mp3)
                                    }
                                }
                                currentlyPlayingId = null


                                isLiveStream = !isLiveStream
                                isLoading = if (isLiveStream) {
                                    viewModel.playAudio(podcast.source)
                                    false
                                } else {
                                    viewModel.pauseAudio(podcast.source)
                                    false
                                }
                            }
                        )
                    }

                    Utils.podcast() -> {
                        PodcastItem(
                            podcast = podcast,
                            isPlaying = currentlyPlayingId == podcast.data?.id,
                            isPodCastPlay = currentlyPlayingId == podcast.data?.id,
                            playbackState = emptyMap(),
                            onClick = {
                                if (isLiveStream) {
                                    isLiveStream = false
                                    podcastList.find { it.type == Utils.livestream() }?.source?.let {
                                        viewModel.pauseAudio(it)
                                    }
                                }

                                if (currentlyPlayingId == podcast.data?.id) {
                                    podcast.data?.mp3?.let { viewModel.pauseAudio(it) }
                                    currentlyPlayingId = null
                                } else {
                                    currentlyPlayingId?.let { playingId ->
                                        podcastList.find { it.data?.id == playingId }?.data?.mp3?.let { mp3 ->
                                            viewModel.pauseAudio(mp3)
                                        }
                                    }
                                    podcast.data?.mp3?.let {
                                        viewModel.playAudio(it)
                                        currentlyPlayingId = podcast.data.id
                                    }
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }
    }
}


