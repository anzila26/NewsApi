package anzila.binar.newsapi.network

import anzila.binar.newsapi.model.ResponseDataSource
import anzila.binar.newsapi.model.Source
import anzila.binar.newsapi.model.list.ResponseDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestfulApi {

    @GET("top-headlines/sources")
    fun getAllSources(
        @Query("category") category : String,
        @Query("apikey") apikey : String = "d4525585d0b74613851a5fbfcd0b6045"
    ) : Call<ResponseDataSource>

    @GET("top-headlines")
    fun getAllList(
        @Query("sources") sources : String ,
        @Query("apikey") apikey : String = "d4525585d0b74613851a5fbfcd0b6045"
    ) : Call<ResponseDataList>
}