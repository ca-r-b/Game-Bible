package com.mobdeve.s12.aquino.batac.game_bible.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandlerGame(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        private val DB_NAME = "GAME_DATABASE"
        private val DB_VERSION = 1
        private val TABLE_NAME = "game_table"

//      TODO: Columns
        private val GAME_ID = "id"
        private val GAME_TITLE = "title"
//        private val GAME_IMG =
        private val GAME_DESC = "description"
        private val GAME_GENRE = "genre"
        private val GAME_DATE = "date"
        private val GAME_DEVELOPER = "developer"
        private val GAME_PUBLISHER = "publisher"
        private val GAME_TRAILER = "trailer"
        private val GAME_STATS = "stats"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($GAME_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$GAME_TITLE TEXT, " +
                "$GAME_DESC TEXT, " +
                "$GAME_GENRE TEXT, " +
                "$GAME_DATE TEXT, " +
                "$GAME_DEVELOPER TEXT, " +
                "$GAME_PUBLISHER TEXT, " +
                "$GAME_TRAILER TEXT, " +
                "$GAME_STATS FLOAT)"

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}