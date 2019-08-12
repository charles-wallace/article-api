package com.api.articles.router

import com.api.articles.data.ArticleContent
import com.api.articles.data.ArticleContentRepository
import com.api.articles.handlers.ArticleContentHandler
import com.api.articles.services.ArticleContentServiceImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.toMono
import java.time.Instant



@RunWith(SpringRunner::class)
@WebAppConfiguration
@WebFluxTest
class ArticleContentRouterTest {

    @MockBean
    private lateinit var repository: ArticleContentRepository


    private lateinit var webTestClient: WebTestClient

    private val testArticle: ArticleContent = ArticleContent("1", "title_1", "publication_1", "author_1", Instant.now(), "1995-10-23", "Article 1 Content")

    /* Setup for testing mock article content */

    @Before
    fun setUp() {
        Mockito.`when`(repository.get("1")).thenReturn(testArticle.toMono())
        this.webTestClient = WebTestClient.bindToRouterFunction(
                    ArticleContentRouter(
                            ArticleContentHandler(
                                    ArticleContentServiceImpl().apply {
                                        articleContentRepository = repository
                                    }
                            )
                    ).articleContentRoutes()).build()
    }

    @Test
    fun `Test Get With Path Variable`() {
        val res = webTestClient.get()
                .uri("/articles/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ArticleContent::class.java)
                .returnResult()
        assertNotNull(res.responseBody)
        assertEquals(res.responseBody!!.equals(testArticle), true)
    }

    @Test
    fun `Test Get Without Path Variable`() {
        val res = webTestClient.get()
                .uri("/article")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ArticleContent::class.java)
                .returnResult()
    }

}