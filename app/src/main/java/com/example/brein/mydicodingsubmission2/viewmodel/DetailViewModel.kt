package com.example.brein.mydicodingsubmission2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brein.mydicodingsubmission2.model.User
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val detailUserGithub = MutableLiveData<User>()

    fun setUserDetail(Login: String) {
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        val url = "https://api.github.com/users/$Login"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    JSONObject(String(responseBody!!)).run {
                        detailUserGithub.postValue(
                            User(
                                this.getString("login"),
                                this.getString("name"),
                                this.getString("avatar_url"),
                                this.getString("location"),
                                this.getString("following"),
                                this.getString("followers"),
                                this.getString("public_repos"),
                                this.getString("company")
                            )
                        )
                    }

                } catch (ex: Exception) {
                    Log.d("exception : ", ex.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Log.d("error", errorMessage)
            }
        })
    }

    fun getDetailUser(): LiveData<User> {
        return detailUserGithub
    }
}