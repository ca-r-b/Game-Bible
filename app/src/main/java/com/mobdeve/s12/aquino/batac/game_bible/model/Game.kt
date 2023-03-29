package com.mobdeve.s12.aquino.batac.game_bible.model

class Game(
    val title: String,
    val img: Int, //TODO: Change to String (maybe after implementation of Database)
    val desc: String,
    val genre: String,
    val releaseDate: String,
    val developer: String,
    val publisher: String,
    val trailer: String,
    var section: String

//    val reviews: ArrayList<Review>

//    val stats: Float = 0F
//  TODO: LIKES divided by ('/') REVIEWS
)