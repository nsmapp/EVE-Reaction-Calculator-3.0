package be.nepravsky.sm.evereactioncalculator.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class MainDest : NavKey, BottomNavItem {

    @Serializable
    object Library : MainDest() {
        override val icon: ImageVector
            get() = Icons.Default.Menu
    }

    @Serializable
    object Reactions : MainDest() {
        override val icon: ImageVector
            get() = Icons.Default.PlayArrow
    }

    @Serializable
    object Settings : MainDest() {
        override val icon: ImageVector
            get() = Icons.Default.Settings
    }
}

interface BottomNavItem {
    val icon: ImageVector
}