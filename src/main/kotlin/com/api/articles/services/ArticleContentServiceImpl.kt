package com.api.articles.services

import com.api.articles.data.ArticleContent
import com.api.articles.data.ArticleContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ArticleContentServiceImpl : ArticleContentService {
    @Autowired
    lateinit var articleContentRepository: ArticleContentRepository
    override fun getContent(id: String): Mono<ArticleContent> =
            articleContentRepository.get(id)
}