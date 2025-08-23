package roni.putra.fullmateri.sqlite.room

import android.content.Context
import androidx.room.*


// Database class after the version update.
@Database(
    version = 1,
    entities = [Note::class],

    )

abstract class NoteDB: RoomDatabase() {

    companion object {

        @Volatile private var instance : NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDB::class.java,
            "noteroni.db"
        ).build()

    }
}