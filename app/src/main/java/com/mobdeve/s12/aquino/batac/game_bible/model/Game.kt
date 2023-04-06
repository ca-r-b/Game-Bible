package com.mobdeve.s12.aquino.batac.game_bible.model

data class Game(
    val title: String? = null,
    val img: String? = null,
    val desc: String? = null,
    val genre: String? = null,
    val releaseDate: String? = null,
    val developer: String? = null,
    val publisher: String? = null,
    val trailer: String? = null,
    var section: String? = null,
)