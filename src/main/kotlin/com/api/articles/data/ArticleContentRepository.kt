package com.api.articles.data

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria

import org.springframework.data.mongodb.core.findById

import org.springframework.stereotype.Repository



@Repository
class ArticleContentRepository(private val articleContentTemplate: ReactiveMongoTemplate) {
    fun get( id: String) = articleContentTemplate.findById<ArticleContent>(id)
}