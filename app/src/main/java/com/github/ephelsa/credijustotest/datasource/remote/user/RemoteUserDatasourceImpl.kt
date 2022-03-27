package com.github.ephelsa.credijustotest.datasource.remote.user

import com.github.ephelsa.credijustotest.datasource.remote.json.UserJSON
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * _Real_ implementation of [RemoteUserDatasource] that is connected to the API.
 *
 * @param dispatcher see [RemoteUserDatasource] for more information.
 * @param client http client to make requests to the server
 */
class RemoteUserDatasourceImpl(
    override val dispatcher: CoroutineDispatcher,
    private val client: HttpClient,
) : RemoteUserDatasource {
    override suspend fun fetchUsers(): List<UserJSON> {
        return withContext(dispatcher) {
            client.get("/users")
        }
    }
}