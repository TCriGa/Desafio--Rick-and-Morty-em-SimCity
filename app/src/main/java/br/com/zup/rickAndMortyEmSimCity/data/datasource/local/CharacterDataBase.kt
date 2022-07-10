package br.com.zup.rickAndMortyEmSimCity.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.rickAndMortyEmSimCity.data.datasource.local.dao.CharacterDAO
import br.com.zup.rickAndMortyEmSimCity.data.datasource.model.CharacterResult

@Database(entities = [CharacterResult::class], version = 2)
abstract class CharacterDataBase : RoomDatabase(){
    abstract fun characterDao(): CharacterDAO

    companion object {
        @Volatile
        private var INSTANCE: CharacterDataBase? = null

        fun getDatabase(context: Context): CharacterDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDataBase::class.java,
                    "character_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}