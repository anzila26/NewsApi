package anzila.binar.newsapi.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import anzila.binar.newsapi.model.list.Article
import anzila.binar.newsapi.model.list.ResponseDataList
import anzila.binar.newsapi.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    lateinit var liveDataList : MutableLiveData<List<Article>?>

    init {
        liveDataList = MutableLiveData()
    }

    fun getDataList(): MutableLiveData<List<Article>?> {
        return  liveDataList
    }

    fun callApiList(sources : String){
        RetrofitClient.instance.getAllList(sources)
            .enqueue(object : Callback<ResponseDataList> {
                override fun onResponse(
                    call: Call<ResponseDataList>,
                    response: Response<ResponseDataList>
                ) {
                    if (response.isSuccessful){
                        liveDataList.postValue(response.body()!!.articles)
                        Log.d(ContentValues.TAG, "onResponse: ${response.body()!!.articles.size}")
                    }else{
                        liveDataList.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataList>, t: Throwable) {
                    liveDataList.postValue(null)
                }
            })
    }
}