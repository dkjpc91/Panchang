package com.mithilakshar.mithilapanchang.Room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Updates::class], version = 1)
abstract class UpdatesDatabase : RoomDatabase() {
    abstract fun UpdatesDao(): UpdatesDao

    companion object {
        @Volatile
        private var INSTANCE: UpdatesDatabase? = null

        fun getDatabase(context: Context): UpdatesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UpdatesDatabase::class.java,
                    "Update_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}