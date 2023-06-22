package com.example.gocart.model.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gocart.model.MyApi
import com.example.gocart.model.data.DefaultResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule

import retrofit2.Call
import retrofit2.Response


class SignupRepository (private  val context : Context){

   fun signup (email : String , password : String , name : String , phoneNumber : String ) : LiveData<Boolean>{
           val signupStatus = MutableLiveData<Boolean>()
           val call = MyApi().retrofit.Signup(email,password,name,phoneNumber)
           call.enqueue(object : retrofit2.Callback<DefaultResponse> {
               override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                   val defaultResponse = response.body()
                   val status = defaultResponse?.status
                   val message = defaultResponse?.message
                   val data = defaultResponse?.data
                   if (data != null) {
                       val email = data?.email
                       val name = data?.name
                       val phone = data?.phone
                       val id = data?.id
                       val image = data?.image
                       val token = data?.token
                       signupStatus.value = true
                       Toast.makeText(context,"${message.toString()}",Toast.LENGTH_SHORT).show()
                   }
                   else if (data == null){
                       signupStatus.value = false
                       Toast.makeText(context,"${message.toString()}",Toast.LENGTH_SHORT).show()
                   }
               }

               override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                   signupStatus.value = false
               }
           })
       return signupStatus
       }
}