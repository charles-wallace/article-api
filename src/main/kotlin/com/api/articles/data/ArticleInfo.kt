package com.api.articles.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME
import java.time.Instant
import java.time.ZonedDateTime
import java.util.*

@Document(collection = "ArticleInfo")
data class ArticleInfo (@Id val id: String,
                        val title: String,
                        val publication: String,
                        val author: String,
                        val isodate: Instant,
                        val date: String)