package com.example.demo

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeNull
import org.javamoney.moneta.Money
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import javax.money.Monetary

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    @Throws(Exception::class)
    fun getExample() {
        val result = restTemplate.getForEntity("http://localhost:$port/examples/1", Example::class.java)
        val body = result.body

        println(result)

        body.shouldNotBeNull()
        body.price shouldBeEqualTo Money.of(123, Monetary.getCurrency("EUR"))
    }

    @Test
    @Throws(Exception::class)
    fun patchExample() {
        val requestBody = """
                {
                    "price": {
                        "amount": 456,
                        "currency": "CHF"
                    }
                }
                """
        val request: HttpEntity<String> =
            HttpEntity<String>(requestBody, HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON })

        val result = restTemplate.exchange(
            "http://localhost:$port/examples/1",
            HttpMethod.PATCH,
            request,
            Example::class.java
        )
        val body = result.body

        println(result)

        body.shouldNotBeNull()
        body.price shouldBeEqualTo Money.of(456, Monetary.getCurrency("CHF"))
    }
}
