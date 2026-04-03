package com.snackbar.customsnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.snackbar.snackswipe.AnimationConfig
import com.snackbar.snackswipe.SnackBehavior
import com.snackbar.snackswipe.SnackPolicy
import com.snackbar.customsnackbar.ui.theme.CustomSnackBarTheme
import com.snackbar.snackswipe.SnackSwipeBox
import com.snackbar.snackswipe.SwipeConfig
import com.snackbar.snackswipe.SwipeDirection
import com.snackbar.snackswipe.showSnackSwipe

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomSnackBarTheme {
                SnackSwipeBox { snackbarController ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black
                            ),
                            onClick = {
                                snackbarController.showSnackSwipe(
                                    durationMillis = 2000,
                                    behavior = SnackBehavior(
                                        policy = SnackPolicy.Replace
                                    ),
                                    messageText = {
                                        Text(
                                            text = "Success snackbar (swipe: Up/Left/Right)",
                                            color = Color.White
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = Color.Green
                                        )
                                    },
                                    customAction = {
                                        Text(
                                            text = "Action",
                                            modifier = Modifier.clickable {},
                                            color = Color.White
                                        )
                                    },
                                    dismissAction = {
                                        IconButton(onClick = { snackbarController.close() }) {
                                            Icon(
                                                Icons.Default.Close,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }
                                    }
                                )
                            }
                        ) {
                            Text(
                                text = "Show Success Snackbar",
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF7B1F1F)
                            ),
                            onClick = {
                                snackbarController.showSnackSwipe(
                                    durationMillis = 3500,
                                    backgroundColor = Color(0xFF7B1F1F),
                                    behavior = SnackBehavior(
                                        policy = SnackPolicy.Replace,
                                        swipe = SwipeConfig(
                                            allowedDirections = setOf(
                                                SwipeDirection.Left,
                                                SwipeDirection.Right
                                            )
                                        ),
                                        animation = AnimationConfig()
                                    ),
                                    messageText = {
                                        Text(
                                            text = "Error snackbar (swipe only Left/Right)",
                                            color = Color.White
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            Icons.Default.Info,
                                            contentDescription = null,
                                            tint = Color(0xFFFFC1C1)
                                        )
                                    },
                                    dismissAction = {
                                        IconButton(onClick = { snackbarController.close() }) {
                                            Icon(
                                                Icons.Default.Close,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }
                                    }
                                )
                            }
                        ) {
                            Text(
                                text = "Show Error Snackbar",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
