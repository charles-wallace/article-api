package com.api.articles.services

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ArticleContentServiceImplTest {

    @Autowired
    lateinit var articleContentServiceImpl:ArticleContentServiceImpl

    @Test
    fun getContent() {
        val res = articleContentServiceImpl.getContent("5d48689e73e15d5c1a24e484")
    }
}