package com.api.articles.services

import com.api.articles.data.ArticleInfo
import com.api.articles.data.ArticleInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ArticleInfoServiceImpl : ArticleInfoService {
    @Autowired
    lateinit var articleInfoRepository: ArticleInfoRepository
    override fun search(key: String, filter: String, page: Long,year: String, month: String): Flux<ArticleInfo> =
            articleInfoRepository.search(key, filter, page, year, month)
}