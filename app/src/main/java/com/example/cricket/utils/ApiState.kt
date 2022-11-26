package com.example.cricket.utils

sealed class ApiState{

    class Success<T>(val data: List<T?>): ApiState()
    class Failure() : ApiState()
    object Loading: ApiState()
    object Empty: ApiState()

}
