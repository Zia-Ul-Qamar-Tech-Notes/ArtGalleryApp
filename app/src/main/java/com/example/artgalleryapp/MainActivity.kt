package com.example.artgalleryapp

//  App Author : Mirza Zia Ul Qamar
//  Author Roll# : 20021519-040
//  Author Class : BS-CS(C)-6th Semester
//  App Name : ArtGalleryApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgalleryapp.ui.theme.ArtGalleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryApp()
                }
            }
        }
    }
}

// Data Class

data class Artwork(
    val id: Int,
    val title: String,
    val occupation: String,
    val year: String,
    val imageResource: Int
)

//  Creating list of artworks using the above Artwork data class

val artworkList = listOf(
    Artwork(
        id = 0,
        title = "Mirza Zia Ul Qamar",
        occupation = "Web Developer",
        year = "2021",
        imageResource = R.drawable.zia
    ),
    Artwork(
        id = 1,
        title = "Simon",
        occupation = "Programmer",
        year = "2015",
        imageResource = R.drawable.simon
    ),
    Artwork(
        id = 2,
        title = "Gerard",
        occupation = "Java Developer",
        year = "2017",
        imageResource = R.drawable.gerard
    )
)

@Composable
fun ArtGalleryApp() {
    val currentIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentArtwork = artworkList[currentIndex.value]

        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currentArtwork.title)
            Text(text = "${currentArtwork.occupation}")
            Text(text = "Starting Year: ${currentArtwork.year}")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (currentIndex.value > 0) {
                        currentIndex.value -= 1
                    }
                },
                enabled = currentIndex.value > 0
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (currentIndex.value < artworkList.lastIndex) {
                        currentIndex.value += 1
                    }
                },
                enabled = currentIndex.value < artworkList.lastIndex
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryAppTheme {
        ArtGalleryApp()
    }
}