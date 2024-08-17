package be.nepravsky.sm.database.network


import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class KtorClient{

    companion object{
        const val URL_EVE_ESI = "https://esi.evetech.net/"
    }

    val client = HttpClient(Android){

        install(ContentNegotiation){
            json(
                Json {
                    isLenient = true
                    useAlternativeNames = true
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                }
            )
        }

        install(HttpTimeout){
            requestTimeoutMillis = 20000
            connectTimeoutMillis = 20000
        }

        install(Logging){
            logger = object: Logger{
                override fun log(message: String) {
                    Log.d("okhttp: ", message)
                }
            }
            level = LogLevel.BODY
        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            url(URL_EVE_ESI)
        }
    }
}