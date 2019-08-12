package com.api.articles.router

import com.api.articles.handlers.ArticleContentHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.router

@Component
class ArticleContentRouter(private val articleContentHandler: ArticleContentHandler) {

    @Bean
    @CrossOrigin
    fun articleContentRoutes() = router{
        "/article".nest {
            GET("/{id}", articleContentHandler::getArticle)
        }
    }
}