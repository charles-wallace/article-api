package com.api.articles.services


import com.api.articles.data.ArticleInfo
import reactor.core.publisher.Flux

interface ArticleInfoService {
    fun search(key: String, filter: String, page: Long, year: String, month: String): Flux<ArticleInfo>
}