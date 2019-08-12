package com.api.articles.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "Articles")
data class ArticleContent (@Id val id: String,
                        val title: String,
                        val publication: String,
                        val author: String,
                        val isodate: Instant,
                        val date: String,
                        val content: String)