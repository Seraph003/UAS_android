package com.ukrida.uasmobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var FAVORITE_INSTANCE: BookDatabase? = null

        @JvmStatic
        fun bookDatabase(context: Context): BookDatabase {
            if (FAVORITE_INSTANCE == null) {
                synchronized(BookDatabase::class.java) {
                    FAVORITE_INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "favorite_book"
                    ).build()
                }
            }
            return FAVORITE_INSTANCE as BookDatabase
        }
    }
}
