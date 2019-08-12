# **Articles API**


## Links
    **Front End Implementation (Git):** https://github.com/cwrepo/article-api
    **Front End Deployment (w/ access to api deployment):**  http://article-archive-east.s3-website-us-east-1.amazonaws.com/
    **Articles API Deployment:**
        - http://ec2-54-83-188-153.compute-1.amazonaws.com:8080/articles
        - http://ec2-54-83-188-153.compute-1.amazonaws.com:8080/article/{id} example ids: 5d48689e73e15d5c1a24d980, 5d48689e73e15d5c1a24df55
        - see description of API Structure below for more info
        - when running locally use localhost:8080/article or /articles and make sure the base url in Requests.js under /src/util is set to 'http://localhost:8080' to run the front end.

## Summary

    Spring Reactive Web, and Spring Reactive Mongo were used to develop a non-blocking REST API pulling from a MongoDB Atlas database
    and exposed via a front end built with ReactJS. 
    
    The articles-api provides an interface to a Mongodb Atlas cluster containing a collection of over 8000 articles obtained from 
    a kaggle kernal (https://www.kaggle.com/snapcrack/all-the-news). This data was sampled, cleaned, and adapted into a collection 
    using python with pandas, numpy, and pymongo. 
    
    The Project is packaged with maven and uses Java 11 and kotlin.
    
##  Overview
    
### Implementation
    - **Routers** supply two primary end points, with additional filter options, and are made avaiable to client applications using functional routing
    in the "router" package. ArticleContentRouter takes a private instance of ArticleContentHandler in its constructor to handle GET Requests to article/{id}
    for a single article object with content, and ArticleInfoRouter takes a private instance of  ArticleInfoHandler to interpret requests for a list of 
    article info documents.
    
    - **Handlers** contained in the handlers package parse api calls, pass request paramaters to services, and subscribe to the results
    of services (returning reactive Mono or Flux objects). 
    
    - **Services** contained in the services package  obtain results corresponding to requests made by handlers through calls to a repository 
    exposing methods to Atlas via a reactive mongo template, and returning Mono or Flux objects wrapping data classes defining the domain model. 
    
    - **Repositories** and simple objects defining the domain model with of data classes are in the data package. Repositories house the MongoTemplate
    used by services to request MongoDB atlas data.
    
### API Structure (brackets represent options and path variables)

    - Get article corresponding to specified id: /article/{id}
    - Get paged articles from entire colleciton: /articles?page={page_number} (retrieves )
    - Get paged articles filtered by specified year: /articles?year={year_var}&page={page_number}
    - Get paged articles filtered by specified year and month: /articles?year={year_var}&month={month_var}&page={page_number} (retrieves )
    - Get articles associated with specified publisher id: /articles/publisher/{id}?page={page_number} (retrieves e)
    - Similar options for publishers: /... the same filter methods associated with the /artilces endpoint are available to /articles/publisher/{id}
        
    
### Data

    - Articles Collection
    The is the primary collection in which all article content and information on the publisher, date, title, and authors is held and indexed with a unique id.
    This is the underlying collection providing the base collection for the ArticleInfo view, and content for the article/{id} endpoint. It is modeled with the
    ArticleContent Data class. 
    
    - ArticleInfo Collection
    Aggregate view of the collection without content, sorted in ascending order. This view of the underlying Article Collection is what supplies information for
    the articles/ and articles/publisher/{id}, endpoints, along with their associated query parameters. It is modeled with the ArticleInfo

