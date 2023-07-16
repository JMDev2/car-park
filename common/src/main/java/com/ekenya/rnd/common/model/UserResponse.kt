package com.ekenya.rnd.common.model

data class UserResponse(
    val email: String,
    val firstname: String,
    val id: Int,
    val password: String,
    val phonenumber: String,
    val secondname: String
)