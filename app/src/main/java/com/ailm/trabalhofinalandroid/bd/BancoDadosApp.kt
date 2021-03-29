package com.ailm.trabalhofinalandroid.bd

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ailm.trabalhofinalandroid.bd.PontosDAO
import com.ailm.trabalhofinalandroid.bd.PontosEntity

@Database(entities = [PontosEntity::class], version = 1)
abstract class BancoDadosApp : RoomDatabase() {

    abstract val pontosDAO: PontosDAO

    companion object {
        @Volatile
        private var INSTANCE: BancoDadosApp? = null

        fun getInstance(context: Context): BancoDadosApp {
            synchronized(this) {
                Log.d("LOG", " BancoDadosApp - getInstance()")

                var instance: BancoDadosApp? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        BancoDadosApp::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}