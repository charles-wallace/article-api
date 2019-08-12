package com.api.articles.handlers

import com.api.articles.data.ArticleInfo
import com.api.articles.services.ArticleInfoService
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class ArticleInfoHandler(val articleInfoService: ArticleInfoService) {
    @CrossOrigin
    fun searchAll(serverRequest: ServerRequest) =
        ok().body(articleInfoService.search("", "\\*",
                serverRequest.queryParam("page").orElse("1").toLong(),
                serverRequest.queryParam("year").orElse(""),
                serverRequest.queryParam("month").orElse("")
        ), ArticleInfo::class.java)


    @CrossOrigin
    fun searchPublication(serverRequest: ServerRequest) =
            ok().body(articleInfoService.search(
                    "publication",
                    serverRequest.pathVariable("publication_id"),
                    serverRequest.queryParam("page").orElse("1").toLong(),
                    serverRequest.queryParam("year").orElse(""),
                    serverRequest.queryParam("month").orElse("")
                    ), ArticleInfo::class.java)
}