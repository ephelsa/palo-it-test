package com.github.ephelsa.credijustotest.datasource.remote.user

import com.github.ephelsa.credijustotest.datasource.remote.KtorConfig
import com.github.ephelsa.credijustotest.datasource.remote.json.UserJSON
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class RemoteUserDatasourceImplTest {

    @Test
    fun `Verify when a 200 is received, then a list of UserJSON is returned`() = runTest {
        // Given
        val rawJson = """
            [
               {
                  "id":1,
                  "name":"Leanne Graham",
                  "username":"Bret",
                  "email":"Sincere@april.biz",
                  "address":{
                     "street":"Kulas Light",
                     "suite":"Apt. 556",
                     "city":"Gwenborough",
                     "zipcode":"92998-3874",
                     "geo":{
                        "lat":"-37.3159",
                        "lng":"81.1496"
                     }
                  },
                  "phone":"1-770-736-8031 x56442",
                  "website":"hildegard.org",
                  "company":{
                     "name":"Romaguera-Crona",
                     "catchPhrase":"Multi-layered client-server neural-net",
                     "bs":"harness real-time e-markets"
                  }
               },
               {
                  "id":2,
                  "name":"Ervin Howell",
                  "username":"Antonette",
                  "email":"Shanna@melissa.tv",
                  "address":{
                     "street":"Victor Plains",
                     "suite":"Suite 879",
                     "city":"Wisokyburgh",
                     "zipcode":"90566-7771",
                     "geo":{
                        "lat":"-43.9509",
                        "lng":"-34.4618"
                     }
                  },
                  "phone":"010-692-6593 x09125",
                  "website":"anastasia.net",
                  "company":{
                     "name":"Deckow-Crist",
                     "catchPhrase":"Proactive didactic contingency",
                     "bs":"synergize scalable supply-chains"
                  }
               }
            ]
        """.trimIndent()

        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(rawJson),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val impl = RemoteUserDatasourceImpl(
            dispatcher = StandardTestDispatcher(testScheduler),
            client = KtorConfig.client(mockEngine)
        )

        // When
        val response = impl.fetchUsers()

        // Then
        response.shouldHaveSize(2)
        response.first().shouldBe(
            UserJSON(
                id = 1,
                name = "Leanne Graham",
                username = "Bret",
            )
        )
    }
}