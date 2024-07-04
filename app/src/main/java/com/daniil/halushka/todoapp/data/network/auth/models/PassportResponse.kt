package com.daniil.halushka.todoapp.data.network.auth.models

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("client_id") val clientID: String,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("real_name") val realName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("psuid") val psuid: String
)