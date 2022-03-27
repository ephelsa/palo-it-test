package com.github.ephelsa.credijustotest.datasource.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.statement.request
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

/**
 * Configurations for Ktor stuff.
 */
object KtorConfig {
    private const val JSON_PLACEHOLDER_HOST = "jsonplaceholder.typicode.com"

    /**
     * [Client][HttpClient] for android.
     *
     * Contains a base URL for requests to [http//jsonplaceholder.typicode.com],
     * json serialization (and setup) and a basic response observer (aka dummy response log).
     */
    fun client(engine: HttpClientEngine = Android.create()) = HttpClient(engine) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = JSON_PLACEHOLDER_HOST
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }

        ResponseObserver { response ->
            println(
                """
                    -> Status: ${response.status.value}
                    -> Headers: ${response.headers}
                    -> Request: 
                        ->> URL: ${response.request.url}
                        ->> Headers: ${response.request.headers}
                        ->> Body: ${response.request.content}
                """.trimIndent()
            )
        }
    }
}