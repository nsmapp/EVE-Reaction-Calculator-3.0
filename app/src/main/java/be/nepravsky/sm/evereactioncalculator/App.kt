package be.nepravsky.sm.evereactioncalculator

import android.app.Application
import androidx.compose.runtime.Composable
import be.nepravsky.sm.uikit.theme.AppTheme
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger


class App: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        KoinDiHolder.getInstance(this@App)
    }

    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.10)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(5 * 1024 * 1024)
                    .build()
            }
            .logger(DebugLogger())
            .respectCacheHeaders(false)
            .build()

}
