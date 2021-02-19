package com.ailm.trabalhofinalandroid.domain

data class LoginResult(
        var result:String? = null ,   //? = null (ou exclusivo) --> retornará somente 1
        var error:String? = null
)