package com.fedx.teststav.network

data class Characters(
    val results: List<Character>
)

data class Character(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
