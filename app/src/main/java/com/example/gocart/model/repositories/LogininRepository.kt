package com.example.gocart.model.repositories

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gocart.model.MyApi
import com.example.gocart.model.data.DefaultResponse
import com.example.gocart.model.data.LoginResponse
import com.example.gocart.ui.MainActivity
import retrofit2.Call
import retrofit2.Response

class LogininRepository (val context : Context){

    private val _user_token = MutableLiveData<String>()
    val user_token : LiveData<String> = _user_token

    fun login (email : String , password : String  ) : LiveData<Boolean>{
        val loginStatus = MutableLiveData<Boolean>()
        val call = MyApi().retrofit.Loginin(email,password)
        call.enqueue(object : retrofit2.Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val LoginResponse = response.body()
                val status = LoginResponse?.status
                val message = LoginResponse?.message
                val data = LoginResponse?.data
                if (status == true) {
                    // Access the user data
                    val email = data?.email
                    val name = data?.name
                    val phone = data?.phone
                    val id = data?.id
                    val image = data?.image
                    val point = data?.points
                    val credit = data?.Credit
                    val token = data?.token.toString()
                    _user_token.value = token
                    loginStatus.value = true
                    Toast.makeText(context,"${message.toString()}",Toast.LENGTH_SHORT).show()

                }
                else if (status == false)
                {
                    loginStatus.value = false
                    Toast.makeText(context,"${message.toString()}",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginStatus.value  = false
            }
        })
        return loginStatus
    }
}