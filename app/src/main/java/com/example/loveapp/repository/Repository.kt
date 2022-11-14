package com.example.loveapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.loveapp.App
import com.example.loveapp.model.LoveModel
import com.example.loveapp.network.LoveApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: LoveApi){
    fun getMutableLiveDataOfLoveModel(
        firstName: String,
        secondName: String
    ): MutableLiveData<LoveModel> {
        val liveLoveModel: MutableLiveData<LoveModel> = MutableLiveData()

        api.getPercentage(firstName, secondName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveLoveModel.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.message.toString()}")
            }
        })
        return liveLoveModel
    }

}