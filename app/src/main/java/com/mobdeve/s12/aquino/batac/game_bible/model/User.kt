package com.mobdeve.s12.aquino.batac.game_bible.model

class User(
    val username: String,
    var password: String,
    var bio: String,
    var img: Int,
    var saved: ArrayList<Game>
){
    fun addToSaved(game: Game){
        saved.add(game)
    }
}