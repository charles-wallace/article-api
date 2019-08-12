# **Articles API**

## Links

- **Articles API Deployment (Hosted on AWS EC2):**
 - http://ec2-54-83-188-153.compute-1.amazonaws.com:8080/articles
 - http://ec2-54-83-188-153.compute-1.amazonaws.com:8080/article/5d48689e73e15d5c1a24d980 example call w/ id
 - See description of API Structure below for more info
 - Note: When running locally use localhost:8080/article or /articles and make sure the base url in Requests.js under /src/util is set to 'http://localhost:8080' to run the front end.
 - **Front End Deployment (w/ access to api deployment):**  http://article-archive-east.s3-website-us-east-1.amazonaws.com/
 - **Front End Implementation (Git):** https://github.com/cwrepo/article-front-end

## Summary
 Spring Reactive Web, and Spring Reactive Mongo were used to develop a non-blocking REST API pulling from a MongoDB Atlas Database. A complementing UI was developed using ReactJS. The resulting deployment of the entire project can be accessed using the links above. The articles-api provides an interface to a Mongodb Atlas cluster containing a collection of over 8000 articles obtained from a kaggle kernal (https://www.kaggle.com/snapcrack/all-the-news). This data was sampled, cleaned, and adapted into a collection using python with pandas, numpy, and pymongo. The Project is packaged with maven and uses Java 11 and kotlin. Run "mvn package" to run with tests and then npm start in the front end application to run project locally.
 
## Overview

### Spring Implementation
  - **Routers** (com.api.articles.router) supply two primary end points, with additional filter options, and are made avaiable to client applications using functional routing in the "router" package. *ArticleContentRouter* takes a private instance of *ArticleContentHandler* in its constructor to handle GET Requests to article/{id} for a single article object with content, and *ArticleInfoRouter* takes a private instance of  *ArticleInfoHandler* to interpret requests for a list of article info documents.
  - **Handlers** (com.api.articles.handlers) contained in the handlers package parse api calls, pass request paramaters to services, and subscribe to the results of services (returning reactive Mono or Flux objects).
  - **Services** (com.api.articles.services) contained in the services package  obtain results corresponding to requests made by handlers through calls to a repository exposing methods to Atlas via a reactive mongo template, and returning Mono or Flux objects wrapping data classes defining the domain model.
  - **Repositories  and Data Objects** (com.api.articles.data) defining the domain model with of data classes are in the data package. Repositories house the MongoTemplate used by services to request MongoDB atlas data.
  
### API Structure
- Get article corresponding to specified id:**/article/{id}**
- Get paged articles from entire colleciton: **/articles?page={page_number}**
- Get paged articles filtered by specified year:**/articles?year={year_var}&page={page_number}**
- Get paged articles filtered by specified year and month: **/articles?year={year_var}&month={month_var}&page={page_number}**
- Get articles associated with specified publisher id:**/articles/publisher/{id}?page={page_number}**
- Similar options for publishers: /... the same filter methods associated with the /artilces endpoint are available to /articles/publisher/{id}

### MongoDB Data

#### Articles Collection
   The the primary collection in which all article content and information on the publisher, date, title, and authors is held and indexed with a 	unique id. This is the underlying collection providing the base collection for the ArticleInfo view, and content for the article/{id} endpoint. It is modeled with the ArticleContent data class.

#### ArticleInfo Collection
The Article Info Collection is a view  obtained by aggregating a projection in sorted ascending order on the Articles collection. This view of the underlying Article Collection is what supplies information for the articles/ and articles/publisher/{id}, endpoints, along with their associated query parameters. It is modeled with the ArticleInfo data class and queried via the ArticleInfo Repository. 