package com.example.json_parser.model

import kr.pokeum.fragile.annotation.Deserializable
import kr.pokeum.fragile.annotation.SerializedName

@Deserializable
data class Person(
    @JvmField()
    @SerializedName("Name")
    val name: String,
    @JvmField()
    @SerializedName("Age")
    val age: Int,
    @JvmField()
    @SerializedName("Email")
    val email: String
)
