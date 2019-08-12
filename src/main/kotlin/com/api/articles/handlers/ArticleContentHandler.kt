package com.api.articles.handlers

import com.api.articles.data.ArticleContent
import com.api.articles.services.ArticleContentService
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*

@Component
class ArticleContentHandler(val articleContentService: ArticleContentService) {
    @CrossOrigin
    fun getArticle(serverRequest: ServerRequest) =
            articleContentService.getContent(serverRequest.pathVariable("id"))
                    .flatMap { if(it != null)
                                    ok().body(fromObject(it))
                                else
                                    notFound().build()
                    }.switchIfEmpty(notFound().build())
            /*
            ok().body(articleContentService.getContent(
                    serverRequest.pathVariable("id")), ArticleContent::class.java)
                    .switchIfEmpty(notFound().build())*/

}