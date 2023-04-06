package com.mobdeve.s12.aquino.batac.game_bible.model

data class User(
    val uid: String? = null,
    val email: String? = null,
    val username: String? = null,
    var password: String? = null,
    var bio: String? = null,
//    TODO: Setup default profile picture
    var img: String? = null,
)