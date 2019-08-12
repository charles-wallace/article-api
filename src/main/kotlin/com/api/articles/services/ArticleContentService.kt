package com.api.articles.services

import com.api.articles.data.ArticleContent
import reactor.core.publisher.Mono

interface ArticleContentService {
    fun getContent(id: String): Mono<ArticleContent>

}