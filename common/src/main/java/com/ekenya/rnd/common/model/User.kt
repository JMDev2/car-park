package com.ekenya.rnd.common.model

data class User(
    val email: String,
    val firstName: String,
    val id: Int? = null,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)