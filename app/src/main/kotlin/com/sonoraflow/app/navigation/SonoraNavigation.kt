package com.sonoraflow.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sonoraflow.feature.library.LibraryScreen
import com.sonoraflow.feature.home.HomeScreen
import com.sonoraflow.feature.player.MiniPlayer
import com.sonoraflow.feature.player.PlayerScreen
import com.sonoraflow.feature.player.PlayerViewModel

@Composable
fun SonoraNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Shared PlayerViewModel
    val playerViewModel: PlayerViewModel = hiltViewModel()
    val playerState by playerViewModel.playerState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "home", // Changed to Home
            modifier = Modifier.fillMaxSize()
                .padding(bottom = if (currentRoute != "player" && playerState.currentMediaItem != null) 72.dp else 0.dp)
        ) {
             composable("home") {
                HomeScreen(
                    onNavigateToPlayer = {
                        navController.navigate("player")
                    }
                )
            }
            composable("library") {
                LibraryScreen(
                    onNavigateToPlayer = {
                        navController.navigate("player")
                    }
                )
            }
            composable("player") {
                PlayerScreen()
            }
        }

        // Mini Player
        if (currentRoute != "player" && playerState.currentMediaItem != null) {
            MiniPlayer(
                playerState = playerState,
                onPlayPauseClick = playerViewModel::onPlayPauseClick,
                onClick = { navController.navigate("player") },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
