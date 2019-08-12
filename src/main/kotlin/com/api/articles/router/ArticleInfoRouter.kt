package com.api.articles.router

import com.api.articles.handlers.ArticleInfoHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.router

@Component
class ArticleInfoRouter(private val articleInfoHandler: ArticleInfoHandler) {

    @Bean
    @CrossOrigin
    fun articleInfoRoutes() = router{
        "/articles".nest {
            GET("/", articleInfoHandler::searchAll)
            "/publication".nest{
                GET("/{publication_id}", articleInfoHandler::searchPublication)
            }
        }
    }
}