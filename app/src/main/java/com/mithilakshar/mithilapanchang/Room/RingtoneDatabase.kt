package com.mithilakshar.mithilapanchang.Room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Ringtone::class], version = 1, exportSchema = false)
abstract class RingtoneDatabase : RoomDatabase() {
    abstract fun ringtonedao(): RingtoneDao

    companion object {
        @Volatile
        private var INSTANCE: RingtoneDatabase? = null

        fun getDatabase(context: Context): RingtoneDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RingtoneDatabase::class.java,
                    "ringtone_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}