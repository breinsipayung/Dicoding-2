package com.example.brein.mydicodingsubmission2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brein.mydicodingsubmission2.model.ItemUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel(application: Application): AndroidViewModel(application) {
    val listUserMain = MutableLiveData<ArrayList<ItemUser>>()

    internal fun setSearch(Login: String) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization" , "token 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")
        client.addHeader("User-Agent" , "request")

        val url = "https://api.github.com/search/users?q=$Login"

        client.get(url , object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try{
                    val listSearch = ArrayList<ItemUser>()
                    JSONObject(String(responseBody!!)).run {
                        val search = this.getJSONArray("items")
                        for(i in 0 until search.length()){
                            search.getJSONObject(i).run {
                                listSearch.add(
                                    ItemUser(
                                        this.getString("login"),
                                        this.getString("avatar_url")
                                    )
                                )
                            }
                        }
                    }
                    listUserMain.postValue(listSearch)
                }catch (ex : Exception){
                    Log.d("Exception" , ex.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val error = when(statusCode){
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Log.d("error" , error)
            }
        })
    }

    fun getUsers() : LiveData<ArrayList<ItemUser>> {
        return listUserMain
    }

}