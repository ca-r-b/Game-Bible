package com.mobdeve.s12.aquino.batac.game_bible.model

class User(
    val username: String,
    var password: String,
    var bio: String = "Hello, there!",
//    TODO: Setup default profile picture
    var img: Int,
//    var saved: ArrayList<Game>
)