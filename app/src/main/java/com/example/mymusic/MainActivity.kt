package com.example.mymusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymusic.ui.theme.MYMUSICTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { //Andile Kundayi ST10487104
            var currentScreen = remember { mutableStateOf("main") }
            var theSongName = remember { mutableStateOf("") }
            var theArtistsName = remember { mutableStateOf("") }
            var theRating = remember { mutableStateOf("") }
            var userComment = remember { mutableStateOf("") }
            var showError = remember { mutableStateOf(false) }
            val  playListManager = remember { mutableStateListOf<playListManager>() }

            Scaffold {padding ->
                Column(modifier = Modifier.padding(16.dp)) {
                    if (currentScreen.value == "main") {
                        Text(
                            text = "MYMUSIC ST10487104",
                            style = MaterialTheme.typography.titleLarge
                        )


                        OutlinedTextField(
                            value = theSongName.value ,
                            onValueChange = {theSongName.value = it},
                            label = { Text("Enter song name") }
                        )
                        OutlinedTextField(
                            value = theArtistsName.value ,
                            onValueChange = {theArtistsName.value = it},
                            label = { Text("Enter artist name") }
                        )
                        OutlinedTextField(
                            value = theRating.value ,
                            onValueChange = {theRating.value = it},
                            label = { Text("Rate the song 1-5") }
                        )
                        OutlinedTextField(
                            value = userComment.value ,
                            onValueChange = {userComment.value = it},
                            label = { Text("Comment on the song") }
                        )

                        if (showError.value) {
                            Text(
                                text = "Error: Please fill in all fields correctly!",
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp
                            )

                        }

                        Button(onClick = {
                            if (theSongName.value.isBlank() || theArtistsName.value.isBlank() || theRating.value.isBlank() || userComment.value.toIntOrNull() == null) {
                                showError.value = true
                            } else {
                                playListManager.add(playListManager(theSongName.value, theArtistsName.value, theRating.value.toInt(), userComment.value))
                                showError.value = false
                                theSongName.value = "" // Reset fields after successful entry
                                theArtistsName.value = ""
                                theRating.value = ""
                                userComment.value = ""
                            }
                        }) {
                            Text("ADD TO PLAYLIST")
                        }

                        Button(onClick = { currentScreen.value = "packing_list" }) {
                            Text("VIEW PLAYLIST")
                        }

                        Button(onClick = { finish() }) {
                            Text("EXIT APP")
                        }


        }
