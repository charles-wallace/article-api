package com.api.articles.services

import com.api.articles.data.ArticleInfoRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class ArticleInfoServiceImplTest {

    @Autowired
    lateinit var articleInfoServiceImpl: ArticleInfoServiceImpl
    /*
        Test: search(key: String, filter: String, page: Long, year: String, month: String): Flux<ArticleInfo>
    */
    @Test
    fun search() {
        articleInfoServiceImpl.search("", "", 0, "", "")
    }
}