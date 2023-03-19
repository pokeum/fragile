package com.example.json_parser.model

import kr.pokeum.fragile.annotation.Deserializable
import kr.pokeum.fragile.annotation.SerializedName

@Deserializable
data class Person(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Age")
    val age: Int,
    @SerializedName("Email")
    val email: String
)
