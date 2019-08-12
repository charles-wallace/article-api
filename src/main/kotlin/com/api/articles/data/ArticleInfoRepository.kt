package com.api.articles.data

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria


import org.springframework.stereotype.Repository

val searchLimit = 10

@Repository
class ArticleInfoRepository(private val articleTemplate: ReactiveMongoTemplate) {

    /*
        The Key String is used to identify a document key on which the filter is applied,
        such as publisher. Filter represents a string to be compared in a regex expression against
        attributes corresponding to the specified document key. Date matches against a string date attribute,
        ordered YYYY-mm-dd
    */
    fun search( key: String, filter: String,  page:Long, year: String, month: String, limit:Int = searchLimit) =
            articleTemplate.find<ArticleInfo>(Query(Criteria.where(key)
                    .regex(".*$filter.*", "i")
                    .andOperator(Criteria.where("date").regex(".*$year-$month-*", "i")))
                    .skip(page*(limit.toLong()-1))
                    .limit(limit))
}