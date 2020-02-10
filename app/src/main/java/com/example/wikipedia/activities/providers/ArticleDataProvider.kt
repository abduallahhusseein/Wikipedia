package com.example.wikipedia.activities.providers

import com.example.wikipedia.activities.models.Urls
import com.example.wikipedia.activities.models.WikiResult
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import java.io.Reader

class ArticleDataProvider {
    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Pluralsight Wikipedia")
    }

    fun search(term: String, skip: Int, take: Int,responseHandler: (result: WikiResult) -> Unit?){
        Urls.getSearchUrl(term,skip,take).httpGet()
            .responseObject(WikipediaDataDeserializer()){ _,response,result ->  //first _ we can change it by request,second _ we can change it by response
           //do something with result
                if(response.httpStatusCode !=200){
                    throw Exception("Unable to get articles")
                }
                val(data,_) = result
                responseHandler.invoke(data as WikiResult)

        }
    }

    fun gerRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Urls.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataDeserializer()){_,response,result ->
                if(response.httpStatusCode !=200){
                    throw Exception("Unable to get articles")
                }
            val(data,_) = result
            responseHandler.invoke(data as WikiResult)

        }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult>{
         override fun deserialize(reader: Reader)= Gson().fromJson(reader,WikiResult::class.java)

    }
}

