package com.sonoraflow.feature.player.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.sonoraflow.core.audio.player.AudioPlayer
import com.sonoraflow.core.ui.theme.SonoraFlowTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import com.sonoraflow.feature.player.R
// Assuming we have drawables. For now we might need to use system ones or generated ones.
// Since we don't have R.drawable yet, I'll use standard android ones or text buttons for now.

class PlayerWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // In a real app we'd observe data here. 
        // For simplified MVP, we'll just show static UI that *would* be updated via WorkManager or Broadcasts.
        // Connecting Glance to Flow<PlayerState> requires a bit of boilerplate (GlanceStateDefinition).
        // For this step, I'll create the Layout.
        
        provideContent {
             // We can theme this
             WidgetContent()
        }
    }

    @Composable
    private fun WidgetContent() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(0xFFFFFBFF)) // Surface color
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SonoraFlow",
                style = TextStyle(color = Color(0xFFA63D13)) // Primary
            )
            Row(
                modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prev
                ButtonText(text = "|<", onClick = actionRunCallback<PrevAction>())
                Spacer(GlanceModifier.width(16.dp))
                // Play/Pause
                ButtonText(text = "Play/Pause", onClick = actionRunCallback<PlayPauseAction>())
                Spacer(GlanceModifier.width(16.dp))
                // Next
                ButtonText(text = ">|", onClick = actionRunCallback<NextAction>())
            }
        }
    }
    
    @Composable 
    fun ButtonText(text: String, onClick: androidx.glance.action.Action) {
        // Using Text as button placeholder since we don't have vector drawables set up in res/drawable yet
        Text(
            text = text,
            modifier = GlanceModifier.clickable(onClick).padding(8.dp),
            style = TextStyle(color = Color.Black)
        )
    }
}

class PlayerWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = PlayerWidget()
}

// Actions
class PlayPauseAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        val audioPlayer = getAudioPlayer(context)

        // Ideally we check state, but direct toggle is simplest for "fire and forget" intent
        // However, we need to know if playing. 
        // AudioPlayer exposes a Flow. We can't easily peek it synchronously without caching.
        // We'll just assume Play for now or toggle if we could.
        // Let's just call a safe toggle method if it existed, or just 'play' for now.
        // Actually, let's just log or no-op since we can't observe easily in a stateless callback without repo.
        // Real implementation involves updating the Widget State via a Service or WorkManager when playback changes.
        
        // For this MVP step, we'll assume integration is "Started".
    }
}

class NextAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        getAudioPlayer(context).skipToNext()
    }
}

class PrevAction : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        getAudioPlayer(context).skipToPrevious()
    }
}

// Hilt Entry Point to get dependencies in classes not supported by Hilt directly (like ActionCallback)
@EntryPoint
@InstallIn(SingletonComponent::class)
interface AudioPlayerEntryPoint {
    fun getAudioPlayer(): AudioPlayer
}

fun getAudioPlayer(context: Context): AudioPlayer {
    val appContext = context.applicationContext ?: throw IllegalStateException()
    val entryPoint = EntryPointAccessors.fromApplication(
        appContext,
        AudioPlayerEntryPoint::class.java
    )
    return entryPoint.getAudioPlayer()
}
