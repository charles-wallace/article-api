package com.api.articles.router

import com.api.articles.data.ArticleInfo
import com.api.articles.data.ArticleInfoRepository
import com.api.articles.handlers.ArticleInfoHandler
import com.api.articles.services.ArticleInfoServiceImpl
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.toFlux
import java.time.Instant


@RunWith(SpringRunner::class)
@WebAppConfiguration
@WebFluxTest
class ArticleInfoRouterTest {
    @MockBean
    private lateinit var repository: ArticleInfoRepository

    private lateinit var webTestClient: WebTestClient

    val articleInfoList = (1..10).map {
                item -> ArticleInfo(
                    item.toString(),
                    "title_${item}",
                    "publication_${item % 2}",
                    "author_${item}",
                    Instant.parse("200${item % 2}-1${item % 2 + 1}-1${item % 2}T10:12:35Z"),
                    "200${item % 2}-1${item % 2 + 1}-1${item % 2}"
            )
    }
    @Before
    fun setUp() {
        Mockito.`when`(repository.search("", "\\*", 1, "", "")).thenReturn(articleInfoList.toFlux())
        Mockito.`when`(repository.search("publication", "publication_0", 1, "", ""))
                .thenReturn(articleInfoList.filter{item -> item.publication.equals("publication_0")}.toFlux())

        this.webTestClient = WebTestClient.bindToRouterFunction(
                ArticleInfoRouter(
                        ArticleInfoHandler(
                                ArticleInfoServiceImpl().apply {
                                    articleInfoRepository = repository
                                }
                        )
                ).articleInfoRoutes()).build()
    }

    @Test
    fun `Test Search With "articles" path variable`() {
        val res = webTestClient.get()
                .uri("/articles")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ArticleInfo::class.java)
                .returnResult()
        assertNotNull(res.responseBody)
        assertEquals(res.responseBody!!.size, 10)
    }

    @Test
    fun `Test Search With "articles" and "publication" path variables`() {
        val res = webTestClient.get()
                .uri("/articles/publication/publication_0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ArticleInfo::class.java)
                .returnResult()
        assertNotNull(res.responseBody)
        assertEquals(res.responseBody!!.size, 5)
    }
}